package com.github.vihaan.isrentalapp.rentals.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface RentStatusRepository extends JpaRepository<RentStatusEntity, Long> {

    Optional<RentStatusEntity> findByStatus(String status);
}
