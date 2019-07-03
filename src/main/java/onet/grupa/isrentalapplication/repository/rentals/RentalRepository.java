package onet.grupa.isrentalapplication.repository.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<ComputerRental, Long> {

}
