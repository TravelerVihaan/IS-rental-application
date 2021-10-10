package com.github.vihaan.isrentalapp.service.computers;

import com.github.vihaan.isrentalapp.devices.oldies.ComputerService;
import com.github.vihaan.isrentalapp.devices.entities.*;
import com.github.vihaan.isrentalapp.service.HttpStatusEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComputerEntityServiceTest {

    @Autowired
    DiskTypeRepository diskTypeRepository;

    @Autowired
    ComputerProducerRepository computerProducerRepository;

    @Autowired
    ComputerModelRepository computerModelRepository;

    @Autowired
    OperatingSystemRepository operatingSystemRepository;

    @Autowired
    ComputerStatusRepository computerStatusRepository;

    @Autowired
    ComputerRepository computerRepository;

    @Autowired
    ComputerService computerService;

    @Before
    public void setUp(){
        DiskType disk1 = new DiskType("SSD");
        diskTypeRepository.save(disk1);

        OperatingSystem os1 = new OperatingSystem("Windows 7");
        operatingSystemRepository.save(os1);

        ComputerProducer producer1 = new ComputerProducer("DELL");
        computerProducerRepository.save(producer1);

        ComputerModel model1 = new ComputerModel("E6440", computerProducerRepository
                .findByProducerName("DELL").orElseGet(ComputerProducer::new));
        computerModelRepository.save(model1);

        ComputerStatus status1 = new ComputerStatus("waiting");
        computerStatusRepository.save(status1);

        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setOtnumber("11/11/2019/IT/RASP");
        computerEntity.setSerialNumber("1234567A");
        computerEntity.setOperatingSystem(operatingSystemRepository.findByOperatingSystem("Windows 7"));
        computerEntity.setDiskType(diskTypeRepository.findByDiskType("SSD"));
        computerEntity.setComputerModel(computerModelRepository.findByModel("E6440"));
        computerEntity.setComputerStatus(status1);
        computerRepository.save(computerEntity);

    }

    @After
    public void tearDown(){
        computerRepository.deleteAll();
        diskTypeRepository.deleteAll();
        computerModelRepository.deleteAll();
        computerProducerRepository.deleteAll();
        computerStatusRepository.deleteAll();
        operatingSystemRepository.deleteAll();
    }

    @Test
    public void getAllComputers() {
        assertEquals(1,computerService.getComputers(null,null).size());
    }

    @Test
    public void getComputer() {
        assertTrue(computerService.getComputerByOT("11/11/2019/IT/RASP").isPresent());
        assertFalse(computerService.getComputerByOT("wrongOT").isPresent());
        assertNotNull(computerRepository.findByOtnumber("11/11/2019/IT/RASP"));
    }

    @Test
    public void addNewComputer() {
        ComputerEntity computerEntity = computerRepository.findByOtnumber("11/11/2019/IT/RASP");
        computerEntity.setOtnumber("22/22/2019/IT/RASP");
        computerEntity.setSerialNumber("222222A");
        assertEquals(HttpStatusEnum.CREATED, computerService.addNewComputer(computerEntity));
    }

    @Test
    public void deletingComputerTest(){
        ComputerEntity computerEntity = computerRepository.findByOtnumber("11/11/2019/IT/RASP");
        computerRepository.delete(computerEntity);
        assertEquals(0,computerRepository.findAll().size());
        assertEquals(1,computerModelRepository.findAll().size());
        assertEquals(1,diskTypeRepository.findAll().size());
        assertEquals(1,operatingSystemRepository.findAll().size());
        assertEquals(1,computerProducerRepository.findAll().size());
        assertEquals(1,computerStatusRepository.findAll().size());
    }

    @Test
    public void changePropertyComputerTest(){
        //given
        ComputerEntity computerEntity = computerRepository.findByOtnumber("11/11/2019/IT/RASP");
        DiskType diskType = new DiskType("HDD");
        diskTypeRepository.save(diskType);

        //when
        computerEntity.setDiskType(diskTypeRepository.findByDiskType("HDD"));
        computerRepository.save(computerEntity);

        //then
        assertEquals("HDD",computerRepository.findByOtnumber("11/11/2019/IT/RASP").getDiskType().getDiskType());
    }

}