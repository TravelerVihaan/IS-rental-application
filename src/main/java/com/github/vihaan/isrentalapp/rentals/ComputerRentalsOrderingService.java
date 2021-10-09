package com.github.vihaan.isrentalapp.rentals;

import com.github.vihaan.isrentalapp.rentals.entities.ComputerRental;
import com.github.vihaan.isrentalapp.service.IOrdering;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ComputerRentalsOrderingService implements IOrdering<ComputerRental> {

    public List<ComputerRental> sortOrderingBy(Set<ComputerRental> inputSet, String orderBy){
        List<ComputerRental> orderedComputerRentals = new ArrayList<>(inputSet);
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
                            .getModelName());
        }
        if("user".equalsIgnoreCase(orderBy)){
            rentalComparator = Comparator
                    .comparing(ComputerRental::getRentingPersonName);
        }
        if("email".equalsIgnoreCase(orderBy)){
            rentalComparator = Comparator
                    .comparing(ComputerRental::getRentingPersonEmail);
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
