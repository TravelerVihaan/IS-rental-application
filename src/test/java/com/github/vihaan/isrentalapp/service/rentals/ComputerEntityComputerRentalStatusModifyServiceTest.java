package com.github.vihaan.isrentalapp.service.rentals;

import com.github.vihaan.isrentalapp.devices.entities.*;
import onet.grupa.isrentalapplication.devices.entities.*;
import com.github.vihaan.isrentalapp.rentals.ComputerRentalStatusModifyService;
import com.github.vihaan.isrentalapp.rentals.RentStatusService;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalEntity;
import com.github.vihaan.isrentalapp.rentals.entities.RentStatusEntity;
import com.github.vihaan.isrentalapp.users.entities.UserEntity;
import com.github.vihaan.isrentalapp.rentals.entities.ComputerRentalRepository;
import com.github.vihaan.isrentalapp.devices.oldies.ComputerService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ComputerEntityComputerRentalStatusModifyServiceTest {

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
        Mockito.doNothing().doThrow(new RuntimeException()).when(computerRentalRepository).save(any(ComputerRentalEntity.class));
        Mockito.doNothing().doThrow(new RuntimeException()).when(computerService).changeComputerStatus(any(String.class),any(Long.class));

        computerRentalStatusModifyService.changeRentalStatus(1,"available");
        verify(computerRentalRepository,times(1)).save(any(ComputerRentalEntity.class));
    }


    private ComputerRentalEntity initializeRental(){
        ComputerRentalEntity rental = new ComputerRentalEntity();
        rental.setRentedComputer(initializeComputer());
        rental.setRentingPersonEmail("test@test");
        rental.setRentingPersonName("test");
        rental.setRentStatus(new RentStatusEntity("waiting"));
        rental.setStartRentalDate(LocalDate.now().minusDays(1));
        rental.setStartRentalDate(LocalDate.now().plusDays(1));
        UserEntity userEntity = new UserEntity("username","password","name","surname");
        rental.setWhoSetStatus(userEntity);
        return rental;
    }

    private ComputerEntity initializeComputer(){
        ComputerProducer computerProducer = new ComputerProducer("Apple");
        ComputerModel computerModel = new ComputerModel("MacBook",computerProducer);
        ComputerStatus computerStatus = new ComputerStatus("available");
        DiskType diskType = new DiskType("SSD");
        OperatingSystem os = new OperatingSystem("Windows 10");
        ComputerEntity computerEntity = new ComputerEntity();
        computerEntity.setOtnumber("1234/56/7890/IT/KR");
        computerEntity.setSerialNumber("XYZABCD1312");
        computerEntity.setOperatingSystem(os);
        computerEntity.setDiskType(diskType);
        computerEntity.setComputerModel(computerModel);
        computerEntity.setComputerStatus(computerStatus);
        return computerEntity;
    }

}