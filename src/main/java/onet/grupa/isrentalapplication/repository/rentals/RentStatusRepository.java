package onet.grupa.isrentalapplication.repository.rentals;

import onet.grupa.isrentalapplication.domain.rentals.RentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentStatusRepository extends JpaRepository<RentStatus, Long> {

    RentStatus findByStatus(String status);
}
