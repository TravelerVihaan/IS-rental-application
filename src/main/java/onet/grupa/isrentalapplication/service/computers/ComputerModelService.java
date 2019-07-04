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

    /**
     * Return simple response with list of all found Computer Models in database.
     *
     * @return ResponseEntity with list and status (OK, or BAD_REQUEST)
     */
    public ResponseEntity<List<ComputerModel>> getResponseWithAllComputerModels(){
        return ResponseEntity.of(getAllComputerModels());
    }

    /**
     * Return simple response with found Computer Model in database.
     *
     * @param id id of ComputerModel entity
     *
     * @return ResponseEntity with Computer Model and status (OK, or BAD_REQUEST)
     */
    public ResponseEntity<ComputerModel> getResponseWithComputerModel(long id){
        return ResponseEntity.of(getComputerModel(id));
    }

    /**
     * Add new computer model to database
     *
     * @param computerModel Object of computer model generated from JSON incoming from front-end
     * @param result BindingResult object with list of errors, if any exist.
     *
     * @return ResponseEntity with status depending on result of insert entity to DB,
     * can return BAD_REQUEST if result has errors
     *            CONFLICT if entity has already exists
     *            CREATED if entity was saved in DB
     *
     */
    public ResponseEntity<?> addNewComputerModel(ComputerModel computerModel, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(getComputerModelByName(computerModel.getModel()).isPresent())
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        computerModelRepository.save(computerModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    Private methods
     */
    private Optional<ComputerModel> getComputerModelByName(String modelName){
        return Optional.ofNullable(computerModelRepository.findByModel(modelName));
    }

    private Optional<ComputerModel> getComputerModel(long id){
        return computerModelRepository.findById(id);
    }

    private Optional<List<ComputerModel>> getAllComputerModels(){
        return Optional.ofNullable(computerModelRepository.findAll());
    }

}
