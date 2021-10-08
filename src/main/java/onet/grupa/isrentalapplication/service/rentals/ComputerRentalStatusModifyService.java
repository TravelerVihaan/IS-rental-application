package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.rentals.entities.ComputerRental;
import onet.grupa.isrentalapplication.rentals.entities.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
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

    HttpStatusEnum changeRentalStatus(long id, String status){
        ComputerRental computerRental = computerRentalRepository.findById(id).orElseThrow();
        HttpStatusEnum httpStatus = changeComputerStatus(computerRental, status);
        if(httpStatus.equals(HttpStatusEnum.OK)) {
            computerRental.setRentStatus(rentStatusService.getStatusByName(status).orElseThrow());
            computerRentalRepository.save(computerRental);
        }
        return httpStatus;
    }

    private HttpStatusEnum changeComputerStatus(ComputerRental cr, String status){
        HttpStatusEnum httpStatus = HttpStatusEnum.NOTFOUND;
        if("finalize".equalsIgnoreCase(status))
            httpStatus = computerService.changeComputerStatus("available",cr.getRentedComputer().getId());
        else if("accept".equalsIgnoreCase(status))
            httpStatus = computerService.changeComputerStatus("rented",cr.getRentedComputer().getId());
        return httpStatus;
    }
}
