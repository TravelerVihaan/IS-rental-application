package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerRentalStatusModifyService {

    private ComputerRentalRepository computerRentalRepository;
    private ComputerService computerService;
    private RentStatusService rentStatusService;

    @Autowired
    public ComputerRentalStatusModifyService(ComputerRentalRepository computerRentalRepository,
                                             ComputerService computerService,
                                             RentStatusService rentStatusService){
        this.computerRentalRepository = computerRentalRepository;
        this.computerService = computerService;
        this.rentStatusService = rentStatusService;
    }

    void changeRentalStatus(long id, String status){
        ComputerRental computerRental = computerRentalRepository.findById(id).orElseThrow();
        changeComputerStatus(computerRental, status);
        computerRental.setRentStatus(rentStatusService.getStatusByName(status).orElseThrow());
        computerRentalRepository.save(computerRental);
    }

    private void changeComputerStatus(ComputerRental cr, String status){
        if("finalize".equalsIgnoreCase(status))
            computerService.changeComputerStatus("available",cr.getRentedComputer().getId());
        else if("accept".equalsIgnoreCase(status))
            computerService.changeComputerStatus("rented",cr.getRentedComputer().getId());
    }
}
