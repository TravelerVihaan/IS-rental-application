package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.rentals.entities.RentStatus;
import onet.grupa.isrentalapplication.rentals.entities.RentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    Optional<RentStatus> getStatusByName(String status){
        return rentStatusRepository.findByStatus(status);
    }
}
