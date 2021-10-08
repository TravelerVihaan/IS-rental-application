package onet.grupa.isrentalapplication.devices.entities;

import onet.grupa.isrentalapplication.devices.entities.OperatingSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Long> {

    OperatingSystem findByOperatingSystem(String operatingSystem);
}
