package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.OperatingSystem;
import onet.grupa.isrentalapplication.repository.computers.OperatingSystemRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OperatingSystemService {

    private OperatingSystemRepository operatingSystemRepository;
    private Validator validator;

    @Autowired
    public OperatingSystemService(OperatingSystemRepository operatingSystemRepository, Validator validator){
        this.operatingSystemRepository = operatingSystemRepository;
        this.validator = validator;
    }

    /*
    Public methods
     */
    /**
     * Return simple response with list of all found OperatingSystems in database.
     *
     * @return List with Operating Systems
     */
    public List<OperatingSystem> getAllOperatingSystems(){
        return operatingSystemRepository.findAll();
    }

    /**
     * Return simple response with found OperatingSystem in database.
     *
     * @param id id of OperatingSystem entity
     *
     * @return Optional with OS
     */
    public Optional<OperatingSystem> getOperatingSystemById(long id){
        return operatingSystemRepository.findById(id);
    }


    /**
     * Add new OperatingSystem entity to database
     *
     * @param operatingSystem Object of computer model generated from JSON incoming from front-end
     *
     * @return HttpStatusEnum depending on result of insert entity to DB,
     * can return BADREQUEST if result has errors
     *            CONFLICT if entity has already exists
     *            CREATED if entity was saved in DB
     *
     */
    public HttpStatusEnum addNewOperatingSystem(OperatingSystem operatingSystem){
        Set<ConstraintViolation<OperatingSystem>> validationErrors = validator.validate(operatingSystem);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        if(getOperatingSystemByName(operatingSystem.getOperatingSystem()).isPresent())
                return HttpStatusEnum.CONFLICT;

        operatingSystemRepository.save(operatingSystem);
        return HttpStatusEnum.CREATED;
    }

    /*
    Private methods
     */
    private Optional<OperatingSystem> getOperatingSystemByName(String operatingSystem){
        return Optional.ofNullable(operatingSystemRepository.findByOperatingSystem(operatingSystem));
    }
}
