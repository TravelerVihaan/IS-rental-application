package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.domain.computers.ComputerStatus;
import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.domain.computers.OperatingSystem;
import onet.grupa.isrentalapplication.domain.rentals.RentStatus;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import onet.grupa.isrentalapplication.repository.computers.ComputerStatusRepository;
import onet.grupa.isrentalapplication.repository.computers.OperatingSystemRepository;
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
    private OperatingSystemService operatingSystemService;
    private DiskTypeService diskTypeService;
    private ComputerStatusRepository computerStatusRepository;

    @Autowired
    public ComputerUpdateService(ComputerRepository computerRepository,
                                 OperatingSystemService operatingSystemService,
                                 DiskTypeService diskTypeService,
                                 ComputerStatusRepository computerStatusRepository){
        this.computerRepository = computerRepository;
        this.operatingSystemService = operatingSystemService;
        this.diskTypeService = diskTypeService;
        this.computerStatusRepository = computerStatusRepository;
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
        ComputerStatus computerStatus = computerStatusRepository.findByStatus(status);
        computer.setComputerStatus(computerStatus);
        computerRepository.save(computer);
    }

    private Computer executeUpdates(Computer computer, Map<String, String> updates){
        if(updates.containsKey("operatingSystem"))
            computer = updateOperatingSystem(computer, updates.get("operatingSystem"));
        if(updates.containsKey("diskType"))
            computer = updateDiskType(computer, updates.get("diskType"));
        return computer;
    }

    private Computer updateOperatingSystem(Computer computer, String os){
        computer.setOperatingSystem(operatingSystemService
                .getOperatingSystemByName(os).orElseGet(computer::getOperatingSystem));
        return computer;
    }

    private Computer updateDiskType(Computer computer, String disk){
        computer.setDiskType(diskTypeService
                .getDiskTypeByName(disk).orElseGet(computer::getDiskType));
        return computer;
    }
}
