package com.github.vihaan.isrentalapp.service.computers;

import com.github.vihaan.isrentalapp.devices.OperatingSystemService;
import com.github.vihaan.isrentalapp.devices.entities.OperatingSystem;
import com.github.vihaan.isrentalapp.devices.entities.OperatingSystemRepository;
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
public class OperatingSystemServiceTest {

    @Autowired
    OperatingSystemService operatingSystemService;

    @Autowired
    OperatingSystemRepository operatingSystemRepository;

    @Before
    public void setUp() {
        OperatingSystem os1 = new OperatingSystem("Windows 7");
        operatingSystemRepository.save(os1);
        OperatingSystem os2 = new OperatingSystem("Windows 10");
        operatingSystemRepository.save(os2);
    }

    @Test
    public void checkIfIdIsNotNull(){
        assertNotNull(operatingSystemService.getOperatingSystemByName("Windows 7").orElseThrow().getId());
    }

    @Test
    public void getOSFromDBTest(){

        assertTrue(operatingSystemService.getOperatingSystemByName("Windows 7").isPresent());
        assertFalse(operatingSystemService.getOperatingSystemByName("Windows XP").isPresent());
        assertEquals(2, operatingSystemService.getAllOperatingSystems().size());
    }

    @After
    public void tearDown(){
        operatingSystemRepository.deleteAll();
    }

    @Test
    public void addOSToDBTest(){

        OperatingSystem testOS = new OperatingSystem("Linux");
        OperatingSystem testOS2 = new OperatingSystem("Windows 10");
        OperatingSystem testOS3 = new OperatingSystem("");

        assertEquals(HttpStatusEnum.CREATED, operatingSystemService.addNewOperatingSystem(testOS));
        assertEquals(HttpStatusEnum.CONFLICT, operatingSystemService.addNewOperatingSystem(testOS2));
        assertEquals(HttpStatusEnum.BADREQUEST, operatingSystemService.addNewOperatingSystem(testOS3));
    }
}