package com.github.vihaan.isrentalapp.service.computers;

import com.github.vihaan.isrentalapp.devices.oldies.ComputerProducerService;
import com.github.vihaan.isrentalapp.devices.entities.ComputerProducer;
import com.github.vihaan.isrentalapp.devices.entities.ComputerProducerRepository;
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
public class ComputerEntityProducerServiceTest {

    @Autowired
    ComputerProducerService computerProducerService;

    @Autowired
    ComputerProducerRepository computerProducerRepository;

    @Before
    public void setUp(){
        ComputerProducer producer1 = new ComputerProducer("DELL");
        computerProducerRepository.save(producer1);
        ComputerProducer producer2 = new ComputerProducer("Apple");
        computerProducerRepository.save(producer2);
    }

    @After
    public void tearDown() {
        computerProducerRepository.deleteAll();
    }

    @Test
    public void checkIfIdIsNotNull(){
        assertNotNull(computerProducerService.getComputerProducerByName("DELL").orElseThrow().getId());
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
        ComputerProducer testProducer2 = new ComputerProducer("Apple");
        ComputerProducer testProducer3 = new ComputerProducer("");

        assertEquals(HttpStatusEnum.CREATED, computerProducerService.addNewComputerProducer(testProducer1));
        assertEquals(HttpStatusEnum.CONFLICT, computerProducerService.addNewComputerProducer(testProducer2));
        assertEquals(HttpStatusEnum.BADREQUEST, computerProducerService.addNewComputerProducer(testProducer3));
    }

}