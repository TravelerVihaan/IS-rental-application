package com.github.vihaan.isrentalapp.devices.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiskTypeRepository extends JpaRepository<DiskType, Long> {

    DiskType findByDiskType(String diskType);
}
