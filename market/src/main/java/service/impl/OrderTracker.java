package service.impl;

import model.Notification;
import model.OrderDetails;
import service.api.EmailService;
import service.api.EventListener;

import javax.mail.MessagingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderTracker implements EventListener<OrderDetails> {

    private final String employeeEmail;
    private final EmailService<Notification> emailService;
    private Logger log = Logger.getLogger(OrderTracker.class.getName());

    public OrderTracker(String employeeEmail, EmailService emailService) {
        this.employeeEmail = employeeEmail;
        this.emailService = emailService;
    }

    @Override
    public void update(String eventType, OrderDetails observable) {
        try {
            Notification notification = new Notification("OrderDetails status has been changed", observable.toString());
            emailService.send(employeeEmail, notification);
        } catch (MessagingException e) {
            log.log(Level.ALL, "Failed to notify. Cause: " + e.getCause());
            throw new RuntimeException("Error sending email", e);
        }
    }
}
