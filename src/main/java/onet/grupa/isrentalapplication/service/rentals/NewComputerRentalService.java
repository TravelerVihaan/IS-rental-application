package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@Service
public class NewComputerRentalService {

    private ComputerRentalRepository computerRentalRepository;
    private ComputerService computerService;
    private Validator validator;

    @Autowired
    public NewComputerRentalService(ComputerRentalRepository computerRentalRepository,
                                    ComputerService computerService,
                                    Validator validator){
        this.computerRentalRepository = computerRentalRepository;
        this.computerService = computerService;
        this.validator = validator;
    }

    public HttpStatusEnum addNewComputerRental(ComputerRental computerRental){
        if(!isComputerRentalCorrect(computerRental))
            return HttpStatusEnum.BADREQUEST;
        if(!isOrderedComputerAvailable(computerRental.getRentedComputer()))
            return HttpStatusEnum.CONFLICT;
        return null;
    }

    private boolean isComputerRentalCorrect(ComputerRental computerRental){
        Set<ConstraintViolation<ComputerRental>> validationErrors = validator.validate(computerRental);
        if(!validationErrors.isEmpty())
            return false;
        return true;
    }

    private boolean isOrderedComputerAvailable(Computer computer){
        String computerStatus = computerService.getComputerByOT(computer.getOtnumber())
                .map(computer1 -> computer1.getComputerStatus().getStatus())
                .orElseGet(() -> {return "unavailable";});
        return "available".equalsIgnoreCase(computerStatus);
    }
}
