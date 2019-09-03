package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import onet.grupa.isrentalapplication.service.IOrdering;
import onet.grupa.isrentalapplication.service.ISearching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ComputerSearchingService implements ISearching<Computer> {

    private ComputerRepository computerRepository;
    private IOrdering<Computer> computerOrderingService;

    @Autowired
    public ComputerSearchingService(ComputerRepository computerRepository,
                                    ComputerOrderingService computerOrderingService){
        this.computerRepository = computerRepository;
        this.computerOrderingService = computerOrderingService;
    }

    /**
     * Return list with found Computers in database.
     * Method is searching by OTnumber, computer model, producer, serialnumber
     * and computer producer.
     *
     * @param searchPhrase pattern using to search in DB
     * @return List with Computers found in DB
     */
    @Override
    public List<Computer> getWithSearchingAndOrder(String searchPhrase, String orderBy) {
        Set<Computer> foundComputers = getComputersWithSearching(searchPhrase);
        return computerOrderingService.sortOrderingBy(foundComputers,orderBy);
    }

    private Set<Computer> getComputersWithSearching(String searchPhrase){
        Set<Computer> foundComputers = new HashSet<>();
        foundComputers.addAll(computerRepository.findAllByOtnumberContaining(searchPhrase));
        foundComputers.addAll(computerRepository.findAllBySerialNumberContaining(searchPhrase));
        foundComputers.addAll(getComputersByModel(searchPhrase));
        foundComputers.addAll(getComputersByProducer(searchPhrase));
        return foundComputers;
    }

    private List<Computer> getComputersByModel(String searchPhrase){
        return computerRepository.findAllByComputerModel_ModelContaining(searchPhrase);
    }

    private List<Computer> getComputersByProducer(String searchPhrase){
        return computerRepository
                .findAllByComputerModel_ComputerProducer_ProducerNameContaining(searchPhrase);
    }
}
