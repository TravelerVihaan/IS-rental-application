package com.github.vihaan.isrentalapp.devices.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ComputerModelRepository extends JpaRepository<ComputerModel, Long> {

    ComputerModel findByModel(String modelName);
}
