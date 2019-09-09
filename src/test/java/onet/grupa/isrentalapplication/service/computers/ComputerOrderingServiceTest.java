package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComputerOrderingServiceTest {

    private Set<Computer> inputSet = new HashSet<>();

    private ComputerOrderingService computerOrderingService;

    @Before
    public void setUp(){
        inputSet.add(getComputer1());
        inputSet.add(getComputer2());
        inputSet.add(getComputer3());
    }

    @After
    public void tearDown(){
        inputSet.clear();
    }

    @Test
    public void shouldReturnSortedListByProducer(){

    }

    @Test
    public void shouldReturnSortedListByModel(){

    }


    private Computer getComputer1(){
        ComputerProducer apple = new ComputerProducer("Apple");
        ComputerModel macBook = new ComputerModel("MacBook",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        Computer computer = new Computer();
        computer.setOtnumber("1234/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(macBook);
        computer.setComputerStatus(computerStatus);
        return computer;
    }

    private Computer getComputer2(){
        ComputerProducer apple = new ComputerProducer("DELL");
        ComputerModel macBook = new ComputerModel("E6440",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        Computer computer = new Computer();
        computer.setOtnumber("1234/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(macBook);
        computer.setComputerStatus(computerStatus);
        return computer;
    }

    private Computer getComputer3(){
        ComputerProducer apple = new ComputerProducer("DELL");
        ComputerModel macBook = new ComputerModel("E7440",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        Computer computer = new Computer();
        computer.setOtnumber("1234/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(macBook);
        computer.setComputerStatus(computerStatus);
        return computer;
    }
}