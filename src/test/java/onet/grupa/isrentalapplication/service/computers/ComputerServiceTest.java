package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.*;
import onet.grupa.isrentalapplication.repository.computers.*;
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
        disk1.setId(1L);
        diskTypeRepository.save(disk1);

        OperatingSystem os1 = new OperatingSystem("Windows 7");
        os1.setId(1L);
        operatingSystemRepository.save(os1);

        ComputerProducer producer1 = new ComputerProducer("DELL");
        producer1.setId(1L);
        computerProducerRepository.save(producer1);

        ComputerModel model1 = new ComputerModel("E6440", producer1);
        model1.setId(1L);
        computerModelRepository.save(model1);

        ComputerStatus status1 = new ComputerStatus("waiting");
        status1.setId(1L);
        computerStatusRepository.save(status1);
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
    }

    @Test
    public void getComputer() {
    }

    @Test
    public void addNewComputer() {
    }

}