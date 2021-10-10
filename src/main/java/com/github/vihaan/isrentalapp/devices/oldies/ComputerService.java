package com.github.vihaan.isrentalapp.devices.oldies;

import com.github.vihaan.isrentalapp.devices.NewDeviceCreator;
import com.github.vihaan.isrentalapp.devices.entities.ComputerEntity;
import com.github.vihaan.isrentalapp.devices.entities.ComputerRepository;
import com.github.vihaan.isrentalapp.service.HttpStatusEnum;
import com.github.vihaan.isrentalapp.service.ISearching;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ComputerService {

    private ComputerRepository computerRepository;
    private ISearching<ComputerEntity> computerSearchingService;
    private ComputerUpdateService computerUpdateService;
    private NewDeviceCreator newDeviceCreator;

    @Autowired
    public ComputerService(ComputerRepository computerRepository,
                           ComputerSearchingService computerSearchingService,
                           ComputerUpdateService computerUpdateService,
                           NewDeviceCreator newDeviceCreator){
        this.computerRepository = computerRepository;
        this.computerSearchingService = computerSearchingService;
        this.computerUpdateService = computerUpdateService;
        this.newDeviceCreator = newDeviceCreator;
    }

    public List<ComputerEntity> getComputers(String searchPattern, String orderBy){
        if(searchPattern == null || searchPattern.isEmpty())
            return computerRepository.findAll();
        else
            return getSpecificComputers(searchPattern, orderBy);
    }

    private List<ComputerEntity> getSpecificComputers(String searchPattern, String orderBy){
        return computerSearchingService.getWithSearchingAndOrder(searchPattern, orderBy);
    }

    public Optional<ComputerEntity> getComputer(long id){
        return computerRepository.findById(id);
    }

    public HttpStatusEnum addNewComputer(ComputerEntity computerEntity){
        return newDeviceCreator.addNewComputer(computerEntity);
    }

    public HttpStatusEnum changeComputerStatus(String status, Long id){
        return computerUpdateService.changeComputerStatus(status, id);
    }

    public HttpStatusEnum updateComputer(Long id, Map<String,String> updates){
        return computerUpdateService.updateComputer(id, updates);
    }

    public Optional<ComputerEntity> getComputerByOT(String OT){
        return Optional.ofNullable(computerRepository.findByOtnumber(OT));
    }

    public void saveComputerToDB(ComputerEntity computerEntity){
        computerRepository.save(computerEntity);
    }

    public HttpStatusEnum deleteComputer(Long id){
        if(computerRepository.findById(id).isPresent()){
            computerRepository.deleteById(id);
            return HttpStatusEnum.OK;
        }
        return HttpStatusEnum.NOTFOUND;
    }
}
