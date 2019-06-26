package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.repository.computers.DiskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiskTypeService {

    private DiskTypeRepository diskTypeRepository;

    @Autowired
    public DiskTypeService(DiskTypeRepository diskTypeRepository){ this.diskTypeRepository = diskTypeRepository; }

    public ResponseEntity<List<DiskType>> getResponseWithAllDisks(){
        return ResponseEntity.of(getAllDisks());
    }

    public ResponseEntity<DiskType> getResponseWithDisk(long id){
        return ResponseEntity.of(getDiskType(id));
    }

    private Optional<DiskType> getDiskType(long id){
            return diskTypeRepository.findById(id);
    }

    private Optional<List<DiskType>> getAllDisks(){
        return Optional.ofNullable(diskTypeRepository.findAll());
    }
}
