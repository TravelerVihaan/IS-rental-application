package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.ISearching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerRentalService {

    private ComputerRentalRepository computerRentalRepository;
    private ISearching<ComputerRental> computerRentalSearchingService;

    @Autowired
    public ComputerRentalService(ComputerRentalRepository computerRentalRepository,ComputerRentalSearchingService computerRentalSearchingService) {
        this.computerRentalRepository = computerRentalRepository;
        this.computerRentalSearchingService = computerRentalSearchingService;
    }

    public List<ComputerRental> getSpecificComputerRentals(String searchPattern, String orderBy){
        return computerRentalSearchingService.getWithSearchingAndOrder(searchPattern,orderBy);
    }

    /**
     * Return simple response with list of all found ComputerRentals in database.
     *
     * @return List with rentals
     */
    public List<ComputerRental> getAllComputerRentals(){
        return computerRentalRepository.findAll();
    }

    /**
     * Return response with found ComputerRental by status.
     *
     * @param status status of computer rental ( approved or rejected).
     *
     * @return Optional with list of Computer Rentals
    */
    public List<ComputerRental> getAllComputerRentalsWithStatus (String status){
            return computerRentalRepository.findAllByRentStatus_Status(status);
        }
}

