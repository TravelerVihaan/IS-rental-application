package com.github.vihaan.isrentalapp.service.computers;

import com.github.vihaan.isrentalapp.devices.NewDeviceCreator;
import com.github.vihaan.isrentalapp.devices.entities.*;
import com.github.vihaan.isrentalapp.service.HttpStatusEnum;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NewDeviceCreatorTest {

    private ComputerEntity computerEntity;

    @Mock
    private ComputerRepository computerRepository;

    @InjectMocks
    private NewDeviceCreator newDeviceCreator;

    @Before
    public void setUp(){
        ComputerProducer computerProducer = new ComputerProducer("Apple");
        ComputerModel computerModel = new ComputerModel("MacBook",computerProducer);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        computerEntity = new ComputerEntity();
        computerEntity.setOtnumber("1234/56/7890/IT/KR");
        computerEntity.setSerialNumber("XYZABCD1312");
        computerEntity.setOperatingSystem(os);
        computerEntity.setDiskType(diskType);
        computerEntity.setComputerModel(computerModel);
        computerEntity.setComputerStatus(computerStatus);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() {
        computerEntity = null;
    }

    @Test
    public void shouldAddComputerAndReturnCREATED() {
        Mockito.when(computerRepository.findByOtnumber(computerEntity.getOtnumber())).thenReturn(null);
        assertEquals(HttpStatusEnum.CREATED, newDeviceCreator.addNewComputer(computerEntity));
        assertNotEquals(HttpStatusEnum.BADREQUEST, newDeviceCreator.addNewComputer(computerEntity));
        assertNotEquals(HttpStatusEnum.CONFLICT, newDeviceCreator.addNewComputer(computerEntity));
    }

    @Test
    public void shouldReturnBADREQUESTEnum(){
        computerEntity.setSerialNumber(null);
        assertEquals(HttpStatusEnum.BADREQUEST, newDeviceCreator.addNewComputer(computerEntity));
        assertNotEquals(HttpStatusEnum.CONFLICT, newDeviceCreator.addNewComputer(computerEntity));
        assertNotEquals(HttpStatusEnum.CREATED, newDeviceCreator.addNewComputer(computerEntity));
    }

    @Test
    public void shouldRejectAddingNewComputerAndReturnCONFLICT(){
        Mockito.when(computerRepository.findByOtnumber(computerEntity.getOtnumber())).thenReturn(computerEntity);
        assertEquals(HttpStatusEnum.CONFLICT, newDeviceCreator.addNewComputer(computerEntity));
        assertNotEquals(HttpStatusEnum.BADREQUEST, newDeviceCreator.addNewComputer(computerEntity));
        assertNotEquals(HttpStatusEnum.CREATED, newDeviceCreator.addNewComputer(computerEntity));
    }
}