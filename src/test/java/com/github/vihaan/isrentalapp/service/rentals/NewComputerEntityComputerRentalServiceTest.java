//package com.github.vihaan.isrentalapp.service.rentals;
//
//import com.github.vihaan.isrentalapp.devices.entities.*;
//import onet.grupa.isrentalapplication.devices.entities.*;
//import com.github.vihaan.isrentalapp.rentals.NewComputerRentalService;
//import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalEntity;
//import com.github.vihaan.isrentalapp.rentals.entities.RentStatusEntity;
//import com.github.vihaan.isrentalapp.users.entities.UserEntity;
//import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalRepository;
//import com.github.vihaan.isrentalapp.service.HttpStatusEnum;
//import com.github.vihaan.isrentalapp.devices.oldies.ComputerService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("test")
//public class NewComputerEntityComputerRentalServiceTest {
//
//    @Mock
//    private ComputerRentalRepository computerRentalRepository;
//    @Mock
//    private ComputerService computerService;
//    @Mock
//    private ComputerStatusRepository computerStatusRepository;
//
//    private NewComputerRentalService newComputerRentalService;
//    private ComputerRentalEntity computerRentalEntity;
//
//    @Before
//    public void setUp(){
//        MockitoAnnotations.initMocks(this);
//        newComputerRentalService = new NewComputerRentalService(computerRentalRepository,computerService,computerStatusRepository);
//        computerRentalEntity = initializeRental();
//    }
//
//    @Test
//    public void shouldReturnBadRequestBecauseOfComputerNotExist(){
//        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.empty());
//        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRentalEntity));
//    }
//
//    @Test
//    public void shouldReturnBadRequestBecauseWrongDates(){
//        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.of(initializeComputer()));
//
//        computerRentalEntity.setStartRentalDate(LocalDate.now().plusDays(3));
//        computerRentalEntity.setEndRentalDate(LocalDate.now().plusDays(2));
//        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRentalEntity));
//
//        computerRentalEntity.setStartRentalDate(LocalDate.now().minusDays(3));
//        computerRentalEntity.setEndRentalDate(LocalDate.now().minusDays(2));
//        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRentalEntity));
//    }
//
//    @Test
//    public void shouldReturnBadRequestBecauseWrongRentalProperties(){
//        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.of(initializeComputer()));
//
//        computerRentalEntity.setEndRentalDate(null);
//        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRentalEntity));
//        computerRentalEntity = initializeRental();
//
//        computerRentalEntity.setStartRentalDate(null);
//        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRentalEntity));
//        computerRentalEntity = initializeRental();
//
//        computerRentalEntity.setRentedComputer(null);
//        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRentalEntity));
//        computerRentalEntity = initializeRental();
//
//        computerRentalEntity.setRentingPersonEmail("wrongemail");
//        assertEquals(HttpStatusEnum.BADREQUEST,newComputerRentalService.addNewComputerRental(computerRentalEntity));
//        computerRentalEntity = initializeRental();
//    }
//
//    @Test
//    public void shouldReturnConflictBecauseOfExistingRental(){
//        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.of(initializeComputer()));
//        Mockito.when(computerRentalRepository
//                .findAllByRentedComputer_OtnumberAndEndRentalDateIsAfterAndRentStatus_Status(any(String.class),any(LocalDate.class),any(String.class)))
//                .thenReturn(initializeRentalsList());
//        computerRentalEntity.setStartRentalDate(LocalDate.now().plusDays(1));
//        computerRentalEntity.setEndRentalDate(LocalDate.now().plusDays(4));
//        assertEquals(HttpStatusEnum.CONFLICT,newComputerRentalService.addNewComputerRental(computerRentalEntity));
//    }
//
//    @Test
//    public void shouldCreateNewComputerRental(){
//        Mockito.when(computerService.getComputerByOT(any(String.class))).thenReturn(Optional.of(initializeComputer()));
//        Mockito.when(computerRentalRepository
//                .findAllByRentedComputer_OtnumberAndEndRentalDateIsAfterAndRentStatus_Status(any(String.class),any(LocalDate.class),any(String.class)))
//                .thenReturn(new ArrayList<>());
//        computerRentalEntity.setStartRentalDate(LocalDate.now().plusDays(1));
//        computerRentalEntity.setEndRentalDate(LocalDate.now().plusDays(4));
//
//        assertEquals(HttpStatusEnum.CREATED,newComputerRentalService.addNewComputerRental(computerRentalEntity));
//    }
//
//    private ComputerRentalEntity initializeRental(){
//        ComputerRentalEntity rental = new ComputerRentalEntity();
//        rental.setRentedComputer(initializeComputer());
//        rental.setRentingPersonEmail("test@test");
//        rental.setRentingPersonName("test");
//        rental.setRentStatus(new RentStatusEntity("available"));
//        rental.setStartRentalDate(LocalDate.now().minusDays(1));
//        rental.setStartRentalDate(LocalDate.now().plusDays(1));
//        UserEntity userEntity = new UserEntity("username","password","name","surname");
//        rental.setWhoSetStatus(userEntity);
//        return rental;
//    }
//
//    private ComputerEntity initializeComputer(){
//        ComputerProducer computerProducer = new ComputerProducer("Apple");
//        ComputerModel computerModel = new ComputerModel("MacBook",computerProducer);
//        ComputerStatus computerStatus = new ComputerStatus("rented");
//        DiskType diskType = new DiskType("SSD");
//        OperatingSystem os = new OperatingSystem("Windows 10");
//        ComputerEntity computerEntity = new ComputerEntity();
//        computerEntity.setOtnumber("1234/56/7890/IT/KR");
//        computerEntity.setSerialNumber("XYZABCD1312");
//        computerEntity.setOperatingSystem(os);
//        computerEntity.setDiskType(diskType);
//        computerEntity.setComputerModel(computerModel);
//        computerEntity.setComputerStatus(computerStatus);
//        return computerEntity;
//    }
//
//    private List<ComputerRentalEntity> initializeRentalsList(){
//        ComputerRentalEntity rental = initializeRental();
//        rental.setStartRentalDate(LocalDate.now().minusDays(3));
//        rental.setEndRentalDate(LocalDate.now().minusDays(1));
//        ComputerRentalEntity rental2 = initializeRental();
//        rental.setStartRentalDate(LocalDate.now().plusDays(3));
//        rental.setEndRentalDate(LocalDate.now().plusDays(5));
//        return List.of(rental,rental2);
//    }
//}