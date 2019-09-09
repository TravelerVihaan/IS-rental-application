package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.*;
import onet.grupa.isrentalapplication.service.IOrdering;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComputerOrderingServiceTest {

    private Set<Computer> inputSet = new HashSet<>();

    private IOrdering<Computer> computerOrderingService;

    @Before
    public void setUp(){
        inputSet.add(getComputer1());
        inputSet.add(getComputer2());
        inputSet.add(getComputer3());
        computerOrderingService = new ComputerOrderingService();
    }

    @After
    public void tearDown(){
        inputSet.clear();
    }

    @Test
    public void shouldReturnSortedListByProducer(){
        assertEquals(listSortedByProducer(),computerOrderingService.sortOrderingBy(inputSet,"producer"));
    }

    @Test
    public void shouldReturnSortedListByModel(){
        assertEquals(listSortedByModel(),computerOrderingService.sortOrderingBy(inputSet,"model"));
    }

    private List<Computer> listSortedByProducer(){
        List<Computer> list = new ArrayList<>();
        list.add(getComputer1());
        list.add(getComputer2());
        list.add(getComputer3());
        return list;
    }
    private List<Computer> listSortedByModel(){
        List<Computer> list = new ArrayList<>();
        list.add(getComputer2());
        list.add(getComputer3());
        list.add(getComputer1());
        return list;
    }


    private Computer getComputer1(){
        ComputerProducer apple = new ComputerProducer("Apple");
        ComputerModel macBook = new ComputerModel("MacBook",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        Computer computer = new Computer();
        computer.setOtnumber("0000/56/7890/IT/KR");
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
        computer.setOtnumber("1111/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(macBook);
        computer.setComputerStatus(computerStatus);
        return computer;
    }

    private Computer getComputer3(){
        ComputerProducer apple = new ComputerProducer("Lenovo");
        ComputerModel macBook = new ComputerModel("E7440",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        Computer computer = new Computer();
        computer.setOtnumber("2222/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(macBook);
        computer.setComputerStatus(computerStatus);
        return computer;
    }
}