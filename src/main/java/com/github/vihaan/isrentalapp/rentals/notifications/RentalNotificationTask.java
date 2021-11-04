package com.github.vihaan.isrentalapp.rentals.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RentalNotificationTask {

    private final MailNotifier mailNotifier;

    @Autowired
    public RentalNotificationTask(MailNotifier mailNotifier){
        this.mailNotifier = mailNotifier;
    }

    @Scheduled(cron = "0 0 9 * * MON-FRI")
    public void remindAboutRentals(){
        mailNotifier.sendMails();
    }
}
