package onet.grupa.isrentalapplication.service.mails;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.service.rentals.ComputerRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderMailService {

    private JavaMailSender mailSender;
    private ComputerRentalService computerRentalService;

    private final String MAIL_FROM = "";

    private List<String> mailsList;

    @Autowired
    public ReminderMailService(JavaMailSender mailSender, ComputerRentalService computerRentalService){
        this.computerRentalService = computerRentalService;
        this.mailSender = mailSender;
    }

    public void sendMailsWithReminds(){
        getCurrentMailsList();
    }

    private void getCurrentMailsList(){
        computerRentalService.getAllComputerRentalsWithStatus("active").ifPresent(this::addCurrentMailsToList);
    }

    private void addCurrentMailsToList(List<ComputerRental> rentalsList){
        rentalsList.stream().forEach( rental -> mailsList.add(rental.getRentingPersonemail()));
    }
}
