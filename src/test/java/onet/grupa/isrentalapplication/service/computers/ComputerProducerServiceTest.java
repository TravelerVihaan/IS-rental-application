package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.repository.computers.ComputerProducerRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class ComputerProducerServiceTest {

    @Autowired
    ComputerProducerService computerProducerService;

    @Autowired
    ComputerProducerRepository computerProducerRepository;

    @Test
    void getProducersFromDBTest(){
        computerProducerRepository.deleteAll();

        ComputerProducer producer1 = new ComputerProducer("DELL");
        producer1.setId(1L);
        computerProducerRepository.save(producer1);
        ComputerProducer producer2 = new ComputerProducer("Apple");
        producer2.setId(2L);
        computerProducerRepository.save(producer2);

        assertTrue(computerProducerService.getComputerProducerByName("DELL").isPresent());
        assertFalse(computerProducerService.getComputerProducerById(3L).isPresent());
        assertEquals(
                2,
                computerProducerService.getAllComputerProducers().orElse(new ArrayList<>()).size());

        computerProducerRepository.deleteAll();
    }

    @Test
    void addProducersToDBTest(){
        computerProducerRepository.deleteAll();

        ComputerProducer producer1 = new ComputerProducer("DELL");
        producer1.setId(1L);
        computerProducerRepository.save(producer1);
        ComputerProducer producer2 = new ComputerProducer("Apple");
        producer2.setId(2L);
        computerProducerRepository.save(producer2);

        ComputerProducer testProducer1 = new ComputerProducer("HP");
        testProducer1.setId(3L);

        ComputerProducer testProducer2 = new ComputerProducer("Apple");
        testProducer2.setId(4L);

        ComputerProducer testProducer3 = new ComputerProducer("");
        testProducer3.setId(5L);

        assertEquals(HttpStatusEnum.CREATED, computerProducerService.addNewComputerProducer(testProducer1));
        assertEquals(HttpStatusEnum.CONFLICT, computerProducerService.addNewComputerProducer(testProducer2));
        assertEquals(HttpStatusEnum.BADREQUEST, computerProducerService.addNewComputerProducer(testProducer3));

        computerProducerRepository.deleteAll();
    }

}