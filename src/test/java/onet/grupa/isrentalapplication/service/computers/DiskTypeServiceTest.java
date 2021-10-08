package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.devices.entities.DiskType;
import onet.grupa.isrentalapplication.devices.entities.DiskTypeRepository;
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
public class DiskTypeServiceTest {

    @Autowired
    DiskTypeService diskTypeService;

    @Autowired
    DiskTypeRepository diskTypeRepository;

    @Before
    public void setUp(){
        DiskType disk1 = new DiskType("SSD");
        diskTypeRepository.save(disk1);
        DiskType disk2 = new DiskType("HDD");
        diskTypeRepository.save(disk2);
    }

    @After
    public void tearDown(){
        diskTypeRepository.deleteAll();
    }

    @Test
    public void checkIfIdIsNotNull(){
        assertNotNull(diskTypeService.getDiskTypeByName("SSD").orElseThrow().getId());
    }

    @Test
    public void getOSFromDBTest(){

        assertTrue(diskTypeService.getDiskTypeByName("SSD").isPresent());
        assertFalse(diskTypeService.getDiskTypeByName("Discette").isPresent());
        assertEquals(2, diskTypeRepository.findAll().size());
    }

    @Test
    public void addDiskTypeToDBTest() {

        DiskType testDisk = new DiskType("FLASH");
        DiskType testDisk2 = new DiskType("SSD");
        DiskType testDisk3 = new DiskType("");

        assertEquals(HttpStatusEnum.CREATED, diskTypeService.addNewDiskType(testDisk));
        assertEquals(HttpStatusEnum.CONFLICT, diskTypeService.addNewDiskType(testDisk2));
        assertEquals(HttpStatusEnum.BADREQUEST, diskTypeService.addNewDiskType(testDisk3));
    }
}