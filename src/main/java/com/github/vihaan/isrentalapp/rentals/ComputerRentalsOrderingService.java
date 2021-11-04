//package com.github.vihaan.isrentalapp.rentals;
//
//import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalEntity;
//import com.github.vihaan.isrentalapp.service.IOrdering;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class ComputerRentalsOrderingService implements IOrdering<ComputerRentalEntity> {
//
//    public List<ComputerRentalEntity> sortOrderingBy(Set<ComputerRentalEntity> inputSet, String orderBy){
//        List<ComputerRentalEntity> orderedComputerRentalEntities = new ArrayList<>(inputSet);
//        Comparator<ComputerRentalEntity> rentalComparator = comparatorFactoryMethod(orderBy);
//        orderedComputerRentalEntities.sort(rentalComparator);
//        return orderedComputerRentalEntities;
//    }
//
//    private Comparator<ComputerRentalEntity> comparatorFactoryMethod(String orderBy){
//        Comparator<ComputerRentalEntity> rentalComparator = null;
//        if("producer".equalsIgnoreCase(orderBy)) {
//            rentalComparator = Comparator
//                    .comparing(computerRental -> computerRental.getRentedComputer()
//                            .getComputerModel()
//                            .getComputerProducer()
//                            .getProducerName());
//        }
//        if("model".equalsIgnoreCase(orderBy)){
//            rentalComparator = Comparator
//                    .comparing(computerRental -> computerRental
//                            .getRentedComputer()
//                            .getComputerModel()
//                            .getModelName());
//        }
//        if("user".equalsIgnoreCase(orderBy)){
//            rentalComparator = Comparator
//                    .comparing(ComputerRentalEntity::getRentingPersonName);
//        }
//        if("email".equalsIgnoreCase(orderBy)){
//            rentalComparator = Comparator
//                    .comparing(ComputerRentalEntity::getRentingPersonEmail);
//        }
//        if("startDate".equalsIgnoreCase(orderBy)) {
//            rentalComparator = Comparator
//                    .comparing(ComputerRentalEntity::getStartRentalDate);
//        }
//        if("endDate".equalsIgnoreCase(orderBy) || rentalComparator ==null) {
//            rentalComparator = Comparator
//                    .comparing(ComputerRentalEntity::getEndRentalDate);
//        }
//        return rentalComparator;
//    }
//}
