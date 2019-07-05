package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.OperatingSystem;
import onet.grupa.isrentalapplication.repository.computers.OperatingSystemRepository;
import org.junit.jupiter.api.*;
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
class OperatingSystemServiceTest {


    @Autowired
    OperatingSystemService operatingSystemService;

    @Autowired
    OperatingSystemRepository operatingSystemRepository;

    @Test
    void getOSFromDBTest(){
        OperatingSystem os1 = new OperatingSystem("Windows 7");
        os1.setId(1L);
        operatingSystemRepository.save(os1);
        OperatingSystem os2 = new OperatingSystem("Windows 10");
        os2.setId(2L);
        operatingSystemRepository.save(os2);

        assertEquals(HttpStatus.OK,operatingSystemService.getResponseWithOperatingSystem(1L).getStatusCode());
        assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND), operatingSystemService.getResponseWithOperatingSystem(3L));
        assertEquals(2, operatingSystemRepository.findAll().size());

        operatingSystemRepository.deleteAll();

    }

    @Test
    void addOSToDBTest(){
        OperatingSystem os1 = new OperatingSystem("Windows 7");
        os1.setId(1L);
        operatingSystemRepository.save(os1);
        OperatingSystem os2 = new OperatingSystem("Windows 10");
        os2.setId(2L);
        operatingSystemRepository.save(os2);

        OperatingSystem testOS = new OperatingSystem("Linux");
        testOS.setId(3L);

        OperatingSystem testOS2 = new OperatingSystem("Windows 10");
        testOS2.setId(4L);

        OperatingSystem testOS3 = new OperatingSystem("");
        testOS3.setId(5L);

        assertEquals(new ResponseEntity(HttpStatus.CREATED), operatingSystemService.addNewOperatingSystem(testOS));
        assertEquals(new ResponseEntity(HttpStatus.CONFLICT), operatingSystemService.addNewOperatingSystem(testOS2));
        assertEquals(new ResponseEntity(HttpStatus.BAD_REQUEST), operatingSystemService.addNewOperatingSystem(testOS3));

        operatingSystemRepository.deleteAll();
    }
}