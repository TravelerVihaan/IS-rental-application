package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevicesRepositoryFacade {

    private final ComputerRepository computerRepository;
    private final ComputerDomainObjectEntityConverter computerDomainObjectEntityConverter;

    @Autowired
    public DevicesRepositoryFacade(ComputerRepository computerRepository,
                                   ComputerDomainObjectEntityConverter computerDomainObjectEntityConverter) {
        this.computerRepository = computerRepository;
        this.computerDomainObjectEntityConverter = computerDomainObjectEntityConverter;
    }

    public ComputerRepository getComputerRepository() {
        return computerRepository;
    }

    public void saveComputerToRepository(Computer computer){
        ComputerEntity computerEntity = computerDomainObjectEntityConverter.convertToEntity(computer);
        computerRepository.save(computerEntity);
    }
}
