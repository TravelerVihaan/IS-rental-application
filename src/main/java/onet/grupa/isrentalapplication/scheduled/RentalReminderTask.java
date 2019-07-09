package onet.grupa.isrentalapplication.scheduled;

import onet.grupa.isrentalapplication.service.mails.ReminderMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RentalReminderTask {

    private ReminderMailService reminderMailService;

    @Autowired
    public RentalReminderTask(ReminderMailService reminderMailService){
        this.reminderMailService = reminderMailService;
    }

    @Scheduled(cron = "0 0 9 * * MON-FRI")
    public void remindAboutRentals(){
        reminderMailService.sendMailsWithReminds();
    }
}
