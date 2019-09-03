package onet.grupa.isrentalapplication.repository.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

    Computer findByOtnumber(String OTNumber);

    List<Computer> findAllByOtnumberContaining(String searchPhrase);

    List<Computer> findAllByComputerModel_ModelContaining(String searchPhrase);

    List<Computer> findAllByComputerModel_ComputerProducer_ProducerNameContaining(String searchPhrase);

    List<Computer> findAllBySerialNumberContaining(String searchPhrase);
}
