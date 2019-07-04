package onet.grupa.isrentalapplication.repository.computers;

import onet.grupa.isrentalapplication.domain.computers.OperatingSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Long> {

    OperatingSystem findByOperatingSystem(String operatingSystem);
}
