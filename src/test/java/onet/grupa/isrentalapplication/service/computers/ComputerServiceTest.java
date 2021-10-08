package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.devices.entities.*;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
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
public class ComputerServiceTest {

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

        Computer computer = new Computer();
        computer.setOtnumber("11/11/2019/IT/RASP");
        computer.setSerialNumber("1234567A");
        computer.setOperatingSystem(operatingSystemRepository.findByOperatingSystem("Windows 7"));
        computer.setDiskType(diskTypeRepository.findByDiskType("SSD"));
        computer.setComputerModel(computerModelRepository.findByModel("E6440"));
        computer.setComputerStatus(status1);
        computerRepository.save(computer);

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
        Computer computer = computerRepository.findByOtnumber("11/11/2019/IT/RASP");
        computer.setOtnumber("22/22/2019/IT/RASP");
        computer.setSerialNumber("222222A");
        assertEquals(HttpStatusEnum.CREATED, computerService.addNewComputer(computer));
    }

    @Test
    public void deletingComputerTest(){
        Computer computer = computerRepository.findByOtnumber("11/11/2019/IT/RASP");
        computerRepository.delete(computer);
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
        Computer computer = computerRepository.findByOtnumber("11/11/2019/IT/RASP");
        DiskType diskType = new DiskType("HDD");
        diskTypeRepository.save(diskType);

        //when
        computer.setDiskType(diskTypeRepository.findByDiskType("HDD"));
        computerRepository.save(computer);

        //then
        assertEquals("HDD",computerRepository.findByOtnumber("11/11/2019/IT/RASP").getDiskType().getDiskType());
    }

}