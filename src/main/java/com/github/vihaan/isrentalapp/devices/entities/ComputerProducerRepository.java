package com.github.vihaan.isrentalapp.devices.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface ComputerProducerRepository extends JpaRepository<ComputerProducerEntity, Long> {

    Optional<ComputerProducerEntity> findByProducerName(String producerName);
}
