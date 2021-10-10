package com.github.vihaan.isrentalapp.devices.oldies;

import com.github.vihaan.isrentalapp.devices.entities.ComputerRepository;
import com.github.vihaan.isrentalapp.devices.entities.ComputerEntity;
import com.github.vihaan.isrentalapp.service.IOrdering;
import com.github.vihaan.isrentalapp.service.ISearching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ComputerSearchingService implements ISearching<ComputerEntity> {

    private ComputerRepository computerRepository;
    private IOrdering<ComputerEntity> computerOrderingService;

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
    public List<ComputerEntity> getWithSearchingAndOrder(String searchPhrase, String orderBy) {
        Set<ComputerEntity> foundComputerEntities = getComputersWithSearching(searchPhrase);
        return computerOrderingService.sortOrderingBy(foundComputerEntities,orderBy);
    }

    private Set<ComputerEntity> getComputersWithSearching(String searchPhrase){
        Set<ComputerEntity> foundComputerEntities = new HashSet<>();
        foundComputerEntities.addAll(computerRepository.findAllByOtnumberContaining(searchPhrase));
        foundComputerEntities.addAll(computerRepository.findAllBySerialNumberContaining(searchPhrase));
        foundComputerEntities.addAll(getComputersByModel(searchPhrase));
        foundComputerEntities.addAll(getComputersByProducer(searchPhrase));
        return foundComputerEntities;
    }

    private List<ComputerEntity> getComputersByModel(String searchPhrase){
        return computerRepository.findAllByComputerModel_ModelContaining(searchPhrase);
    }

    private List<ComputerEntity> getComputersByProducer(String searchPhrase){
        return computerRepository
                .findAllByComputerModel_ComputerProducer_ProducerNameContaining(searchPhrase);
    }
}
