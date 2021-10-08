package onet.grupa.isrentalapplication.devices.entities;

import onet.grupa.isrentalapplication.devices.entities.ComputerStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerStatusRepository extends JpaRepository<ComputerStatus, Long> {

    ComputerStatus findByStatus(String status);
}
