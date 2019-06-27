package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.OperatingSystem;
import onet.grupa.isrentalapplication.repository.computers.OperatingSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class OperatingSystemService {

    private OperatingSystemRepository operatingSystemRepository;

    @Autowired
    public OperatingSystemService(OperatingSystemRepository operatingSystemRepository){
        this.operatingSystemRepository = operatingSystemRepository;
    }

    /*
    Public methods
     */
    public ResponseEntity<List<OperatingSystem>> getResponseWithAllOperatingSystems(){
        return ResponseEntity.of(getAllOperatingSystems());
    }

    public ResponseEntity<OperatingSystem> getResponseWithOperatingSystem(long id){
        return ResponseEntity.of(getOperatingSystem(id));
    }

    /*
     * This function add new Disk Type to database
     * @param diskType
     * @param binding result
     */
    public ResponseEntity<?> addNewOperatingSystem(OperatingSystem operatingSystem, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(getAllOperatingSystems().isPresent()){
            if(getAllOperatingSystems()
                    .get()
                    .stream()
                    .anyMatch(os -> os.getOperatingSystem()
                            .equalsIgnoreCase(operatingSystem.getOperatingSystem())))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        operatingSystemRepository.save(operatingSystem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    Private methods
     */
    private Optional<OperatingSystem> getOperatingSystem(long id){
        return operatingSystemRepository.findById(id);
    }

    private Optional<List<OperatingSystem>> getAllOperatingSystems(){
        return Optional.ofNullable(operatingSystemRepository.findAll());
    }
}
