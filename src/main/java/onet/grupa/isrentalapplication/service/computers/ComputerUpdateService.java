package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;


@Service
public class ComputerUpdateService {

    private ComputerRepository computerRepository;
    private Validator validator;

    @Autowired
    public ComputerUpdateService(ComputerRepository computerRepository,
                                 Validator validator){
        this.computerRepository = computerRepository;
        this.validator = validator;
    }

    public HttpStatusEnum updateComputer(Long id, Map<String, String> updates){
        if(computerRepository.findById(id).isEmpty())
            return HttpStatusEnum.NOTFOUND;
        Computer computer = executeUpdates(computerRepository.findById(id).orElseThrow(), updates);
        Set<ConstraintViolation<Computer>> validationErrors = validator.validate(computer);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        return HttpStatusEnum.OK;
    }

    private Computer executeUpdates(Computer computer, Map<String, String> updates){
        if(updates.containsKey("operatingSystem"))
            computer.getOperatingSystem().setOperatingSystem(updates.get("operatingSystem"));
        if(updates.containsKey("diskType"))
            computer.getOperatingSystem().setOperatingSystem(updates.get("diskType"));
        return computer;
    }
}
