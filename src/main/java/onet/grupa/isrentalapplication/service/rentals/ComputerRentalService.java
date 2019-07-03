package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerRentalService {

    private ComputerRentalRepository computerRentalRepository;

    @Autowired
    public ComputerRentalService(ComputerRentalRepository computerRentalRepository){
        this.computerRentalRepository = computerRentalRepository;
    }

    public ResponseEntity<List<ComputerRental>> getResponseWithComputerRentals(){
        return ResponseEntity.of(getAllComputerRentals());
    }

    public ResponseEntity<List<ComputerRental>> getResponseWithComputerRentalsAndSearching(String searchPhrase){
        return null;
    }

    /*
    Private methods
     */

    private Optional<List<ComputerRental>> getAllComputerRentals(){
        return Optional.ofNullable(computerRentalRepository.findAll());
    }
}
