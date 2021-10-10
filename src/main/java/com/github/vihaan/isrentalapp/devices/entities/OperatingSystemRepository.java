package com.github.vihaan.isrentalapp.devices.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface OperatingSystemRepository extends JpaRepository<OperatingSystemEntity, Long> {

    OperatingSystemEntity findByOperatingSystem(String operatingSystem);
}
