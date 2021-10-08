package onet.grupa.isrentalapplication.devices.entities;

import onet.grupa.isrentalapplication.devices.entities.ComputerProducer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComputerProducerRepository extends JpaRepository<ComputerProducer, Long> {

    Optional<ComputerProducer> findByProducerName(String producerName);
}
