package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.repository.computers.ComputerProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerProducerService {

    private ComputerProducerRepository computerProducerRepository;

    @Autowired
    public ComputerProducerService(ComputerProducerRepository computerProducerRepository){
        this.computerProducerRepository = computerProducerRepository;
    }

    /*
    Public methods
     */
    public ResponseEntity<List<ComputerProducer>> getResponseWithAllComputerProducers(){
        return ResponseEntity.of(getAllComputerProducers());
    }

    public ResponseEntity<ComputerProducer> getResponseWithComputerProducer(long id){
        return ResponseEntity.of(getComputerProducer(id));
    }

    public ResponseEntity<?> addNewComputerProducer(ComputerProducer computerProducer, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(getComputerProducerByName(computerProducer.getProducerName()).isPresent())
                return new ResponseEntity<>(HttpStatus.CONFLICT);

        computerProducerRepository.save(computerProducer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    Private methods
     */
    private Optional<ComputerProducer> getComputerProducerByName(String computerProducerName){
        return Optional.ofNullable(computerProducerRepository.findByProducerName(computerProducerName));
    }

    private Optional<ComputerProducer> getComputerProducer(long id){
        return computerProducerRepository.findById(id);
    }

    private Optional<List<ComputerProducer>> getAllComputerProducers(){
        return Optional.ofNullable(computerProducerRepository.findAll());
    }
}
