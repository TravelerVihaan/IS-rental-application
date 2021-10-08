package onet.grupa.isrentalapplication.devices.entities;

import onet.grupa.isrentalapplication.devices.entities.DiskType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiskTypeRepository extends JpaRepository<DiskType, Long> {

    DiskType findByDiskType(String diskType);
}
