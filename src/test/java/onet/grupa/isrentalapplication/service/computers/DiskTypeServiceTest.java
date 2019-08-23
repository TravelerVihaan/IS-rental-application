package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.domain.computers.DiskType;
import onet.grupa.isrentalapplication.repository.computers.DiskTypeRepository;
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
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DiskTypeServiceTest {

    @Autowired
    DiskTypeService diskTypeService;

    @Autowired
    DiskTypeRepository diskTypeRepository;

    @Before
    public void setUp(){
        DiskType disk1 = new DiskType("SSD");
        disk1.setId(1L);
        diskTypeRepository.save(disk1);
        DiskType disk2 = new DiskType("HDD");
        disk1.setId(2L);
        diskTypeRepository.save(disk2);
    }

    @After
    public void tearDown(){
        diskTypeRepository.deleteAll();
    }

    @Test
    public void getOSFromDBTest(){

        assertTrue(diskTypeService.getDiskTypeById(1L).isPresent());
        assertFalse(diskTypeService.getDiskTypeById(3L).isPresent());
        assertEquals(2, diskTypeRepository.findAll().size());
    }

    @Test
    public void addDiskTypeToDBTest() {

        DiskType testDisk = new DiskType("FLASH");
        testDisk.setId(3L);

        DiskType testDisk2 = new DiskType("SSD");
        testDisk.setId(4L);

        DiskType testDisk3 = new DiskType("");
        testDisk.setId(5L);

        assertEquals(HttpStatusEnum.CREATED, diskTypeService.addNewDiskType(testDisk));
        assertEquals(HttpStatusEnum.CONFLICT, diskTypeService.addNewDiskType(testDisk2));
        assertEquals(HttpStatusEnum.BADREQUEST, diskTypeService.addNewDiskType(testDisk3));
    }
}