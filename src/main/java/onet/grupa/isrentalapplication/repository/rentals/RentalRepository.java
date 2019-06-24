package onet.grupa.isrentalapplication.repository.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<ComputerRentals, Long> {

}
