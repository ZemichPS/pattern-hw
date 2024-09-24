package service.impl;

import model.Notification;
import model.Product;
import service.api.crud.CustomerService;
import service.api.EmailService;
import service.api.EventListener;

import javax.mail.MessagingException;
import java.math.RoundingMode;

public class ProductOnSaleEmailNotificatorListener implements EventListener<Product> {
    private final EmailService<Notification> emailService;
    private final CustomerService customerService;

    public ProductOnSaleEmailNotificatorListener(EmailService<Notification> emailService,
                                                 CustomerService customerService) {
        this.emailService = emailService;
        this.customerService = customerService;
    }


    @Override
    public void update(String eventType, Product observable) {
        customerService.getAll().forEach(customer -> {
            String messageBody;
            if (eventType.equals("NEW_PRODUCT_ON_SALE"))
                messageBody = "Great news! We have new products at discounts. Product: %s. Price %s "
                        .formatted(observable.getName(), observable.getPrice().setScale(2, RoundingMode.DOWN));
            else
                messageBody = "Great news! Great news! We have reduced the prices".formatted(observable.getName(), observable.getPrice().setScale(2, RoundingMode.DOWN));

            try {
                emailService.send(customer.getEmail(), new Notification("sales!", messageBody));
            } catch (MessagingException e) {
                throw new RuntimeException("Failed to send email", e);
            }
        });
    }
}
