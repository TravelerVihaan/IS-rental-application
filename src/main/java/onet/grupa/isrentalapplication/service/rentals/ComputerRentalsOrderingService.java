package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ComputerRentalsOrderingService {

    public List<ComputerRental> sortRentalsOrderingBy(Set<ComputerRental> computerRentalsSet, String orderBy){
        List<ComputerRental> orderedComputerRentals = new ArrayList<>();

        return orderedComputerRentals;
    }
}
