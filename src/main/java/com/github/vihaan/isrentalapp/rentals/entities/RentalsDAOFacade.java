package com.github.vihaan.isrentalapp.rentals.entities;

import com.github.vihaan.isrentalapp.rentals.ComputerRental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class RentalsDAOFacade {

    private final ComputerRentalRepository computerRentalRepository;
    private final ComputerRentalMapper computerRentalMapper;

    @Autowired
    public RentalsDAOFacade(ComputerRentalRepository computerRentalRepository,
                            ComputerRentalMapper computerRentalMapper) {
        this.computerRentalRepository = computerRentalRepository;
        this.computerRentalMapper = computerRentalMapper;
    }

    public List<ComputerRental> collectRentalsForNotifications(){
        return computerRentalMapper.convertCollectionToDomainObjects
                (computerRentalRepository.findAllByEndRentalDateAfter(LocalDate.now()));
    }
}
