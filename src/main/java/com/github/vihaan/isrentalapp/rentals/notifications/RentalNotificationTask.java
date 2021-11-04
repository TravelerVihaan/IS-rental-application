package com.github.vihaan.isrentalapp.rentals.notifications;

import com.github.vihaan.isrentalapp.service.mails.ReminderMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RentalNotificationTask {

    private ReminderMailService reminderMailService;

    @Autowired
    public RentalNotificationTask(ReminderMailService reminderMailService){
        this.reminderMailService = reminderMailService;
    }

    @Scheduled(cron = "0 0 9 * * MON-FRI")
    public void remindAboutRentals(){
        reminderMailService.sendMailsWithReminds();
    }
}
