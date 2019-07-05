package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {

    private ComputerRepository computerRepository;

    @Autowired
    public ComputerService(ComputerRepository computerRepository){
        this.computerRepository = computerRepository;
    }

    public ResponseEntity<Computer> getResponseWithComputer(long id){
        return ResponseEntity.of(getComputer(id));
    }

    public ResponseEntity<List<Computer>> getResponseWithAllComputers(){
        return ResponseEntity.of(getAllComputers());
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

    private Optional<Computer> getComputer(long id){ return computerRepository.findById(id);}

    private Optional<List<Computer>> getAllComputers(){
        return Optional.ofNullable(computerRepository.findAll());
    }
}
