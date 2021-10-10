package com.github.vihaan.isrentalapp.devices.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DevicesRepositoryFacade {

    private final ComputerRepository computerRepository;

    @Autowired
    public DevicesRepositoryFacade(ComputerRepository computerRepository) {
        this.computerRepository = computerRepository;
    }

    public ComputerRepository getComputerRepository() {
        return computerRepository;
    }
}
