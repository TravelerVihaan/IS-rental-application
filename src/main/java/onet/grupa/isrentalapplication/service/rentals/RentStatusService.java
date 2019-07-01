package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.RentStatus;
import onet.grupa.isrentalapplication.repository.rentals.RentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class RentStatusService {

    private RentStatusRepository rentStatusRepository;

    @Autowired
    public RentStatusService(RentStatusRepository rentStatusRepository){
        this.rentStatusRepository = rentStatusRepository;
    }

    public ResponseEntity<List<RentStatus>> getResponseWithAllStatuses(){
        return ResponseEntity.of(getAllStatuses());
    }

    public ResponseEntity<RentStatus> getResponseWithStatus(long id){
        return ResponseEntity.of(getStatus(id));
    }

    public ResponseEntity<?> addNewRentStatus(RentStatus rentStatus, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(getStatusByName(rentStatus.getStatus()).isPresent())
            return new ResponseEntity<>(HttpStatus.CONFLICT);

        rentStatusRepository.save(rentStatus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*
    * Private methods
     */

    private Optional<List<RentStatus>> getAllStatuses(){
        return Optional.ofNullable(rentStatusRepository.findAll());
    }

    private Optional<RentStatus> getStatus(long id){
        return rentStatusRepository.findById(id);
    }

    private Optional<RentStatus> getStatusByName(String status){
        return Optional.ofNullable(rentStatusRepository.findByStatus(status));
    }
}
