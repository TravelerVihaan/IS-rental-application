package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.repository.computers.ComputerProducerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
        ComputerProducer producer1 = new ComputerProducer("DELL");
        producer1.setId(1L);
        computerProducerRepository.save(producer1);
        ComputerProducer producer2 = new ComputerProducer("Apple");
        producer2.setId(2L);
        computerProducerRepository.save(producer2);

        assertEquals(
                HttpStatus.OK,
                computerProducerService.getResponseWithComputerProducer(1L).getStatusCode());
        assertEquals(
                new ResponseEntity(HttpStatus.NOT_FOUND),
                computerProducerService.getResponseWithComputerProducer(3L));
        assertEquals(
                2,
                computerProducerRepository.findAll().size());

        computerProducerRepository.deleteAll();
    }

    @Test
    void addProducersToDBTest(){
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

        assertEquals(
                new ResponseEntity(HttpStatus.CREATED),
                computerProducerService.addNewComputerProducer(testProducer1));
        assertEquals(
                new ResponseEntity(HttpStatus.CONFLICT),
                computerProducerService.addNewComputerProducer(testProducer2));
        assertEquals(
                new ResponseEntity(HttpStatus.BAD_REQUEST),
                computerProducerService.addNewComputerProducer(testProducer3));

        computerProducerRepository.deleteAll();
    }

}