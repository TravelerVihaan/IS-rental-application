package com.github.vihaan.isrentalapp.rentals;

import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalRepository;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalEntity;
import com.github.vihaan.isrentalapp.service.HttpStatusEnum;
import com.github.vihaan.isrentalapp.service.ISearching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerRentalService {

    private ComputerRentalRepository computerRentalRepository;
    private NewComputerRentalService newComputerRentalService;
    private ISearching<ComputerRentalEntity> computerRentalSearchingService;
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

    public List<ComputerRentalEntity> getComputerRentals(String searchPattern, String orderBy){
        if(searchPattern != null && !searchPattern.isEmpty())
            return getAllComputerRentals();
        else
            return getSpecificComputerRentals(searchPattern, orderBy);

    }

    public HttpStatusEnum addNewComputerRental(ComputerRentalEntity computerRentalEntity){
        return newComputerRentalService.addNewComputerRental(computerRentalEntity);
    }

    public List<ComputerRentalEntity> getAllComputerRentalsWithStatus (String status){
        return computerRentalRepository.findAllByRentStatus_Status(status);
    }

    public HttpStatusEnum changeRentalStatus(Long id, String status){
        computerRentalStatusModifyService.changeRentalStatus(id, status);
        return HttpStatusEnum.OK;
    }

    private List<ComputerRentalEntity> getSpecificComputerRentals(String searchPattern, String orderBy){
        return computerRentalSearchingService.getWithSearchingAndOrder(searchPattern,orderBy);
    }

    private List<ComputerRentalEntity> getAllComputerRentals(){
        return computerRentalRepository.findAll();
    }
}

