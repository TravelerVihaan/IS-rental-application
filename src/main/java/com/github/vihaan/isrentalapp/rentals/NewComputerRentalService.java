package com.github.vihaan.isrentalapp.rentals;

import com.github.vihaan.isrentalapp.devices.oldies.ComputerService;
import com.github.vihaan.isrentalapp.devices.entities.ComputerEntity;
import com.github.vihaan.isrentalapp.devices.entities.ComputerStatusRepository;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalRepository;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRental;
import com.github.vihaan.isrentalapp.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class NewComputerRentalService {

    private ComputerRentalRepository computerRentalRepository;
    private ComputerService computerService;
    private ComputerStatusRepository computerStatusRepository;

    @Autowired
    public NewComputerRentalService(ComputerRentalRepository computerRentalRepository,
                                    ComputerService computerService,
                                    ComputerStatusRepository computerStatusRepository){
        this.computerRentalRepository = computerRentalRepository;
        this.computerService = computerService;
        this.computerStatusRepository = computerStatusRepository;
    }

    HttpStatusEnum addNewComputerRental(ComputerRental computerRental){
        try{
            computerRental.
                    setRentedComputer(getCorrectComputer(computerRental));
        }catch(NullPointerException e){
            System.err.println("This computer does not exist");
            return HttpStatusEnum.BADREQUEST;
        }
        if(!isComputerRentalCorrect(computerRental))
            return HttpStatusEnum.BADREQUEST;
        if(!isComputerRentAvailable(computerRental))
            return HttpStatusEnum.CONFLICT;
        computerRental.setRentedComputer(changeComputerRentStatus(getCorrectComputer(computerRental)));
        computerRentalRepository.save(computerRental);
        return HttpStatusEnum.CREATED;
    }

    private boolean isComputerRentalCorrect(ComputerRental cr){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<ComputerRental>> validationErrors = validator.validate(cr);
        if(!validationErrors.isEmpty())
            return false;
        if(cr.getStartRentalDate().isAfter(cr.getEndRentalDate()))
            return false;
        return !cr.getStartRentalDate().isBefore(LocalDate.now());
    }

    private boolean isComputerRentAvailable(ComputerRental crent){
        Set<ComputerRental> rentals = getRentalsOfComputer(crent.getRentedComputer().getOtnumber(),
                crent.getStartRentalDate());
        Stream<LocalDate> dates = crent.getStartRentalDate().datesUntil(crent.getEndRentalDate().plusDays(1));
        List<LocalDate> rentRange = dates.collect(Collectors.toList());
        for(ComputerRental cr: rentals) {
            if (rentRange.stream()
                    .anyMatch(date -> date.isAfter(cr.getStartRentalDate()) && date.isBefore(cr.getEndRentalDate())))
                return false;
        }
        return true;
    }

    private Set<ComputerRental> getRentalsOfComputer(String OT, LocalDate date){
        return new HashSet<>(computerRentalRepository
                .findAllByRentedComputer_OtnumberAndEndRentalDateIsAfterAndRentStatus_Status(OT,date,"accepted"));
    }

    private ComputerEntity changeComputerRentStatus(ComputerEntity computerEntity){
        computerEntity.setComputerStatus(computerStatusRepository.findByStatus("rented"));
        computerService.saveComputerToDB(computerEntity);
        return computerEntity;
    }

    private ComputerEntity getCorrectComputer(ComputerRental cr) throws NullPointerException{
        return computerService.getComputerByOT(cr.getRentedComputer().getOtnumber())
                        .orElseThrow();
    }
}
