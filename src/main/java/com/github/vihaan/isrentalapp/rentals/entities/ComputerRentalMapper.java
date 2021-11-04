package com.github.vihaan.isrentalapp.rentals.entities;

import com.github.vihaan.isrentalapp.devices.entities.ComputerMapper;
import com.github.vihaan.isrentalapp.rentals.ComputerRental;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComputerRentalMapper implements DomainObjectMapper<ComputerRentalEntity, com.github.vihaan.isrentalapp.rentals.ComputerRental> {

    private final ComputerMapper computerMapper;
    private final RentStatusMapper rentStatusMapper;

    @Autowired
    public ComputerRentalMapper(ComputerMapper computerMapper,
                                RentStatusMapper rentStatusMapper) {
        this.computerMapper = computerMapper;
        this.rentStatusMapper = rentStatusMapper;
    }

    @Override
    public ComputerRentalEntity convertToEntity(com.github.vihaan.isrentalapp.rentals.ComputerRental computerRental) {
        return new ComputerRentalEntity(computerRental.getStartRentalDate(),
                computerRental.getEndRentalDate(),
                computerRental.getRentingPersonEmail(),
                computerRental.getRentingPersonName(),
                computerMapper.convertToEntity(computerRental.getRentedComputer()),
                rentStatusMapper.convertToEntity(computerRental.getRentStatus()));
    }

    public List<ComputerRentalEntity> convertCollectionToEntities(Collection<ComputerRental> computerRentalsEntities) {
        return computerRentalsEntities.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }

    public List<ComputerRental> convertCollectionToDomainObjects(Collection<ComputerRentalEntity> computerRentalsEntities) {
        return computerRentalsEntities.stream()
                .map(this::convertToDomainObject)
                .collect(Collectors.toList());
    }

    @Override
    public ComputerRental convertToDomainObject(ComputerRentalEntity computerRental) {
        return new com.github.vihaan.isrentalapp.rentals.ComputerRental(computerRental.getStartRentalDate(),
                computerRental.getEndRentalDate(),
                computerRental.getRentingPersonEmail(),
                computerRental.getRentingPersonName(),
                computerMapper.convertToDomainObject(computerRental.getRentedComputer()),
                rentStatusMapper.convertToDomainObject(computerRental.getRentStatus()),
                null);
        //TODO USER
    }
}
