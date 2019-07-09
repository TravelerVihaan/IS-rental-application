package onet.grupa.isrentalapplication.service.mails;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.service.rentals.ComputerRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReminderMailService {

    private JavaMailSender mailSender;
    private ComputerRentalService computerRentalService;

    private final String MAIL_FROM = "";
    private final String MAIL_SUBJECT = "[WYPOZYCZALNIA] PROSIMY O ZWROT POZYCZONEGO SPRZETU";
    private final String MAIL_TEXT = "Upłynął termin, który zadeklarowałeś jako datę zwrotu sprzętu. \n" +
                                    " Dział Internal IT prosi o niezwłoczny zwrot wyporzyczonego sprzętu.";

    private List<String> mailsList;

    @Autowired
    public ReminderMailService(JavaMailSender mailSender, ComputerRentalService computerRentalService){
        this.computerRentalService = computerRentalService;
        this.mailSender = mailSender;
    }

    public void sendMailsWithReminds(){
        getCurrentMailsList();
        prepareAndSendReminderMails();
    }

    private void prepareAndSendReminderMails(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(setRecipientList());
        mailMessage.setFrom(MAIL_FROM);
        mailMessage.setSubject(MAIL_SUBJECT);
        mailMessage.setText(MAIL_TEXT);
        mailSender.send(mailMessage);
    }

    private String[] setRecipientList(){
        String[] recipientList = new String[mailsList.size()];
        mailsList.toArray(recipientList);
        return recipientList;
    }

    private void getCurrentMailsList(){
        computerRentalService.getAllComputerRentalsWithStatus("active")
                .ifPresent(this::addCurrentMailsToList);
    }

    private void addCurrentMailsToList(List<ComputerRental> rentalsList){
        rentalsList
                .stream()
                .filter(rental -> rental.getEndRentalDate()
                        .isBefore(LocalDate.now()))
                .forEach(rental -> mailsList.add(rental.getRentingPersonemail()));
    }
}
