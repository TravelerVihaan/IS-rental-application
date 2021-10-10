package com.github.vihaan.isrentalapp.devices;

import com.github.vihaan.isrentalapp.devices.entities.ComputerEntity;
import com.github.vihaan.isrentalapp.devices.entities.DevicesRepositoryFacade;
import com.github.vihaan.isrentalapp.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class NewDeviceCreator {

    private final DevicesRepositoryFacade devicesRepositoryFacade;

    @Autowired
    public NewDeviceCreator(DevicesRepositoryFacade devicesRepositoryFacade) {
        this.devicesRepositoryFacade = devicesRepositoryFacade;
    }

    /**
     * Method saving computer object to database after validate all properties
     * @param computer is an object which will be converted to db entity
     * @return HttpStatusEnum with status depending on result of insert entity to DB,
     *      can return BAD_REQUEST if result has errors
     *      CONFLICT if entity has already exists
     *      CREATED if entity was saved in DB
     */
    HttpStatusEnum addNewComputer(Computer computer){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Computer>> validationErrors = validator.validate(computer);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        if(devicesRepositoryFacade.getComputerRepository().findByOtnumber(computer.getOtNumber());
            return HttpStatusEnum.CONFLICT;

        devicesRepositoryFacade.getComputerRepository().save(computer);
        return HttpStatusEnum.CREATED;
    }
}
