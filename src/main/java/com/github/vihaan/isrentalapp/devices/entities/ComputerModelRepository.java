package com.github.vihaan.isrentalapp.devices.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerModelRepository extends JpaRepository<ComputerModelEntity, Long> {

    ComputerModelEntity findByModelName(String modelName);
}
