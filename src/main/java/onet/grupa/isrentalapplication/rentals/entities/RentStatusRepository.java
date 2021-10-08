package onet.grupa.isrentalapplication.rentals.entities;

import onet.grupa.isrentalapplication.rentals.entities.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentStatusRepository extends JpaRepository<RentStatus, Long> {

    Optional<RentStatus> findByStatus(String status);
}
