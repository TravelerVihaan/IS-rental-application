package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ComputerRentalService {

    private ComputerRentalRepository computerRentalRepository;

    @Autowired
    public ComputerRentalService(ComputerRentalRepository computerRentalRepository) {
        this.computerRentalRepository = computerRentalRepository;
    }

    /**
     * Return simple response with list of all found ComputerRentals in database.
     *
     * @return List with rentals
     */
    public List<ComputerRental> getAllComputerRentals() {
        return computerRentalRepository.findAll();
    }

    /**
     * Return simple response with found ComputerRentals in database.
     * Method is searching by email, name of renting person, by computer model
     * and computer producer.
     *
     * @param searchPhrase pattern using to search in DB
     * @return Optional with List with ComputerRentals found in DB
     */
    public List<ComputerRental> getComputerRentalsWithSearchingAndOrder(String searchPhrase, String orderBy) {
        Set<ComputerRental> foundRentals = getComputerRentalsWithSearching(searchPhrase);
        //TODO order and set to list
        return new ArrayList<>();
    }

    private Set<ComputerRental> getComputerRentalsWithSearching(String searchPhrase){
        Set<ComputerRental> foundRentals = new HashSet<>();
        foundRentals.addAll(computerRentalRepository.findAllByRentingPersonemailContaining(searchPhrase));
        foundRentals.addAll(computerRentalRepository.findAllByRentingPersonNameContaining(searchPhrase));
        foundRentals.addAll(getRentalsByProducer(searchPhrase));
        foundRentals.addAll(getRentalsByModel(searchPhrase));
        return foundRentals;
    }

        /**
         * Return response with found ComputerRental by status.
         *
         * @param status status of computer rental ( approved or rejected).
         *
         * @return Optional with list of Computer Rentals
         */
        public Optional<List<ComputerRental>> getAllComputerRentalsWithStatus (String status){
            return getAllComputerRentalsByStatus(status);
        }

    /*
    Private methods
     */
        private Optional<List<ComputerRental>> getAllComputerRentalsByStatus (String status){
            return Optional.ofNullable(computerRentalRepository.findAllByRentStatus_Status(status));
        }

        private List<ComputerRental> getRentalsByProducer (String searchPhrase){
            return computerRentalRepository
                    .findAllByRentedComputer_ComputerModel_ComputerProducer_ProducerNameContaining(searchPhrase);
        }

        private List<ComputerRental> getRentalsByModel (String searchPhrase){
            return computerRentalRepository
                    .findAllByRentedComputer_ComputerModel_ModelContaining(searchPhrase);
        }
}

