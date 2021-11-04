package com.github.vihaan.isrentalapp.rentals.entities;

import com.github.vihaan.isrentalapp.devices.entities.ComputerMapper;
import com.github.vihaan.isrentalapp.rentals.ComputerRental;
import com.github.vihaan.isrentalapp.util.DomainObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerRentalMapper implements DomainObjectMapper<ComputerRentalEntity, ComputerRental> {

    private final ComputerMapper computerMapper;
    private final RentStatusMapper rentStatusMapper;

    @Autowired
    public ComputerRentalMapper(ComputerMapper computerMapper,
                                RentStatusMapper rentStatusMapper) {
        this.computerMapper = computerMapper;
        this.rentStatusMapper = rentStatusMapper;
    }

    @Override
    public ComputerRentalEntity convertToEntity(ComputerRental computerRental) {
        return new ComputerRentalEntity(computerRental.getStartRentalDate(),
                computerRental.getEndRentalDate(),
                computerRental.getRentingPersonEmail(),
                computerRental.getRentingPersonName(),
                computerMapper.convertToEntity(computerRental.getRentedComputer()),
                rentStatusMapper.convertToEntity(computerRental.getRentStatus()));
    }

    @Override
    public ComputerRental convertToDomainObject(ComputerRentalEntity computerRentalEntity) {
        return new ComputerRental(computerRentalEntity.getStartRentalDate(),
                computerRentalEntity.getEndRentalDate(),
                computerRentalEntity.getRentingPersonEmail(),
                computerRentalEntity.getRentingPersonName(),
                computerMapper.convertToDomainObject(computerRentalEntity.getRentedComputer()),
                rentStatusMapper.convertToDomainObject(computerRentalEntity.getRentStatus()),
                null);
        //TODO USER
    }
}
