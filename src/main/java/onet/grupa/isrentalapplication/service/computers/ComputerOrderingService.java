package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.service.IOrdering;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ComputerOrderingService implements IOrdering<Computer> {


    @Override
    public List<Computer> sortOrderingBy(Set<Computer> inputSet, String orderBy) {
        return null;
    }
}
