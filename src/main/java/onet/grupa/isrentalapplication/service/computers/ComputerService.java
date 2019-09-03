package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.ISearching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ComputerService {

    private ComputerRepository computerRepository;
    private ISearching<Computer> computerSearchingService;
    private Validator validator;

    @Autowired
    public ComputerService(ComputerRepository computerRepository,
                           ComputerSearchingService computerSearchingService,
                           Validator validator){
        this.computerRepository = computerRepository;
        this.computerSearchingService = computerSearchingService;
        this.validator = validator;
    }

    /*
   Public methods
    */

    /**
     * Return simple response with list of all found Computer in database.
     *
     * @return List of Computers
     */
    public List<Computer> getAllComputers(){
        return computerRepository.findAll();
    }

    public List<Computer> getSpecificComputers(String searchPattern, String orderBy){
        return computerSearchingService.getWithSearchingAndOrder(searchPattern, orderBy);
    }
    /**
     * Return Optional with found Computer in database.
     *
     * @param id id of Computer entity
     *
     * @return Optional with Computer
     */
    public Optional<Computer> getComputer(long id){
        return computerRepository.findById(id);
    }

    /**
     * Method saving computer object to database after validate all properties
     * @param computer is an object which will be converted to db entity
     * @return HttpStatusEnum with status depending on result of insert entity to DB,
     *      can return BAD_REQUEST if result has errors
     *      CONFLICT if entity has already exists
     *      CREATED if entity was saved in DB
     */
    public HttpStatusEnum addNewComputer(Computer computer){
        Set<ConstraintViolation<Computer>> validationErrors = validator.validate(computer);
        if(!validationErrors.isEmpty())
            return HttpStatusEnum.BADREQUEST;
        if(getComputerByOT(computer.getOtnumber()).isPresent())
            return HttpStatusEnum.CONFLICT;

        computerRepository.save(computer);
        return HttpStatusEnum.CREATED;
    }

    public ResponseEntity<?> changeComputerStatus(String status, Long id){
        if(getComputer(id).isPresent()) {
            getComputer(id).ifPresent(computer -> updateComputerStatus(computer, status));
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /**
     *  Private method returning optional with computer.
     *  It exists to check duplicated gear in magazine
     * @param OT OT number which is unique for every computer
     * @return Optional with null or found computer
     */
    public Optional<Computer> getComputerByOT(String OT){
        return Optional.ofNullable(computerRepository.findByOtnumber(OT));
    }

    /*
    Private methods to get computers from repo
     */
    private void updateComputerStatus(Computer computer, String status){
        computer.getComputerStatus().setStatus(status);
        computerRepository.save(computer);
    }

    public HttpStatusEnum deleteComputer(Long id){
        if(computerRepository.findById(id).isPresent()){
            computerRepository.deleteById(id);
            return HttpStatusEnum.OK;
        }
        return HttpStatusEnum.NOTFOUND;
    }

}
