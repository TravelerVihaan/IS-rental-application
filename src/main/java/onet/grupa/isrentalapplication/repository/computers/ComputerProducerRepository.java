package onet.grupa.isrentalapplication.repository.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerProducerRepository extends JpaRepository<ComputerProducer, Long> {
}
