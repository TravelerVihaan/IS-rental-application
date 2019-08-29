package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComputerRentalsOrderingService {

    List<ComputerRental> sortRentalsOrderingBy(Set<ComputerRental> computerRentalsSet, String orderBy){
        List<ComputerRental> orderedComputerRentals = new ArrayList<>(computerRentalsSet);
        Comparator<ComputerRental> rentalComparator = comparatorFactoryMethod(orderBy);
        orderedComputerRentals.sort(rentalComparator);
        return orderedComputerRentals;
    }

    private Comparator<ComputerRental> comparatorFactoryMethod(String orderBy){
        Comparator<ComputerRental> rentalComparator = null;
        if("producer".equalsIgnoreCase(orderBy)) {
            rentalComparator = Comparator
                    .comparing(computerRental -> computerRental.getRentedComputer()
                            .getComputerModel()
                            .getComputerProducer()
                            .getProducerName());
        }
        if("model".equalsIgnoreCase(orderBy)){
            rentalComparator = Comparator
                    .comparing(computerRental -> computerRental
                            .getRentedComputer()
                            .getComputerModel()
                            .getModel());
        }
        if("user".equalsIgnoreCase(orderBy)){
            rentalComparator = Comparator
                    .comparing(ComputerRental::getRentingPersonName);
        }
        if("email".equalsIgnoreCase(orderBy)){
            rentalComparator = Comparator
                    .comparing(ComputerRental::getRentingPersonemail);
        }
        if("startDate".equalsIgnoreCase(orderBy)) {
            rentalComparator = Comparator
                    .comparing(ComputerRental::getStartRentalDate);
        }
        if("endDate".equalsIgnoreCase(orderBy) || rentalComparator ==null) {
            rentalComparator = Comparator
                    .comparing(ComputerRental::getEndRentalDate);
        }
        return rentalComparator;
    }
}
