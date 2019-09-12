package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
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

    @Autowired
    public NewComputerRentalService(ComputerRentalRepository computerRentalRepository,
                                    ComputerService computerService){
        this.computerRentalRepository = computerRentalRepository;
        this.computerService = computerService;
    }

    HttpStatusEnum addNewComputerRental(ComputerRental computerRental){
        try{
            computerRental.
                    setRentedComputer(setCorrectComputer(computerRental));
        }catch(NullPointerException e){
            return HttpStatusEnum.BADREQUEST;
        }

        if(!isComputerRentalCorrect(computerRental))
            return HttpStatusEnum.BADREQUEST;
        if(!isComputerRentAvailable(computerRental))
            return HttpStatusEnum.CONFLICT;
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
                .findAllByRentedComputer_OtnumberAndEndRentalDateIsAfter(OT,date));
    }

    private Computer setCorrectComputer(ComputerRental cr) throws NullPointerException{
        return computerService.getComputerByOT(cr.getRentedComputer().getOtnumber())
                        .orElseThrow();
    }
}
