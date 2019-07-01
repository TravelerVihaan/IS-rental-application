package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerModel;
import onet.grupa.isrentalapplication.repository.computers.ComputerModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerModelService {

    private ComputerModelRepository computerModelRepository;

    @Autowired
    public ComputerModelService(ComputerModelRepository computerModelRepository){
        this.computerModelRepository = computerModelRepository;
    }

    /*
   Public methods
    */
    public ResponseEntity<List<ComputerModel>> getResponseWithAllComputerModels(){
        return ResponseEntity.of(getAllComputerModels());
    }

    public ResponseEntity<ComputerModel> getResponseWithComputerModel(long id){
        return ResponseEntity.of(getComputerModel(id));
    }

    /*
     * This function add new Disk Type to database
     * @param diskType
     * @param binding result
     */
    public ResponseEntity<?> addNewComputerModel(ComputerModel computerModel, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(getAllComputerModels().isPresent()){
                if(getAllComputerModels()
                        .get()
                        .stream()
                        .anyMatch(
                            cmodel -> cmodel
                                .getModel()
                                .equalsIgnoreCase(computerModel.getModel())
                                &&
                                cmodel.getComputerProducer()
                                        .getProducerName()
                                        .equalsIgnoreCase(computerModel.getComputerProducer().getProducerName())))
                    //.filter(cmodel -> cmodel.getModel().equalsIgnoreCase(computerModel.getModel()))
                    //.filter(cmodel -> cmodel.getComputerProducer().getProducerName().equalsIgnoreCase(computerModel.getComputerProducer().getProducerName()))
                    //.count() > 0)
                    return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        computerModelRepository.save(computerModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    Private methods
     */
    private Optional<ComputerModel> getComputerModel(long id){
        return computerModelRepository.findById(id);
    }

    private Optional<List<ComputerModel>> getAllComputerModels(){
        return Optional.ofNullable(computerModelRepository.findAll());
    }

}
