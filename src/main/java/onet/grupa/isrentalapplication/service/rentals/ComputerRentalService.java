package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
     * @return Optional with list
     */
    public Optional<List<ComputerRental>> getAllComputerRentals() {
        return Optional.ofNullable(computerRentalRepository.findAll());
    }

    /**
     * Return simple response with found ComputerRentals in database.
     * Method is searching by email, name of renting person, by computer model
     * and computer producer.
     *
     * @param searchPhrase pattern using to search in DB
     * @return Optional with List with ComputerRentals found in DB
     */
    public Optional<List<ComputerRental>> getComputerRentalsWithSearching(String searchPhrase) {
        List<ComputerRental> foundRentalsList = computerRentalRepository.findAllByRentingPersonemailContaining(searchPhrase);
        foundRentalsList.addAll(computerRentalRepository.findAllByRentingPersonNameContaining(searchPhrase));
        foundRentalsList.addAll(getRentalsByProducer(searchPhrase));
        foundRentalsList.addAll(getRentalsByModel(searchPhrase));
        return Optional.of(foundRentalsList);
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

