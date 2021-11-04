package com.github.vihaan.isrentalapp.rentals.notifications;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MailNotificationCreator {

    private final Set<String> notificationRecipents = new HashSet<>();
    private final Set<String> warningRecipents = new HashSet<>();
    private final Set<String> alertRecipents = new HashSet<>();
    private JavaMailSender javaMailSender;

    @Autowired
    public MailNotificationCreator(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    private static final String MAIL_FROM = "example@example";
    private static final String NOTIFICATION_MAIL_SUBJECT = "...";
    private static final String WARNING_MAIL_SUBJECT = "...";
    private static final String ALERT_MAIL_SUBJECT = "...";
}
