package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.RentStatus;
import onet.grupa.isrentalapplication.repository.rentals.RentStatusRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RentStatusService {

    private RentStatusRepository rentStatusRepository;

    @Autowired
    public RentStatusService(RentStatusRepository rentStatusRepository){
        this.rentStatusRepository = rentStatusRepository;
    }

    public List<RentStatus> getAllStatuses(){
        return rentStatusRepository.findAll();
    }

    public Optional<RentStatus> getStatus(long id){
        return rentStatusRepository.findById(id);
    }

    /*
    * Private methods
     */

    private Optional<RentStatus> getStatusByName(String status){
        return Optional.ofNullable(rentStatusRepository.findByStatus(status));
    }
}
