package onet.grupa.isrentalapplication.service.computers;

import onet.grupa.isrentalapplication.repository.computers.ComputerRepository;
import org.junit.Before;
import org.junit.runner.Computer;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComputerSearchingServiceTest {

    @Mock
    private ComputerRepository computerRepository;
    @Mock
    private ComputerOrderingService computerOrderingService;

    private ComputerSearchingService computerSearchingService;

    @Before
    public void setUp(){
        computerSearchingService = new ComputerSearchingService(computerRepository,computerOrderingService);
    }

}