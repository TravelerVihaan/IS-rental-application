package onet.grupa.isrentalapplication.repository.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiskTypeRepository extends JpaRepository<DiskType, Long> {
}
