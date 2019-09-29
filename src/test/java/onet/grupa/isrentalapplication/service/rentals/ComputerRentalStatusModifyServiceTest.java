package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComputerRentalStatusModifyServiceTest {

    @Mock
    private ComputerRentalRepository computerRentalRepository;
    @Mock
    private ComputerService computerService;
    @Mock
    private RentStatusService rentStatusService;

    private ComputerRentalStatusModifyService computerRentalStatusModifyService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        computerRentalStatusModifyService = new ComputerRentalStatusModifyService(
                computerRentalRepository, computerService, rentStatusService);
    }

}