package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
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

    public HttpStatusEnum deleteComputer(Long id){
        if(computerRepository.findById(id).isPresent()){
            computerRepository.deleteById(id);
            return HttpStatusEnum.OK;
        }
        return HttpStatusEnum.NOTFOUND;
    }
}
