package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;


@Service
public class ComputerUpdateService {

    private ComputerRepository computerRepository;

    @Autowired
    public ComputerUpdateService(ComputerRepository computerRepository){
        this.computerRepository = computerRepository;
    }

    HttpStatusEnum updateComputer(Long id, Map<String, String> updates){
        if(computerRepository.findById(id).isEmpty())
            return HttpStatusEnum.NOTFOUND;
        Computer computer = executeUpdates(computerRepository.findById(id).orElseThrow(), updates);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Computer>> validationErrors = validator.validate(computer);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        return HttpStatusEnum.OK;
    }

    HttpStatusEnum changeComputerStatus(String status, Long id){
        if(computerRepository.findById(id).isPresent()) {
            computerRepository.findById(id).ifPresent(computer -> updateComputerStatus(computer, status));
            return HttpStatusEnum.OK;
        }
        return HttpStatusEnum.NOTFOUND;
    }

    private void updateComputerStatus(Computer computer, String status){
        computer.getComputerStatus().setStatus(status);
        computerRepository.save(computer);
    }

    private Computer executeUpdates(Computer computer, Map<String, String> updates){
        if(updates.containsKey("operatingSystem"))
            computer.getOperatingSystem().setOperatingSystem(updates.get("operatingSystem"));
        if(updates.containsKey("diskType"))
            computer.getOperatingSystem().setOperatingSystem(updates.get("diskType"));
        return computer;
    }
}
