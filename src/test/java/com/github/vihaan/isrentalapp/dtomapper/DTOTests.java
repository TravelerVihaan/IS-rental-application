package com.github.vihaan.isrentalapp.dtomapper;

import com.github.vihaan.isrentalapp.devices.*;
import com.github.vihaan.isrentalapp.devices.dto.*;
import com.github.vihaan.isrentalapp.devices.entities.*;
import com.github.vihaan.isrentalapp.rentals.dto.ComputerRentalDTO;
import com.github.vihaan.isrentalapp.rentals.dto.RentStatusDTO;
import com.github.vihaan.isrentalapp.users.dto.UserDTO;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRental;
import com.github.vihaan.isrentalapp.rentals.entities.RentStatus;
import com.github.vihaan.isrentalapp.users.entities.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

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
        ComputerModel computerModel = prepareComputerModelToTests();
        ComputerProducer computerProducer = computerModel.getComputerProducer();
        //when
        ComputerProducerDTO computerProducerDTO = modelMapper
                .map(computerProducer, ComputerProducerDTO.class);
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
        Computer computer = prepareComputerToTests();
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

    @Test
    public void ComputerRentalDTO_Test(){
        //given
        Computer computer = prepareComputerToTests();
        RentStatus rentStatus = new RentStatus("accepted");
        ComputerRental computerRental = new ComputerRental();
        computerRental.setStartRentalDate(LocalDate.now());
        computerRental.setEndRentalDate(LocalDate.now());
        computerRental.setRentingPersonEmail("test@test");
        computerRental.setRentingPersonName("Name");
        computerRental.setRentedComputer(computer);
        computerRental.setRentStatus(rentStatus);
        //when
        ComputerRentalDTO computerRentalDTO = modelMapper
                .map(computerRental, ComputerRentalDTO.class);
        //then
        assertEquals(LocalDate.now(),computerRentalDTO.getStartRentalDate());
        assertEquals(LocalDate.now(),computerRentalDTO.getEndRentalDate());
        assertEquals("test@test",computerRentalDTO.getRentingPersonEmail());
        assertEquals("Name",computerRentalDTO.getRentingPersonName());
        assertEquals("accepted",computerRentalDTO.getRentStatus());

        assertEquals("1234/56/7890/IT/KR",computerRentalDTO.getComputerOtnumber());
        assertEquals("XYZABCD1312",computerRentalDTO.getComputerSerialNumber());
        assertEquals("Apple",computerRentalDTO.getComputerProducerName());
        assertEquals("MacBook",computerRentalDTO.getComputerModel());
    }

    public void UserDTO_Test(){
        //given
        User user = new User("username","password","name","surname");
        //when
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        //then
        assertEquals("username",userDTO.getUsername());
        assertEquals("name",userDTO.getName());
        assertEquals("surname",userDTO.getSurname());
    }

    private ComputerModel prepareComputerModelToTests(){
        ComputerProducer computerProducer = new ComputerProducer("Apple");
        return new ComputerModel("MacBook",computerProducer);
    }

    private Computer prepareComputerToTests(){
        ComputerModel computerModel = prepareComputerModelToTests();
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
        return computer;
    }
}
