package onet.grupa.isrentalapplication.dtomapper;

import onet.grupa.isrentalapplication.domain.computers.*;
import onet.grupa.isrentalapplication.domain.rentals.RentStatus;
import onet.grupa.isrentalapplication.dto.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DTOTests {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void DiskType_OS_RentStatus_DTOTest(){
        //given
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        RentStatus rentStatus = new RentStatus("accepted");
        ComputerStatus computerStatus = new ComputerStatus("rented");
        //when
        DiskTypeDTO diskTypeDTO = modelMapper.map(diskType, DiskTypeDTO.class);
        OperatingSystemDTO operatingSystemDTO = modelMapper.map(os, OperatingSystemDTO.class);
        RentStatusDTO rentStatusDTO = modelMapper.map(rentStatus, RentStatusDTO.class);
        ComputerStatusDTO computerStatusDTO =
                modelMapper.map(computerStatus, ComputerStatusDTO.class);
        //then
        assertEquals(diskTypeDTO.getDiskType(),"SSD");
        assertEquals(operatingSystemDTO.getOperatingSystem(),"Windows 10");
        assertEquals(rentStatusDTO.getStatus(),"accepted");
        assertEquals(computerStatusDTO.getStatus(),"rented");
    }

    @Test
    public void Computer_Producer_Model_DTOTest(){
        //given
        ComputerProducer computerProducer = new ComputerProducer("Apple");
        ComputerModel computerModel = new ComputerModel("MacBook",computerProducer);
        //when
        ComputerProducerDTO computerProducerDTO =
                modelMapper.map(computerProducer, ComputerProducerDTO.class);
        ComputerModelDTO computerModelDTO =
                modelMapper.map(computerModel, ComputerModelDTO.class);
        //then
        assertEquals(computerProducerDTO.getProducerName(),"Apple");
        assertEquals(computerModelDTO.getComputerProducer(),"Apple");
        assertEquals(computerModelDTO.getModel(),"MacBook");
    }

    @Test
    public void ComputerDTO_Test(){
        //given
        ComputerProducer computerProducer = new ComputerProducer("Apple");
        ComputerModel computerModel = new ComputerModel("MacBook",computerProducer);
        ComputerStatus computerStatus = new ComputerStatus("rented");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");

        Computer computer = new Computer();
        computer.setOtnumber("1234/56/7890/IT/KR");
        computer.setSerialNumber("XYZABCD1312");
        computer.setOperatingSystem(os);
        computer.setDiskType(diskType);
        computer.setComputerModel(computerModel);
        computer.setComputerStatus(computerStatus);

        //when
        ComputerDTO computerDTO = modelMapper.map(computer, ComputerDTO.class);

        //then
        assertEquals(computerDTO.getOtnumber(),"1234/56/7890/IT/KR");
        assertEquals(computerDTO.getSerialNumber(),"XYZABCD1312");
        assertEquals(computerDTO.getOperatingSystem(),"Windows 10");
        assertEquals(computerDTO.getDiskType(),"SSD");
        assertEquals(computerDTO.getComputerModelProducerName(),"Apple");
        assertEquals(computerDTO.getModel(),"MacBook");
        assertEquals(computerDTO.getComputerStatus(),"rented");
    }
}
