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
    /**
     * Return simple response with list of all found OperatingSystems in database.
     *
     * @return ResponseEntity with list and status (OK, or BAD_REQUEST)
     */
    public ResponseEntity<List<OperatingSystem>> getResponseWithAllOperatingSystems(){
        return ResponseEntity.of(getAllOperatingSystems());
    }

    /**
     * Return simple response with found OperatingSystem in database.
     *
     * @param id id of OperatingSystem entity
     *
     * @return ResponseEntity with OperatingSystem and status (OK, or BAD_REQUEST)
     */
    public ResponseEntity<OperatingSystem> getResponseWithOperatingSystem(long id){
        return ResponseEntity.of(getOperatingSystem(id));
    }

    /**
     * Add new OperatingSystem entity to database
     *
     * @param operatingSystem Object of computer model generated from JSON incoming from front-end
     * @param result BindingResult object with list of errors, if any exist.
     *
     * @return ResponseEntity with status depending on result of insert entity to DB,
     * can return BAD_REQUEST if result has errors
     *            CONFLICT if entity has already exists
     *            CREATED if entity was saved in DB
     *
     */
    public ResponseEntity<?> addNewOperatingSystem(OperatingSystem operatingSystem, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(getOperatingSystemByName(operatingSystem.getOperatingSystem()).isPresent())
                return new ResponseEntity<>(HttpStatus.CONFLICT);

        operatingSystemRepository.save(operatingSystem);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    Private methods
     */
    private Optional<OperatingSystem> getOperatingSystemByName(String operatingSystem){
        return Optional.ofNullable(operatingSystemRepository.findByOperatingSystem(operatingSystem));
    }

    private Optional<OperatingSystem> getOperatingSystem(long id){
        return operatingSystemRepository.findById(id);
    }

    private Optional<List<OperatingSystem>> getAllOperatingSystems(){
        return Optional.ofNullable(operatingSystemRepository.findAll());
    }
}
