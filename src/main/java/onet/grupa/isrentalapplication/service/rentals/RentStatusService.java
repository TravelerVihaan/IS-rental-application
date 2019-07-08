package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.RentStatus;
import onet.grupa.isrentalapplication.repository.rentals.RentStatusRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RentStatusService {

    private RentStatusRepository rentStatusRepository;
    private Validator validator;

    @Autowired
    public RentStatusService(RentStatusRepository rentStatusRepository, Validator validator){
        this.rentStatusRepository = rentStatusRepository;
        this.validator = validator;
    }

    public Optional<List<RentStatus>> getAllStatuses(){
        return Optional.ofNullable(rentStatusRepository.findAll());
    }

    public Optional<RentStatus> getStatus(long id){
        return rentStatusRepository.findById(id);
    }

    /**
     * Add new RentStatus entity to database
     *
     * @param rentStatus Object of computer model generated from JSON incoming from front-end
     *
     * @return HttpStatusEnum with status depending on result of insert entity to DB,
     * can return BADREQUEST if result has errors
     *            CONFLICT if entity has already exists
     *            CREATED if entity was saved in DB
     *
     */
    public HttpStatusEnum addNewRentStatus(RentStatus rentStatus){
        Set<ConstraintViolation<RentStatus>> validationErrors = validator.validate(rentStatus);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        if(getStatusByName(rentStatus.getStatus()).isPresent())
            return HttpStatusEnum.CONFLICT;

        rentStatusRepository.save(rentStatus);
        return HttpStatusEnum.CREATED;
    }

    /*
    * Private methods
     */

    private Optional<RentStatus> getStatusByName(String status){
        return Optional.ofNullable(rentStatusRepository.findByStatus(status));
    }
}
