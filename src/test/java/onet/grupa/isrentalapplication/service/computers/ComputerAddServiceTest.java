package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.devices.entities.*;
import onet.grupa.isrentalapplication.devices.entities.ComputerRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComputerAddServiceTest {

    private Computer computer;

    @Mock
    private ComputerRepository computerRepository;

    @InjectMocks
    private ComputerAddService computerAddService;

    @Before
    public void setUp(){
        ComputerProducer computerProducer = new ComputerProducer("Apple");
        ComputerModel computerModel = new ComputerModel("MacBook",computerProducer);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        computer = new Computer();
        computer.setOtnumber("1234/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(computerModel);
        computer.setComputerStatus(computerStatus);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        computer = null;
    }

    @Test
    public void shouldAddComputerAndReturnCREATED() {
        Mockito.when(computerRepository.findByOtnumber(computer.getOtnumber())).thenReturn(null);
        assertEquals(HttpStatusEnum.CREATED, computerAddService.addNewComputer(computer));
        assertNotEquals(HttpStatusEnum.BADREQUEST,computerAddService.addNewComputer(computer));
        assertNotEquals(HttpStatusEnum.CONFLICT,computerAddService.addNewComputer(computer));
    }

    @Test
    public void shouldReturnBADREQUESTEnum(){
        computer.setSerialNumber(null);
        assertEquals(HttpStatusEnum.BADREQUEST,computerAddService.addNewComputer(computer));
        assertNotEquals(HttpStatusEnum.CONFLICT,computerAddService.addNewComputer(computer));
        assertNotEquals(HttpStatusEnum.CREATED,computerAddService.addNewComputer(computer));
    }

    @Test
    public void shouldRejectAddingNewComputerAndReturnCONFLICT(){
        Mockito.when(computerRepository.findByOtnumber(computer.getOtnumber())).thenReturn(computer);
        assertEquals(HttpStatusEnum.CONFLICT, computerAddService.addNewComputer(computer));
        assertNotEquals(HttpStatusEnum.BADREQUEST,computerAddService.addNewComputer(computer));
        assertNotEquals(HttpStatusEnum.CREATED, computerAddService.addNewComputer(computer));
    }
}