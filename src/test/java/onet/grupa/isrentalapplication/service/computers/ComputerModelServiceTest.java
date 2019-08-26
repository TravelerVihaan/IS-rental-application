package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerModel;
import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.repository.computers.ComputerModelRepository;
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
public class ComputerModelServiceTest {

    @Autowired
    ComputerModelService computerModelService;

    @Autowired
    ComputerProducerRepository computerProducerRepository;

    @Autowired
    ComputerModelRepository computerModelRepository;

    @Before
    public void setUp() {
        ComputerProducer producer1 = new ComputerProducer("DELL");
        producer1.setId(1L);
        computerProducerRepository.save(producer1);

        ComputerModel model1 = new ComputerModel("E6440", producer1);
        model1.setId(1L);
        computerModelRepository.save(model1);

        ComputerModel model2 = new ComputerModel();
        model2.setId(2L);
        model2.setModel("E7450");
        model2.setComputerProducer(computerProducerRepository.findById(1L).orElse(new ComputerProducer()));
        computerModelRepository.save(model2);
    }

    @After
    public void tearDown(){
        computerModelRepository.deleteAll();
        computerProducerRepository.deleteAll();
    }

    @Test
    public void getComputerModelTest(){
        assertTrue(computerModelService.getComputerModel(2L).isPresent());
        assertTrue(computerModelService.getComputerModelByName("E6440").isPresent());
        assertFalse(computerModelService.getComputerModelByName("5480").isPresent());
        assertFalse(computerModelService.getComputerModel(3L).isPresent());
        //assertEquals(producer1.getProducerName(),computerModelService.getComputerModel(4L).orElse(new ComputerModel()).getComputerProducer().getProducerName());
        assertEquals(2, computerModelService.getAllComputerModels().size());
    }

    @Test
    public void addComputerModelTest(){

        ComputerProducer producer2 = new ComputerProducer("Apple");
        producer2.setId(2L);
        computerProducerRepository.save(producer2);
        ComputerModel testModel1 = new ComputerModel("MacBook Pro");
        testModel1.setId(3L);
        testModel1.setComputerProducer(computerProducerRepository.findByProducerName("Apple").orElse(new ComputerProducer()));

        ComputerModel testModel2 = new ComputerModel("MacBook Pro");
        testModel2.setId(4L);
        testModel2.setComputerProducer(computerProducerRepository.findByProducerName("Apple").orElse(new ComputerProducer()));

        ComputerModel testModel3 = new ComputerModel("");
        testModel3.setId(5L);
        testModel3.setComputerProducer(computerProducerRepository.findByProducerName("Apple").orElse(new ComputerProducer()));

        ComputerModel testModel4 = new ComputerModel("5490");
        testModel4.setId(6L);

        assertEquals(HttpStatusEnum.CREATED, computerModelService.addNewComputerModel(testModel1));
        assertEquals(HttpStatusEnum.CONFLICT, computerModelService.addNewComputerModel(testModel2));
        assertEquals(HttpStatusEnum.BADREQUEST, computerModelService.addNewComputerModel(testModel3));
        assertEquals(HttpStatusEnum.BADREQUEST, computerModelService.addNewComputerModel(testModel4));
    }

}