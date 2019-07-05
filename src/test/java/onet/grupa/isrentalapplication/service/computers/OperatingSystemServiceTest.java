package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.OperatingSystem;
import onet.grupa.isrentalapplication.repository.computers.OperatingSystemRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

        assertTrue(operatingSystemService.getOperatingSystemById(1L).isPresent());
        assertFalse(operatingSystemService.getOperatingSystemById(3L).isPresent());
        assertEquals(2, operatingSystemService.getAllOperatingSystems().get().size());

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

        assertEquals(HttpStatusEnum.CREATED, operatingSystemService.addNewOperatingSystem(testOS));
        assertEquals(HttpStatusEnum.CONFLICT, operatingSystemService.addNewOperatingSystem(testOS2));
        assertEquals(HttpStatusEnum.BADREQUEST, operatingSystemService.addNewOperatingSystem(testOS3));

        operatingSystemRepository.deleteAll();
    }
}