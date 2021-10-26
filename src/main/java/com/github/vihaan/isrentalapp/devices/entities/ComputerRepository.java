package com.github.vihaan.isrentalapp.devices.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<ComputerEntity, Long> {

    Optional<ComputerEntity> findByOtnumber(String OTNumber);

    List<ComputerEntity> findAllByOtnumberContaining(String searchPhrase);

    List<ComputerEntity> findAllByComputerModel_ModelContaining(String searchPhrase);

    List<ComputerEntity> findAllByComputerModel_ComputerProducer_ProducerNameContaining(String searchPhrase);

    List<ComputerEntity> findAllBySerialNumberContaining(String searchPhrase);
}
