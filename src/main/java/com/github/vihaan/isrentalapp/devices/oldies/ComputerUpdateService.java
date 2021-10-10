package com.github.vihaan.isrentalapp.devices.oldies;

import com.github.vihaan.isrentalapp.devices.ComputerStatus;
import com.github.vihaan.isrentalapp.devices.entities.ComputerEntity;
import com.github.vihaan.isrentalapp.devices.entities.ComputerRepository;
import com.github.vihaan.isrentalapp.devices.entities.ComputerStatusRepository;
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
        ComputerEntity computerEntity = executeUpdates(computerRepository.findById(id).orElseThrow(), updates);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ComputerEntity>> validationErrors = validator.validate(computerEntity);
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

    private void updateComputerStatus(ComputerEntity computerEntity, String status){
        ComputerStatus computerStatus = computerStatusRepository.findByStatus(status);
        computerEntity.setComputerStatus(computerStatus);
        computerRepository.save(computerEntity);
    }

    private ComputerEntity executeUpdates(ComputerEntity computerEntity, Map<String, String> updates){
        if(updates.containsKey("operatingSystem")) {
            updateOperatingSystem(computerEntity, updates.get("operatingSystem"));
        }
        if(updates.containsKey("diskType")) {
            updateDiskType(computerEntity, updates.get("diskType"));
        }
        return computerEntity;
    }

    private ComputerEntity updateOperatingSystem(ComputerEntity computerEntity, String os){
        computerEntity.setOperatingSystem(operatingSystemService
                .getOperatingSystemByName(os).orElseGet(computerEntity::getOperatingSystem));
        return computerEntity;
    }

    private ComputerEntity updateDiskType(ComputerEntity computerEntity, String disk){
        computerEntity.setDiskType(diskTypeService
                .getDiskTypeByName(disk).orElseGet(computerEntity::getDiskType));
        return computerEntity;
    }
}
