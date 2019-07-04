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

    /**
     * Return simple response with list of all found ComputerRentals in database.
     *
     * @return ResponseEntity with list and status (OK, or BAD_REQUEST)
     */
    public ResponseEntity<List<ComputerRental>> getResponseWithComputerRentals(){
        return ResponseEntity.of(getAllComputerRentals());
    }

    /**
     * Return simple response with found ComputerRentals in database.
     * Method is searching by email, name of renting person, by computer model
     * and computer producer.
     *
     * @param searchPhrase pattern using to search in DB
     *
     * @return ResponseEntity with ComputerRentals found in DB and status OK.
     */
    public ResponseEntity<List<ComputerRental>> getResponseWithComputerRentalsAndSearching(String searchPhrase){
        List<ComputerRental> foundRentalsList = computerRentalRepository.findAllByRentingPersonemailContaining(searchPhrase);
        foundRentalsList.addAll(computerRentalRepository.findAllByRentingPersonNameContaining(searchPhrase));
        foundRentalsList.addAll(getRentalsByProducer(searchPhrase));
        foundRentalsList.addAll(getRentalsByModel(searchPhrase));
        return ResponseEntity.ok(foundRentalsList);
    }

    /**
     * Return response with found ComputerRental by status.
     *
     * @param status status of computer rental ( approved or rejected).
     *
     * @return ResponseEntity with OperatingSystem and status (OK, or BAD_REQUEST)
     */
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
