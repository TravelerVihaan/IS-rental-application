package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.repository.computers.ComputerProducerRepository;
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
public class ComputerProducerServiceTest {

    @Autowired
    ComputerProducerService computerProducerService;

    @Autowired
    ComputerProducerRepository computerProducerRepository;

    @Before
    public void setUp(){
        ComputerProducer producer1 = new ComputerProducer("DELL");
        producer1.setId(1L);
        computerProducerRepository.save(producer1);
        ComputerProducer producer2 = new ComputerProducer("Apple");
        producer2.setId(2L);
        computerProducerRepository.save(producer2);
    }

    @After
    public void tearDown() {
        computerProducerRepository.deleteAll();
    }

    @Test
    public void getProducersFromDBTest(){
        assertTrue(computerProducerService.getComputerProducerByName("DELL").isPresent());
        assertFalse(computerProducerService.getComputerProducerById(3L).isPresent());
        assertEquals(2, computerProducerService.getAllComputerProducers().size());
    }

    @Test
    public void addProducersToDBTest(){
        ComputerProducer testProducer1 = new ComputerProducer("HP");
        testProducer1.setId(3L);

        ComputerProducer testProducer2 = new ComputerProducer("Apple");
        testProducer2.setId(4L);

        ComputerProducer testProducer3 = new ComputerProducer("");
        testProducer3.setId(5L);

        assertEquals(HttpStatusEnum.CREATED, computerProducerService.addNewComputerProducer(testProducer1));
        assertEquals(HttpStatusEnum.CONFLICT, computerProducerService.addNewComputerProducer(testProducer2));
        assertEquals(HttpStatusEnum.BADREQUEST, computerProducerService.addNewComputerProducer(testProducer3));
    }

}