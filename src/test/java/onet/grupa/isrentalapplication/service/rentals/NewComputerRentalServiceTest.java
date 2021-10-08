package onet.grupa.isrentalapplication.service.rentals;

import onet.grupa.isrentalapplication.devices.entities.*;
import onet.grupa.isrentalapplication.rentals.entities.ComputerRental;
import onet.grupa.isrentalapplication.rentals.entities.RentStatus;
import onet.grupa.isrentalapplication.domain.users.User;
import onet.grupa.isrentalapplication.devices.entities.ComputerStatusRepository;
import onet.grupa.isrentalapplication.rentals.entities.ComputerRentalRepository;
import onet.grupa.isrentalapplication.service.HttpStatusEnum;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NewComputerRentalServiceTest {

    @Mock
    private ComputerRentalRepository computerRentalRepository;
    @Mock
    private ComputerService computerService;
    @Mock
    private ComputerStatusRepository computerStatusRepository;

    private NewComputerRentalService newComputerRentalService;
    private ComputerRental computerRental;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        newComputerRentalService = new NewComputerRentalService(computerRentalRepository,computerService,computerStatusRepository);
        computerRental = initializeRental();
    }

    @Test
    public void shouldReturnBadRequestBecauseOfComputerNotExist(){
        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.empty());
        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRental));
    }

    @Test
    public void shouldReturnBadRequestBecauseWrongDates(){
        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.of(initializeComputer()));

        computerRental.setStartRentalDate(LocalDate.now().plusDays(3));
        computerRental.setEndRentalDate(LocalDate.now().plusDays(2));
        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRental));

        computerRental.setStartRentalDate(LocalDate.now().minusDays(3));
        computerRental.setEndRentalDate(LocalDate.now().minusDays(2));
        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRental));
    }

    @Test
    public void shouldReturnBadRequestBecauseWrongRentalProperties(){
        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.of(initializeComputer()));

        computerRental.setEndRentalDate(null);
        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRental));
        computerRental = initializeRental();

        computerRental.setStartRentalDate(null);
        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRental));
        computerRental = initializeRental();

        computerRental.setRentedComputer(null);
        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRental));
        computerRental = initializeRental();

        computerRental.setRentingPersonEmail("wrongemail");
        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRental));
        computerRental = initializeRental();
    }

    @Test
    public void shouldReturnConflictBecauseOfExistingRental(){
        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.of(initializeComputer()));
        Mockito.when(computerRentalRepository
                .findAllByRentedComputer_OtnumberAndEndRentalDateIsAfterAndRentStatus_Status(any(String.class),any(LocalDate.class),any(String.class)))
                .thenReturn(initializeRentalsList());
        computerRental.setStartRentalDate(LocalDate.now().plusDays(1));
        computerRental.setEndRentalDate(LocalDate.now().plusDays(4));
        assertEquals(HttpStatusEnum.CONFLICT,newComputerRentalService.addNewComputerRental(computerRental));
    }

    @Test
    public void shouldCreateNewComputerRental(){
        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.of(initializeComputer()));
        Mockito.when(computerRentalRepository
                .findAllByRentedComputer_OtnumberAndEndRentalDateIsAfterAndRentStatus_Status(any(String.class),any(LocalDate.class),any(String.class)))
                .thenReturn(new ArrayList<>());
        computerRental.setStartRentalDate(LocalDate.now().plusDays(1));
        computerRental.setEndRentalDate(LocalDate.now().plusDays(4));

        assertEquals(HttpStatusEnum.CREATED,newComputerRentalService.addNewComputerRental(computerRental));
    }

    private ComputerRental initializeRental(){
        ComputerRental rental = new ComputerRental();
        rental.setRentedComputer(initializeComputer());
        rental.setRentingPersonEmail("test@test");
        rental.setRentingPersonName("test");
        rental.setRentStatus(new RentStatus("available"));
        rental.setStartRentalDate(LocalDate.now().minusDays(1));
        rental.setStartRentalDate(LocalDate.now().plusDays(1));
        User user = new User("username","password","name","surname");
        rental.setWhoSetStatus(user);
        return rental;
    }

    private Computer initializeComputer(){
        ComputerProducer computerProducer = new ComputerProducer("Apple");
        ComputerModel computerModel = new ComputerModel("MacBook",computerProducer);
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

    private List<ComputerRental> initializeRentalsList(){
        ComputerRental rental = initializeRental();
        rental.setStartRentalDate(LocalDate.now().minusDays(3));
        rental.setEndRentalDate(LocalDate.now().minusDays(1));
        ComputerRental rental2 = initializeRental();
        rental.setStartRentalDate(LocalDate.now().plusDays(3));
        rental.setEndRentalDate(LocalDate.now().plusDays(5));
        return List.of(rental,rental2);
    }
}