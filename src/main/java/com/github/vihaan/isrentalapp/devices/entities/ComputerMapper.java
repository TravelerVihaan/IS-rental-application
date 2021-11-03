package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.Computer;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerMapper implements DomainObjectMapper<ComputerEntity, Computer> {

    private final OperatingSystemMapper operatingSystemMapper;
    private final DiskTypeMapper diskTypeMapper;
    private final ComputerModelMapper computerModelMapper;

    @Autowired
    public ComputerMapper(OperatingSystemMapper operatingSystemMapper,
                          DiskTypeMapper diskTypeMapper,
                          ComputerModelMapper computerModelMapper) {
        this.operatingSystemMapper = operatingSystemMapper;
        this.diskTypeMapper = diskTypeMapper;
        this.computerModelMapper = computerModelMapper;
    }

    @Override
    public ComputerEntity convertToEntity(Computer domainObject) {
        return null;
    }

    @Override
    public Computer convertToDomainObject(ComputerEntity computerEntity) {
        Computer computer = new Computer(computerEntity.getOtnumber(),
                computerEntity.getSerialNumber(),
                operatingSystemMapper.convertToDomainObject(computerEntity.getOperatingSystem()),
                diskTypeMapper.convertToDomainObject(computerEntity.getDiskType()),
                computerModelMapper.convertToDomainObject(computerEntity.getComputerModel()),
                computerEntity.getComputerRentals()
                )

    }
}
