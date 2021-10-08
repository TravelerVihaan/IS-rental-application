package com.github.vihaan.isrentalapp.devices.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ComputerStatusRepository extends JpaRepository<ComputerStatus, Long> {

    ComputerStatus findByStatus(String status);
}
