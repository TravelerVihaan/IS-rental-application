package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.Computer;
import onet.grupa.isrentalapplication.service.IOrdering;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
public class ComputerOrderingService implements IOrdering<Computer> {

    @Override
    public List<Computer> sortOrderingBy(Set<Computer> inputSet, String orderBy) {
        List<Computer> orderedComputers = new ArrayList<>(inputSet);
        Comparator<Computer> computerComparator = comparatorFactoryMethod(orderBy);
        orderedComputers.sort(computerComparator);
        return orderedComputers;
    }

    private Comparator<Computer> comparatorFactoryMethod(String orderBy){
        Comparator<Computer> rentalComparator = null;
        if("producer".equalsIgnoreCase(orderBy)) {
            rentalComparator = Comparator
                    .comparing(computer -> computer
                            .getComputerModel()
                            .getComputerProducer()
                            .getProducerName());
        }
        if("model".equalsIgnoreCase(orderBy)){
            rentalComparator = Comparator
                    .comparing(computer -> computer
                            .getComputerModel()
                            .getModel());
        }
        return rentalComparator;
    }
}
