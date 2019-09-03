package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.repository.computers.ComputerProducerRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<ComputerProducer> getAllComputerProducers(){
        return computerProducerRepository.findAll();
    }

    public Optional<ComputerProducer> getComputerProducerById(long id){
        return computerProducerRepository.findById(id);
    }

    Optional<ComputerProducer> getComputerProducerByName(String computerProducerName){
        return computerProducerRepository.findByProducerName(computerProducerName);
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
    public HttpStatusEnum addNewComputerProducer(ComputerProducer computerProducer){
        Set<ConstraintViolation<ComputerProducer>> validationErrors = validator.validate(computerProducer);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        if(getComputerProducerByName(computerProducer.getProducerName()).isPresent())
            return HttpStatusEnum.CONFLICT;

        computerProducerRepository.save(computerProducer);
        return HttpStatusEnum.CREATED;
    }

    public HttpStatusEnum deleteComputerProducer(Long id){
        if(computerProducerRepository.findById(id).isPresent()){
            computerProducerRepository.deleteById(id);
            return HttpStatusEnum.OK;
        }
        return HttpStatusEnum.BADREQUEST;
    }
}
