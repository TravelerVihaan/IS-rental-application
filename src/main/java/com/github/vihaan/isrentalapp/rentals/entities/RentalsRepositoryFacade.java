package com.github.vihaan.isrentalapp.rentals.entities;

import com.github.vihaan.isrentalapp.rentals.ComputerRental;
import com.github.vihaan.isrentalapp.rentals.IRentalConstants;
import org.apache.commons.collections4.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalsRepositoryFacade {

    private final ComputerRentalRepository computerRentalRepository;
    private final ComputerRentalMapper computerRentalMapper;

    @Autowired
    public RentalsRepositoryFacade(ComputerRentalRepository computerRentalRepository,
                                   ComputerRentalMapper computerRentalMapper) {
        this.computerRentalRepository = computerRentalRepository;
        this.computerRentalMapper = computerRentalMapper;
    }

    public List<ComputerRental> findRentalsToNotify(){
        return ListUtils.union(
                computerRentalRepository.findAllByRentStatus_StatusAndEndRentalDateAfter(IRentalConstants.RENT_STATUS_ACTIVE, LocalDate.now().minusDays(3)),
                computerRentalRepository.findAllByRentStatus_Status(IRentalConstants.RENT_STATUS_OVERDUE))
                .stream().map(computerRentalMapper::convertToDomainObject).collect(Collectors.toList());
    }
}
