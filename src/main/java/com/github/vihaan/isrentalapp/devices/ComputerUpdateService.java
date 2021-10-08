package com.github.vihaan.isrentalapp.devices;

import com.github.vihaan.isrentalapp.devices.entities.ComputerRepository;
import com.github.vihaan.isrentalapp.devices.entities.ComputerStatusRepository;
import com.github.vihaan.isrentalapp.devices.entities.Computer;
import com.github.vihaan.isrentalapp.devices.entities.ComputerStatus;
import com.github.vihaan.isrentalapp.service.HttpStatusEnum;
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
        if(updates.containsKey("operatingSystem")) {
            updateOperatingSystem(computer, updates.get("operatingSystem"));
        }
        if(updates.containsKey("diskType")) {
            updateDiskType(computer, updates.get("diskType"));
        }
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
