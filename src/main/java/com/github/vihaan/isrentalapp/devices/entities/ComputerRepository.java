package com.github.vihaan.isrentalapp.devices.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ComputerRepository extends JpaRepository<Computer, Long> {

    Computer findByOtnumber(String OTNumber);

    List<Computer> findAllByOtnumberContaining(String searchPhrase);

    List<Computer> findAllByComputerModel_ModelContaining(String searchPhrase);

    List<Computer> findAllByComputerModel_ComputerProducer_ProducerNameContaining(String searchPhrase);

    List<Computer> findAllBySerialNumberContaining(String searchPhrase);
}
