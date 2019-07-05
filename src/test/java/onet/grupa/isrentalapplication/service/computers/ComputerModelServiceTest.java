package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.ComputerModel;
import onet.grupa.isrentalapplication.domain.computers.ComputerProducer;
import onet.grupa.isrentalapplication.repository.computers.ComputerModelRepository;
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
class ComputerModelServiceTest {

    @Autowired
    ComputerModelService computerModelService;

    @Autowired
    ComputerProducerRepository computerProducerRepository;

    @Autowired
    ComputerModelRepository computerModelRepository;

    @Test
    void getComputerModelTest(){
        computerModelRepository.deleteAll();
        computerProducerRepository.deleteAll();

        ComputerProducer producer1 = new ComputerProducer("DELL");
        producer1.setId(3L);
        computerProducerRepository.save(producer1);

        ComputerModel model1 = new ComputerModel("E6440", producer1);
        model1.setId(1L);
        computerModelRepository.save(model1);
        ComputerModel model2 = new ComputerModel();
        model2.setId(2L);
        model2.setModel("E7450");
        model2.setComputerProducer(computerProducerRepository.findById(3L).orElse(new ComputerProducer()));
        computerModelRepository.save(model2);

        computerModelRepository.findAll().forEach(System.out::println);

        assertTrue(computerModelService.getComputerModel(4L).isPresent());
        assertTrue(computerModelService.getComputerModelByName("E6440").isPresent());
        assertFalse(computerModelService.getComputerModelByName("5480").isPresent());
        assertFalse(computerModelService.getComputerModel(3L).isPresent());
        assertEquals(producer1.getProducerName(),computerModelService.getComputerModel(4L).orElse(new ComputerModel()).getComputerProducer().getProducerName());
        assertEquals(2, computerModelService.getAllComputerModels().orElse(new ArrayList<>()).size());

        computerProducerRepository.deleteAll();
        assertEquals(0, computerModelService.getAllComputerModels().orElse(new ArrayList<>(10)).size());
        computerModelRepository.deleteAll();
    }

    @Test
    void addComputerModelTest(){

        computerModelRepository.deleteAll();
        computerProducerRepository.deleteAll();

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

        computerModelRepository.findAll().forEach(System.out::println);

        assertEquals(HttpStatusEnum.CREATED, computerModelService.addNewComputerModel(testModel1));
        assertEquals(HttpStatusEnum.CONFLICT, computerModelService.addNewComputerModel(testModel2));
        assertEquals(HttpStatusEnum.BADREQUEST, computerModelService.addNewComputerModel(testModel3));
        assertEquals(HttpStatusEnum.BADREQUEST, computerModelService.addNewComputerModel(testModel4));

        computerModelRepository.findAll().forEach(System.out::println);

        computerModelRepository.deleteAll();
        computerProducerRepository.deleteAll();
    }

}