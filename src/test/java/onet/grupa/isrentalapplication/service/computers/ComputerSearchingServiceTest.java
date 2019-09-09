package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.*;
import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComputerSearchingServiceTest {

    @Mock
    private ComputerRepository computerRepository;
    @Mock
    private ComputerOrderingService computerOrderingService;

    private ComputerSearchingService computerSearchingService;

    @Before
    public void setUp(){
        computerSearchingService = new ComputerSearchingService(computerRepository,computerOrderingService);

    }

    @Test
    public void getSearchedComputersTest(){
        Mockito.when(computerRepository.findAllByOtnumberContaining(any))

    }

    private onet.grupa.isrentalapplication.domain.computers.Computer getComputer1(){
        ComputerProducer apple = new ComputerProducer("Apple");
        ComputerModel macBook = new ComputerModel("MacBook",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        onet.grupa.isrentalapplication.domain.computers.Computer computer = new onet.grupa.isrentalapplication.domain.computers.Computer();
        computer.setOtnumber("0000/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(macBook);
        computer.setComputerStatus(computerStatus);
        return computer;
    }

    private onet.grupa.isrentalapplication.domain.computers.Computer getComputer2(){
        ComputerProducer apple = new ComputerProducer("DELL");
        ComputerModel macBook = new ComputerModel("E6440",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        onet.grupa.isrentalapplication.domain.computers.Computer computer = new onet.grupa.isrentalapplication.domain.computers.Computer();
        computer.setOtnumber("1111/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(macBook);
        computer.setComputerStatus(computerStatus);
        return computer;
    }

    private onet.grupa.isrentalapplication.domain.computers.Computer getComputer3(){
        ComputerProducer apple = new ComputerProducer("Lenovo");
        ComputerModel macBook = new ComputerModel("E7440",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        onet.grupa.isrentalapplication.domain.computers.Computer computer = new Computer();
        computer.setOtnumber("2222/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(macBook);
        computer.setComputerStatus(computerStatus);
        return computer;
    }

}