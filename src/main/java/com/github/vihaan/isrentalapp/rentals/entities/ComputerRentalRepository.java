package com.github.vihaan.isrentalapp.rentals.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
interface ComputerRentalRepository extends JpaRepository<ComputerRentalEntity, Long> {

    List<ComputerRentalEntity> findAllByRentingPersonEmailContaining(String pattern);

    List<ComputerRentalEntity> findAllByRentingPersonNameContaining(String pattern);

    List<ComputerRentalEntity> findAllByRentedComputer_ComputerModel_ModelContaining(String pattern);

    List<ComputerRentalEntity> findAllByRentedComputer_ComputerModel_ComputerProducer_ProducerNameContaining(String pattern);

    List<ComputerRentalEntity> findAllByRentStatus_Status(String status);

    List<ComputerRentalEntity> findAllByEndRentalDateAfter(LocalDate date);

}
