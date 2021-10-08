package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.devices.entities.OperatingSystem;
import onet.grupa.isrentalapplication.devices.entities.OperatingSystemRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OperatingSystemService {

    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    public OperatingSystemService(OperatingSystemRepository operatingSystemRepository){
        this.operatingSystemRepository = operatingSystemRepository;
    }

    public List<OperatingSystem> getAllOperatingSystems(){
        return operatingSystemRepository.findAll();
    }

    public Optional<OperatingSystem> getOperatingSystemById(long id){
        return operatingSystemRepository.findById(id);
    }

    Optional<OperatingSystem> getOperatingSystemByName(String operatingSystem){
        return Optional.ofNullable(operatingSystemRepository.findByOperatingSystem(operatingSystem));
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
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<OperatingSystem>> validationErrors = validator.validate(operatingSystem);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        if(getOperatingSystemByName(operatingSystem.getOperatingSystem()).isPresent())
                return HttpStatusEnum.CONFLICT;

        operatingSystemRepository.save(operatingSystem);
        return HttpStatusEnum.CREATED;
    }

    public HttpStatusEnum deleteOperatingSystem(Long id){
        if(operatingSystemRepository.findById(id).isPresent()){
            operatingSystemRepository.deleteById(id);
            return HttpStatusEnum.OK;
        }
        return HttpStatusEnum.BADREQUEST;
    }
}
