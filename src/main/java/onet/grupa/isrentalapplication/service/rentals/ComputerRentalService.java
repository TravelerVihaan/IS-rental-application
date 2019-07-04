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

    public static final String STATUS_AVAILABLE = "available";
    public static final String STATUS_UNAVAILABLE = "unavailable";

    @Autowired
    public ComputerRentalService(ComputerRentalRepository computerRentalRepository){
        this.computerRentalRepository = computerRentalRepository;
    }

    public ResponseEntity<List<ComputerRental>> getResponseWithComputerRentals(){
        return ResponseEntity.of(getAllComputerRentals());
    }

    public ResponseEntity<List<ComputerRental>> getResponseWithComputerRentalsAndSearching(String searchPhrase){
        List<ComputerRental> foundRentalsList = computerRentalRepository.findAllByRentingPersonemailContaining(searchPhrase);
        foundRentalsList.addAll(computerRentalRepository.findAllByRentingPersonNameContaining(searchPhrase));
        foundRentalsList.addAll(getRentalsByProducer(searchPhrase));
        foundRentalsList.addAll(getRentalsByModel(searchPhrase));
        return ResponseEntity.ok(foundRentalsList);
    }

    public ResponseEntity<List<ComputerRental>> getResponseWithComputerRentalsAndStatus(String status){
        return ResponseEntity.of(getAllComputerRentalsByStatus(status));
    }

    /*
    Private methods
     */

    private Optional<List<ComputerRental>> getAllComputerRentalsByStatus(String status){
        return Optional.ofNullable(computerRentalRepository.findAllByRentStatus_Status(status));
    }

    private Optional<List<ComputerRental>> getAllComputerRentals(){
        return Optional.ofNullable(computerRentalRepository.findAll());
    }

    private List<ComputerRental> getRentalsByProducer(String searchPhrase){
        return computerRentalRepository
                .findAllByRentedComputer_ComputerModel_ComputerProducer_ProducerNameContaining(searchPhrase);
    }

    private List<ComputerRental> getRentalsByModel(String searchPhrase) {
        return computerRentalRepository
                .findAllByRentedComputer_ComputerModel_ModelContaining(searchPhrase);
    }
}
