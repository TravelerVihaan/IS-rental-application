package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.repository.computers.DiskTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class DiskTypeService {

    private DiskTypeRepository diskTypeRepository;

    /*
    Constructor with Autowired DiskTypeRepo
     */
    @Autowired
    public DiskTypeService(DiskTypeRepository diskTypeRepository){ this.diskTypeRepository = diskTypeRepository; }

    /*
    Public methods
     */
    public ResponseEntity<List<DiskType>> getResponseWithAllDisks(){
        return ResponseEntity.of(getAllDisks());
    }

    public ResponseEntity<DiskType> getResponseWithDisk(long id){
        return ResponseEntity.of(getDiskType(id));
    }

    /*
    * This function add new Disk Type to database
    * @param diskType
    * @param binding result
     */
    public ResponseEntity<?> addNewDiskType(DiskType diskType, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(getAllDisks().isPresent()){
            if(isDiskTypeAlreadyExist(diskType))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        diskTypeRepository.save(diskType);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private boolean isDiskTypeAlreadyExist(DiskType diskType){
        return getAllDisks()
                .get()
                .stream()
                .anyMatch(disk -> disk.getDiskType()
                        .equalsIgnoreCase(diskType.getDiskType()));
    }

    /*
    Private methods
     */
    private Optional<DiskType> getDiskType(long id){
            return diskTypeRepository.findById(id);
    }

    private Optional<List<DiskType>> getAllDisks(){
        return Optional.ofNullable(diskTypeRepository.findAll());
    }

}
