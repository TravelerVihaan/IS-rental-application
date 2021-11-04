package com.github.vihaan.isrentalapp.devices.entities;

import com.github.vihaan.isrentalapp.devices.Computer;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalMapper;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ComputerMapper implements DomainObjectMapper<ComputerEntity, Computer> {

    private final OperatingSystemMapper operatingSystemMapper;
    private final DiskTypeMapper diskTypeMapper;
    private final ComputerModelMapper computerModelMapper;
    private final ComputerRentalMapper computerRentalMapper;
    private final ComputerStatusMapper computerStatusMapper;

    @Autowired
    public ComputerMapper(OperatingSystemMapper operatingSystemMapper,
                          DiskTypeMapper diskTypeMapper,
                          ComputerModelMapper computerModelMapper,
                          ComputerRentalMapper computerRentalMapper,
                          ComputerStatusMapper computerStatusMapper) {
        this.operatingSystemMapper = operatingSystemMapper;
        this.diskTypeMapper = diskTypeMapper;
        this.computerModelMapper = computerModelMapper;
        this.computerRentalMapper = computerRentalMapper;
        this.computerStatusMapper = computerStatusMapper;
    }

    @Override
    public ComputerEntity convertToEntity(Computer computer) {
        return new ComputerEntity(computer.getOtNumber(),
                computer.getSerialNumber(),
                operatingSystemMapper.convertToEntity(computer.getOperatingSystem()),
                diskTypeMapper.convertToEntity(computer.getDiskType()),
                computerModelMapper.convertToEntity(computer.getComputerModel()),
                computerStatusMapper.convertToEntity(computer.getComputerStatus()));
    }

    @Override
    public Computer convertToDomainObject(ComputerEntity computerEntity) {
        return new Computer(computerEntity.getOtnumber(),
                computerEntity.getSerialNumber(),
                operatingSystemMapper.convertToDomainObject(computerEntity.getOperatingSystem()),
                diskTypeMapper.convertToDomainObject(computerEntity.getDiskType()),
                computerModelMapper.convertToDomainObject(computerEntity.getComputerModel()),
                computerStatusMapper.convertToDomainObject(computerEntity.getComputerStatus()),
                computerEntity.getComputerRentals().stream()
                        .map(computerRentalMapper::convertToDomainObject).collect(Collectors.toList())
                );

    }
}
