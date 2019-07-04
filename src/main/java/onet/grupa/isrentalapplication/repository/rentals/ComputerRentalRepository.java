package onet.grupa.isrentalapplication.repository.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComputerRentalRepository extends JpaRepository<ComputerRental, Long> {

    List<ComputerRental> findAllByRentingPersonemailContaining(String pattern);

    List<ComputerRental> findAllByRentingPersonNameContaining(String pattern);

    List<ComputerRental> findAllByRentedComputer_ComputerModel_ModelContaining(String pattern);

    List<ComputerRental> findAllByRentedComputer_ComputerModel_ComputerProducer_ProducerNameContaining(String pattern);

    List<ComputerRental> findAllByRentStatus_Status(String status);

}