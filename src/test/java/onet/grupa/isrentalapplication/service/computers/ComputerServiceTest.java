package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.*;
import onet.grupa.isrentalapplication.repository.computers.*;
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
        computerRepository.save(computer);

    }

    @After
    public void tearDown(){
        diskTypeRepository.deleteAll();
        computerModelRepository.deleteAll();
        computerProducerRepository.deleteAll();
        computerStatusRepository.deleteAll();
        operatingSystemRepository.deleteAll();
    }

    @Test
    public void getAllComputers() {
        assertEquals(1,computerService.getAllComputers().size());
    }

    @Test
    public void getComputer() {
        assertTrue(computerService.getComputer(1L).isPresent());
        assertFalse(computerService.getComputer(2L).isPresent());
        assertNotNull(computerRepository.findByOtnumber("11/11/2019/IT/RASP"));
    }

    @Test
    public void addNewComputer() {
        Computer computer = computerRepository.findByOtnumber("11/11/2019/IT/RASP");
        computer.setOtnumber("22/22/2019/IT/RASP");
        computer.setSerialNumber("222222A");
        assertEquals(HttpStatusEnum.CREATED, computerService.addNewComputer(computer));
    }

}