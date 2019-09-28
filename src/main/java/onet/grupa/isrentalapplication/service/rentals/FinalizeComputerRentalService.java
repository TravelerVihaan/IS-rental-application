package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinalizeComputerRentalService {

    private ComputerRentalRepository computerRentalRepository;
    private ComputerService computerService;
    private RentStatusService rentStatusService;

    @Autowired
    public FinalizeComputerRentalService(ComputerRentalRepository computerRentalRepository,
                                         ComputerService computerService,
                                         RentStatusService rentStatusService){
        this.computerRentalRepository = computerRentalRepository;
        this.computerService = computerService;
        this.rentStatusService = rentStatusService;
    }

    public void finalizeRental(ComputerRental cr){
        computerService.changeComputerStatus("available",cr.getRentedComputer().getId());
        ComputerRental computerRental = computerRentalRepository.findById(cr.getId()).orElseThrow();
        computerRental.setRentStatus(rentStatusService.getStatusByName("finalized").orElseThrow());
        computerRentalRepository.save(computerRental);
    }
}
