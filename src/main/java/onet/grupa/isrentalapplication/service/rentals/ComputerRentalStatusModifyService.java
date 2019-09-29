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

    void finalizeRental(long id){
        ComputerRental computerRental = computerRentalRepository.findById(id).orElseThrow();
        computerService.changeComputerStatus("available",computerRental.getRentedComputer().getId());
        computerRental.setRentStatus(rentStatusService.getStatusByName("finalized").orElseThrow());
        computerRentalRepository.save(computerRental);
    }

    void rejectRental(long id){
        ComputerRental computerRental = computerRentalRepository.findById(id).orElseThrow();
        computerRental.setRentStatus(rentStatusService.getStatusByName("rejected").orElseThrow());
        computerRentalRepository.save(computerRental);
    }
}
