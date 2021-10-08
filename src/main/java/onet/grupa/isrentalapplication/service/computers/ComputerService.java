package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.devices.entities.Computer;
import onet.grupa.isrentalapplication.devices.entities.ComputerRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import onet.grupa.isrentalapplication.service.ISearching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ComputerService {

    private ComputerRepository computerRepository;
    private ISearching<Computer> computerSearchingService;
    private ComputerUpdateService computerUpdateService;
    private ComputerAddService computerAddService;

    @Autowired
    public ComputerService(ComputerRepository computerRepository,
                           ComputerSearchingService computerSearchingService,
                           ComputerUpdateService computerUpdateService,
                           ComputerAddService computerAddService){
        this.computerRepository = computerRepository;
        this.computerSearchingService = computerSearchingService;
        this.computerUpdateService = computerUpdateService;
        this.computerAddService = computerAddService;
    }

    public List<Computer> getComputers(String searchPattern, String orderBy){
        if(searchPattern == null || searchPattern.isEmpty())
            return computerRepository.findAll();
        else
            return getSpecificComputers(searchPattern, orderBy);
    }

    private List<Computer> getSpecificComputers(String searchPattern, String orderBy){
        return computerSearchingService.getWithSearchingAndOrder(searchPattern, orderBy);
    }

    public Optional<Computer> getComputer(long id){
        return computerRepository.findById(id);
    }

    public HttpStatusEnum addNewComputer(Computer computer){
        return computerAddService.addNewComputer(computer);
    }

    public HttpStatusEnum changeComputerStatus(String status, Long id){
        return computerUpdateService.changeComputerStatus(status, id);
    }

    public HttpStatusEnum updateComputer(Long id, Map<String,String> updates){
        return computerUpdateService.updateComputer(id, updates);
    }

    public Optional<Computer> getComputerByOT(String OT){
        return Optional.ofNullable(computerRepository.findByOtnumber(OT));
    }

    public void saveComputerToDB(Computer computer){
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
