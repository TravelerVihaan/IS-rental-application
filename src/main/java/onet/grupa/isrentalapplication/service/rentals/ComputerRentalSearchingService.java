package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ComputerRentalSearchingService {

    private ComputerRentalRepository computerRentalRepository;
    private ComputerRentalsOrderingService computerRentalsOrderingService;

    @Autowired
    public ComputerRentalSearchingService(ComputerRentalRepository computerRentalRepository,
                                          ComputerRentalsOrderingService computerRentalsOrderingService){
        this.computerRentalRepository = computerRentalRepository;
        this.computerRentalsOrderingService = computerRentalsOrderingService;
    }

    /**
     * Return list with found ComputerRentals in database.
     * Method is searching by email, name of renting person, by computer model
     * and computer producer.
     *
     * @param searchPhrase pattern using to search in DB
     * @return Optional with List with ComputerRentals found in DB
     */
    List<ComputerRental> getComputerRentalsWithSearchingAndOrder(String searchPhrase, String orderBy) {
        Set<ComputerRental> foundRentals = getComputerRentalsWithSearching(searchPhrase);
        return computerRentalsOrderingService.sortRentalsOrderingBy(foundRentals, orderBy);
    }

    /**
     * Taking Set of unique Computer Rentals objects from db, searching by pattern
     * @param searchPhrase search pattern using to find data in db
     * @return HashSet of found records
     */
    private Set<ComputerRental> getComputerRentalsWithSearching(String searchPhrase){
        Set<ComputerRental> foundRentals = new HashSet<>();
        foundRentals.addAll(computerRentalRepository.findAllByRentingPersonemailContaining(searchPhrase));
        foundRentals.addAll(computerRentalRepository.findAllByRentingPersonNameContaining(searchPhrase));
        foundRentals.addAll(getRentalsByProducer(searchPhrase));
        foundRentals.addAll(getRentalsByModel(searchPhrase));
        return foundRentals;
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
