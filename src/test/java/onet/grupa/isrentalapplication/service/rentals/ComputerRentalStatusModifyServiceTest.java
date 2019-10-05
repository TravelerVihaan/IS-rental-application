package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.domain.computers.*;
import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.domain.rentals.RentStatus;
import onet.grupa.isrentalapplication.domain.users.User;
import onet.grupa.isrentalapplication.repository.rentals.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.computers.ComputerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

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

    @Test(expected = Exception.class)
    public void shouldThrowExceptionBecauseOfWrongStatusName(){
        Mockito.when(computerRentalRepository.findById(any(Long.class))).thenReturn(Optional.of(initializeRental()));
        computerRentalStatusModifyService.changeRentalStatus(1,"wrong");
    }

    @Test
    public void shouldCallSaveMethodOnce(){
        Mockito.when(computerRentalRepository.findById(any(Long.class))).thenReturn(Optional.of(initializeRental()));
        Mockito.doNothing().doThrow(new RuntimeException()).when(computerRentalRepository).save(any(ComputerRental.class));
        Mockito.doNothing().doThrow(new RuntimeException()).when(computerService).changeComputerStatus(any(String.class),any(Long.class));

        computerRentalStatusModifyService.changeRentalStatus(1,"available");
        verify(computerRentalRepository,times(1)).save(any(ComputerRental.class));
    }


    private ComputerRental initializeRental(){
        ComputerRental rental = new ComputerRental();
        rental.setRentedComputer(initializeComputer());
        rental.setRentingPersonEmail("test@test");
        rental.setRentingPersonName("test");
        rental.setRentStatus(new RentStatus("waiting"));
        rental.setStartRentalDate(LocalDate.now().minusDays(1));
        rental.setStartRentalDate(LocalDate.now().plusDays(1));
        User user = new User("username","password","name","surname");
        rental.setWhoSetStatus(user);
        return rental;
    }

    private Computer initializeComputer(){
        ComputerProducer computerProducer = new ComputerProducer("Apple");
        ComputerModel computerModel = new ComputerModel("MacBook",computerProducer);
        ComputerStatus computerStatus = new ComputerStatus("available");
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