package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.repository.computers.DiskTypeRepository;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
//@ActiveProfiles("test")
class DiskTypeServiceTest {

    @Autowired
    DiskTypeService diskTypeService;

    @Autowired
    DiskTypeRepository diskTypeRepository;

    @BeforeClass
    void setUp(){
        DiskType disk1 = new DiskType("SSD");
        disk1.setId(1L);
        diskTypeRepository.save(disk1);
        DiskType disk2 = new DiskType("HDD");
        disk1.setId(2L);
        diskTypeRepository.save(disk2);
    }

    @AfterClass
    void cleanAfterTests(){
        diskTypeRepository.deleteAll();
    }

    @Test
    void shouldPassWhenDiskWasFound() {
        assertEquals(HttpStatus.OK,diskTypeService.getResponseWithDisk(1L).getStatusCode());
    }

    @Test
    void shouldPassWhenDiskIsNotFound(){
        assertEquals(new ResponseEntity(HttpStatus.NOT_FOUND), diskTypeService.getResponseWithDisk(3L));
    }

    @Test
    void shouldPassWhenDiskWasAdded(){
        DiskType testDisk = new DiskType("FLASH");
        testDisk.setId(3L);
        assertEquals(new ResponseEntity(HttpStatus.CREATED), diskTypeService.addNewDiskType(testDisk));
    }

    @Test
    void shouldPassWhenReturnConflictBecauseOfTypeOfDisk(){
        DiskType disk1 = new DiskType("SSD");
        disk1.setId(1L);
        diskTypeRepository.save(disk1);
        DiskType testDisk = new DiskType("SSD");
        testDisk.setId(4L);
        assertEquals(new ResponseEntity(HttpStatus.CONFLICT), diskTypeService.addNewDiskType(testDisk));
    }

    @Test
    void shouldPassWhenReturnBadRequest(){
        DiskType testDisk = new DiskType("");
        testDisk.setId(5L);
        assertEquals(new ResponseEntity(HttpStatus.BAD_REQUEST), diskTypeService.addNewDiskType(testDisk));
    }
}