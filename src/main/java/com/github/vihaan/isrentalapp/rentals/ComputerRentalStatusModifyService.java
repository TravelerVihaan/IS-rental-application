package com.github.vihaan.isrentalapp.rentals;

import com.github.vihaan.isrentalapp.devices.oldies.ComputerService;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalRepository;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalEntity;
import com.github.vihaan.isrentalapp.service.HttpStatusEnum;
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
        ComputerRentalEntity computerRentalEntity = computerRentalRepository.findById(id).orElseThrow();
        HttpStatusEnum httpStatus = changeComputerStatus(computerRentalEntity, status);
        if(httpStatus.equals(HttpStatusEnum.OK)) {
            computerRentalEntity.setRentStatus(rentStatusService.getStatusByName(status).orElseThrow());
            computerRentalRepository.save(computerRentalEntity);
        }
        return httpStatus;
    }

    private HttpStatusEnum changeComputerStatus(ComputerRentalEntity cr, String status){
        HttpStatusEnum httpStatus = HttpStatusEnum.NOTFOUND;
        if("finalize".equalsIgnoreCase(status))
            httpStatus = computerService.changeComputerStatus("available",cr.getRentedComputer().getId());
        else if("accept".equalsIgnoreCase(status))
            httpStatus = computerService.changeComputerStatus("rented",cr.getRentedComputer().getId());
        return httpStatus;
    }
}
