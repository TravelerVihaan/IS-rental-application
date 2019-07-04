package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.repository.computers.ComputerProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ComputerProducerService {

    private ComputerProducerRepository computerProducerRepository;
    private Validator validator;

    @Autowired
    public ComputerProducerService(ComputerProducerRepository computerProducerRepository, Validator validator){
        this.computerProducerRepository = computerProducerRepository;
        this.validator = validator;
    }

    /*
    Public methods
     */
    /**
     * Return simple response with list of all found Computer Producers in database.
     *
     * @return ResponseEntity with list and status (OK, or BAD_REQUEST)
     */
    public ResponseEntity<List<ComputerProducer>> getResponseWithAllComputerProducers(){
        return ResponseEntity.of(getAllComputerProducers());
    }

    /**
     * Return simple response with found Computer Producer in database.
     *
     * @param id id of ComputerProducer entity
     *
     * @return ResponseEntity with ComputerProducer and status (OK, or BAD_REQUEST)
     */
    public ResponseEntity<ComputerProducer> getResponseWithComputerProducer(long id){
        return ResponseEntity.of(getComputerProducer(id));
    }

    /**
     * Add new ComputerProducer entity to database
     *
     * @param computerProducer Object of computer model generated from JSON incoming from front-end
     *
     * @return ResponseEntity with status depending on result of insert entity to DB,
     * can return BAD_REQUEST if result has errors
     *            CONFLICT if entity has already exists
     *            CREATED if entity was saved in DB
     *
     */
    public ResponseEntity<?> addNewComputerProducer(ComputerProducer computerProducer){
        Set<ConstraintViolation<ComputerProducer>> validationErrors = validator.validate(computerProducer);
        if(!validationErrors.isEmpty())
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
