//package com.github.vihaan.isrentalapp.devices.oldies;
//
//import com.github.vihaan.isrentalapp.devices.entities.ComputerEntity;
//import com.github.vihaan.isrentalapp.service.IOrdering;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class ComputerOrderingService implements IOrdering<ComputerEntity> {
//
//    @Override
//    public List<ComputerEntity> sortOrderingBy(Set<ComputerEntity> inputSet, String orderBy) {
//        List<ComputerEntity> orderedComputerEntities = new ArrayList<>(inputSet);
//        Comparator<ComputerEntity> computerComparator = comparatorFactoryMethod(orderBy);
//        orderedComputerEntities.sort(computerComparator);
//        return orderedComputerEntities;
//    }
//
//    private Comparator<ComputerEntity> comparatorFactoryMethod(String orderBy){
//        Comparator<ComputerEntity> rentalComparator = null;
//        if("producer".equalsIgnoreCase(orderBy)) {
//            rentalComparator = Comparator
//                    .comparing(computer -> computer
//                            .getComputerModel()
//                            .getComputerProducer()
//                            .getProducerName());
//        }
//        if("model".equalsIgnoreCase(orderBy)){
//            rentalComparator = Comparator
//                    .comparing(computer -> computer
//                            .getComputerModel()
//                            .getModelName());
//        }
//        return rentalComparator;
//    }
//}
