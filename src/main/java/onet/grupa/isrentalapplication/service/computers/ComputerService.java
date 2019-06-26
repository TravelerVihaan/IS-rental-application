package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComputerService {

    private ComputerRepository computerRepository;

    public ComputerService(ComputerRepository computerRepository){
        this.computerRepository = computerRepository;
    }

    public List<Computer> getAllComputers(){
        return computerRepository.findAll();
    }

    public Computer findComputerById(long id) {
        if (computerRepository.findById(id).isPresent())
            return computerRepository.findById(id).get();
        else
            return new Computer();
    }

    public Computer updateComputer(long id, Computer computer){
        return null;
    }
}
