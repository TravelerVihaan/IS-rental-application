package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.computers.*;
import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NewComputerRentalServiceTest {

    @Mock
    private ComputerRentalRepository computerRentalRepository;
    @Mock
    private ComputerService computerService;

    private NewComputerRentalService newComputerRentalService;
    private ComputerRental computerRental;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        newComputerRentalService = new NewComputerRentalService(computerRentalRepository,computerService);
    }

    private ComputerRental initializeRental(){
        ComputerRental rental = new ComputerRental();
        rental.setRentedComputer(initializeComputer());

        return rental;
    }

    private Computer initializeComputer(){
        ComputerProducer computerProducer = new ComputerProducer("Apple");
        ComputerModel computerModel = new ComputerModel("MacBook",computerProducer);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        Computer computer = new Computer();
        computer.setOtnumber("1234/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(computerModel);
        computer.setComputerStatus(computerStatus);
        return computer;
    }
}