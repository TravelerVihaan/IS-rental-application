//package com.github.vihaan.isrentalapp.rentals;
//
//import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalRepository;
//import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalEntity;
//import com.github.vihaan.isrentalapp.service.IOrdering;
//import com.github.vihaan.isrentalapp.service.ISearching;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class ComputerRentalSearchingService implements ISearching<ComputerRentalEntity> {
//
//    private ComputerRentalRepository computerRentalRepository;
//    private IOrdering<ComputerRentalEntity> computerRentalsOrderingService;
//
//    @Autowired
//    public ComputerRentalSearchingService(ComputerRentalRepository computerRentalRepository,
//                                          ComputerRentalsOrderingService computerRentalsOrderingService){
//        this.computerRentalRepository = computerRentalRepository;
//        this.computerRentalsOrderingService = computerRentalsOrderingService;
//    }
//
//    /**
//     * Return list with found ComputerRentals in database.
//     * Method is searching by email, name of renting person, by computer model
//     * and computer producer.
//     *
//     * @param searchPhrase pattern using to search in DB
//     * @return Optional with List with ComputerRentals found in DB
//     */
//    public List<ComputerRentalEntity> getWithSearchingAndOrder(String searchPhrase, String orderBy) {
//        Set<ComputerRentalEntity> foundRentals = getComputerRentalsWithSearching(searchPhrase);
//        return computerRentalsOrderingService.sortOrderingBy(foundRentals, orderBy);
//    }
//
//    /**
//     * Taking Set of unique Computer Rentals objects from db, searching by pattern
//     * @param searchPhrase search pattern using to find data in db
//     * @return HashSet of found records
//     */
//    private Set<ComputerRentalEntity> getComputerRentalsWithSearching(String searchPhrase){
//        Set<ComputerRentalEntity> foundRentals = new HashSet<>();
//        foundRentals.addAll(computerRentalRepository.findAllByRentingPersonEmailContaining(searchPhrase));
//        foundRentals.addAll(computerRentalRepository.findAllByRentingPersonNameContaining(searchPhrase));
//        foundRentals.addAll(getRentalsByProducer(searchPhrase));
//        foundRentals.addAll(getRentalsByModel(searchPhrase));
//        return foundRentals;
//    }
//
//    private List<ComputerRentalEntity> getRentalsByProducer(String searchPhrase){
//        return computerRentalRepository
//                .findAllByRentedComputer_ComputerModel_ComputerProducer_ProducerNameContaining(searchPhrase);
//    }
//
//    private List<ComputerRentalEntity> getRentalsByModel(String searchPhrase){
//        return computerRentalRepository
//                .findAllByRentedComputer_ComputerModel_ModelContaining(searchPhrase);
//    }
//}
