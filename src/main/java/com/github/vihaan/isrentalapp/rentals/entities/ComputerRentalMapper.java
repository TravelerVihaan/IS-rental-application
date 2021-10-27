package com.github.vihaan.isrentalapp.rentals.entities;

import com.github.vihaan.isrentalapp.rentals.ComputerRental;
import com.github.vihaan.isrentalapp.rentals.RentStatus;

class ComputerRentalMapper {

    ComputerRental convertToDomainObject(ComputerRentalEntity computerRentalEntity) {
        ComputerRental computerRental = new ComputerRental(computerRentalEntity.getStartRentalDate(),
                computerRentalEntity.getEndRentalDate(),
                computerRentalEntity.getRentingPersonEmail(),
                computerRentalEntity.getRentingPersonName(),
                computerRentalEntity.getRentedComputer(),
                RentStatus.fromString(computerRentalEntity.getRentStatus().getStatus()),
                computerRentalEntity.getWhoSetStatus());
        return computerRental;
    }
}
