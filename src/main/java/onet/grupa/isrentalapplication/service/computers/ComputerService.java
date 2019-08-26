package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    private ComputerRepository computerRepository;
    private Validator validator;

    @Autowired
    public ComputerService(ComputerRepository computerRepository, Validator validator){
        this.computerRepository = computerRepository;
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



    public ResponseEntity<?> changeComputerStatus(String status, Long id){
        if(getComputer(id).isPresent()) {
            getComputer(id).ifPresent(computer -> updateComputerStatus(computer, status));
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    /*
    Private methods to get computers from repo
     */
    private void updateComputerStatus(Computer computer, String status){
        computer.getComputerStatus().setStatus(status);
        computerRepository.save(computer);
    }

}
