package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.ISearching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerRentalService {

    private ComputerRentalRepository computerRentalRepository;
    private NewComputerRentalService newComputerRentalService;
    private ISearching<ComputerRental> computerRentalSearchingService;
    private ComputerRentalStatusModifyService computerRentalStatusModifyService;

    @Autowired
    public ComputerRentalService(ComputerRentalRepository computerRentalRepository,
                                 ComputerRentalSearchingService computerRentalSearchingService,
                                 NewComputerRentalService newComputerRentalService,
                                 ComputerRentalStatusModifyService computerRentalStatusModifyService) {
        this.computerRentalRepository = computerRentalRepository;
        this.computerRentalSearchingService = computerRentalSearchingService;
        this.newComputerRentalService = newComputerRentalService;
        this.computerRentalStatusModifyService = computerRentalStatusModifyService;
    }

    public List<ComputerRental> getComputerRentals(String searchPattern, String orderBy){
        if(searchPattern != null && !searchPattern.isEmpty())
            return getAllComputerRentals();
        else
            return getSpecificComputerRentals(searchPattern, orderBy);

    }

    public HttpStatusEnum addNewComputerRental(ComputerRental computerRental){
        return newComputerRentalService.addNewComputerRental(computerRental);
    }

    public List<ComputerRental> getAllComputerRentalsWithStatus (String status){
        return computerRentalRepository.findAllByRentStatus_Status(status);
    }

    public HttpStatusEnum changeRentalStatus(Long id, String status){
        computerRentalStatusModifyService.changeRentalStatus(id, status);
        return HttpStatusEnum.OK;
    }

    private List<ComputerRental> getSpecificComputerRentals(String searchPattern, String orderBy){
        return computerRentalSearchingService.getWithSearchingAndOrder(searchPattern,orderBy);
    }

    private List<ComputerRental> getAllComputerRentals(){
        return computerRentalRepository.findAll();
    }
}

