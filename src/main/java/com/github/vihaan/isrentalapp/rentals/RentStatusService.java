package com.github.vihaan.isrentalapp.rentals;

import com.github.vihaan.isrentalapp.rentals.entities.RentStatusRepository;
import com.github.vihaan.isrentalapp.rentals.entities.RentStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentStatusService {

    private final RentStatusRepository rentStatusRepository;

    @Autowired
    public RentStatusService(RentStatusRepository rentStatusRepository){
        this.rentStatusRepository = rentStatusRepository;
    }

    public List<RentStatusEntity> getAllStatuses(){
        return rentStatusRepository.findAll();
    }

    public Optional<RentStatusEntity> getStatus(long id){
        return rentStatusRepository.findById(id);
    }

    Optional<RentStatusEntity> getStatusByName(String status){
        return rentStatusRepository.findByStatus(status);
    }
}
