package com.github.vihaan.isrentalapp.rentals.notifications;

import com.github.vihaan.isrentalapp.rentals.ComputerRental;
import com.github.vihaan.isrentalapp.rentals.entities.RentalsDAOFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MailNotifier {

    private final RentalsDAOFacade rentalsDAOFacade;

    @Autowired
    public MailNotifier(RentalsDAOFacade rentalsDAOFacade) {
        this.rentalsDAOFacade = rentalsDAOFacade;
    }

    public void sendMails() {
        List<ComputerRental> allRentalsToNotification = collectDataForMailNotifications();
    }

    private List<ComputerRental> collectDataForMailNotifications(){
        return rentalsDAOFacade.collectRentalsForNotifications();
    }
}
