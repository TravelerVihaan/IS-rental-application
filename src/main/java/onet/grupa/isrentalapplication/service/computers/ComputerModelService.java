package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.repository.computers.ComputerModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputerModelService {

    private ComputerModelRepository computerModelRepository;

    @Autowired
    public ComputerModelService(ComputerModelRepository computerModelRepository){
        this.computerModelRepository = computerModelRepository;
    }
}
