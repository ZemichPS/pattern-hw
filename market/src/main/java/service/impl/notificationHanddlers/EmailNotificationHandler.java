package service.impl.notificationHanddlers;

import model.Customer;
import model.Notification;
import service.api.EmailService;

import javax.mail.*;

public class EmailNotificationHandler implements NotificationHandler<Customer, Notification> {

    private final EmailService emailService;

    public EmailNotificationHandler(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void notify(Customer subject, Notification notification) throws MessagingException {
        emailService.send(subject.getEmail(), notification);
    }
}
