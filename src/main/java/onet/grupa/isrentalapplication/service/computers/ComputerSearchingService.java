package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import onet.grupa.isrentalapplication.service.IOrdering;
import onet.grupa.isrentalapplication.service.ISearching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<Computer> getWithSearchingAndOrder(String searchPhrase, String orderBy) {
        return null;
    }
}
