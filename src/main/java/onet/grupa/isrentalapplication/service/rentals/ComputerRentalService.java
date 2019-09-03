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
    public ComputerRentalService(ComputerRentalRepository computerRentalRepository,
                                 ComputerRentalSearchingService computerRentalSearchingService) {
        this.computerRentalRepository = computerRentalRepository;
        this.computerRentalSearchingService = computerRentalSearchingService;
    }

    public List<ComputerRental> getComputerRentals(String searchPattern, String orderBy){
        if(searchPattern != null && !searchPattern.isEmpty())
            return getAllComputerRentals();
        else
            return getSpecificComputerRentals(searchPattern, orderBy);

    }

    private List<ComputerRental> getSpecificComputerRentals(String searchPattern, String orderBy){
        return computerRentalSearchingService.getWithSearchingAndOrder(searchPattern,orderBy);
    }

    private List<ComputerRental> getAllComputerRentals(){
        return computerRentalRepository.findAll();
    }

    public List<ComputerRental> getAllComputerRentalsWithStatus (String status){
            return computerRentalRepository.findAllByRentStatus_Status(status);
        }
}

