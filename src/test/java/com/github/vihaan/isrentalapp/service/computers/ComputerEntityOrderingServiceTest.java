package com.github.vihaan.isrentalapp.service.computers;

import com.github.vihaan.isrentalapp.devices.oldies.ComputerOrderingService;
import com.github.vihaan.isrentalapp.devices.entities.*;
import com.github.vihaan.isrentalapp.service.IOrdering;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComputerEntityOrderingServiceTest {

    private Set<ComputerEntity> inputSet = new HashSet<>();

    private IOrdering<ComputerEntity> computerOrderingService;

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

    private List<ComputerEntity> listSortedByProducer(){
        List<ComputerEntity> list = new ArrayList<>();
        list.add(getComputer1());
        list.add(getComputer2());
        list.add(getComputer3());
        return list;
    }
    private List<ComputerEntity> listSortedByModel(){
        List<ComputerEntity> list = new ArrayList<>();
        list.add(getComputer2());
        list.add(getComputer3());
        list.add(getComputer1());
        return list;
    }


    private ComputerEntity getComputer1(){
        ComputerProducer apple = new ComputerProducer("Apple");
        ComputerModel macBook = new ComputerModel("MacBook",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setOtnumber("0000/56/7890/IT/KR");
        computerEntity.setSerialNumber("XYZABCD1312");
        computerEntity.setOperatingSystem(os);
        computerEntity.setDiskType(diskType);
        computerEntity.setComputerModel(macBook);
        computerEntity.setComputerStatus(computerStatus);
        return computerEntity;
    }

    private ComputerEntity getComputer2(){
        ComputerProducer apple = new ComputerProducer("DELL");
        ComputerModel macBook = new ComputerModel("E6440",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setOtnumber("1111/56/7890/IT/KR");
        computerEntity.setSerialNumber("XYZABCD1312");
        computerEntity.setOperatingSystem(os);
        computerEntity.setDiskType(diskType);
        computerEntity.setComputerModel(macBook);
        computerEntity.setComputerStatus(computerStatus);
        return computerEntity;
    }

    private ComputerEntity getComputer3(){
        ComputerProducer apple = new ComputerProducer("Lenovo");
        ComputerModel macBook = new ComputerModel("E7440",apple);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setOtnumber("2222/56/7890/IT/KR");
        computerEntity.setSerialNumber("XYZABCD1312");
        computerEntity.setOperatingSystem(os);
        computerEntity.setDiskType(diskType);
        computerEntity.setComputerModel(macBook);
        computerEntity.setComputerStatus(computerStatus);
        return computerEntity;
    }
}