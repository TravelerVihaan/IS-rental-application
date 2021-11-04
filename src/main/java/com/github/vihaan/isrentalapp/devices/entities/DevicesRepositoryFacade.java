package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevicesRepositoryFacade {

    private final ComputerRepository computerRepository;
    private final ComputerMapper computerMapper;

    @Autowired
    public DevicesRepositoryFacade(ComputerRepository computerRepository,
                                   ComputerMapper computerMapper) {
        this.computerRepository = computerRepository;
        this.computerMapper = computerMapper;
    }

    public ComputerRepository getComputerRepository() {
        return computerRepository;
    }

    public void saveComputerToRepository(Computer computer){
        ComputerEntity computerEntity = computerMapper.convertToEntity(computer);
        computerRepository.save(computerEntity);
    }
}
