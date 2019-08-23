package onet.grupa.isrentalapplication.service.mails;

import onet.grupa.isrentalapplication.domain.rentals.ComputerRental;
import onet.grupa.isrentalapplication.service.rentals.ComputerRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReminderMailService {

    private JavaMailSender mailSender;
    private ComputerRentalService computerRentalService;

    private final String MAIL_FROM = "is@is.com";
    private final String MAIL_SUBJECT = "[WYPOZYCZALNIA] PROSIMY O ZWROT POZYCZONEGO SPRZETU";
    private final String MAIL_TEXT = "Upłynął termin, który zadeklarowałeś jako datę zwrotu sprzętu. \n" +
                                    " Dział Internal IT prosi o niezwłoczny zwrot wypożyczonego sprzętu.";

    private List<String> mailsList = new ArrayList<>();

    @Autowired
    public ReminderMailService(JavaMailSender mailSender, ComputerRentalService computerRentalService){
        this.computerRentalService = computerRentalService;
        this.mailSender = mailSender;
    }

    /**
     * This method send mail.
     */
    public void sendMailsWithReminds(){
        getCurrentMailsList();
        prepareAndSendReminderMails();
    }

    /**
     * This method sets message parameters like recipients, subject,
     * text of the message.
     */
    private void prepareAndSendReminderMails(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(setRecipientList());
        mailMessage.setFrom(MAIL_FROM);
        mailMessage.setSubject(MAIL_SUBJECT);
        mailMessage.setText(MAIL_TEXT);
        mailSender.send(mailMessage);
    }

    /**
     * This method convert recipients list to an array
     * @return array of recipients of mail.
     */
    private String[] setRecipientList(){
        String[] recipientList = new String[mailsList.size()];
        mailsList.toArray(recipientList);
        return recipientList;
    }

    /**
     * This method get all active rentals
     */
    private void getCurrentMailsList(){
        computerRentalService.getAllComputerRentalsWithStatus("active")
                .ifPresent(this::addCurrentMailsToList);
    }


    /**
     * This method filter terminated rentals.
     * @param rentalsList list of rentals
     */
    private void addCurrentMailsToList(List<ComputerRental> rentalsList){
        rentalsList
                .stream()
                .filter(rental -> rental.getEndRentalDate()
                        .isBefore(LocalDate.now()))
                .forEach(rental -> mailsList.add(rental.getRentingPersonemail()));
    }
}
