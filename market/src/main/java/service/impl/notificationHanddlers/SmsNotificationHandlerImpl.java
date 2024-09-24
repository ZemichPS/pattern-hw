package service.impl.notificationHanddlers;

import model.Customer;
import model.Notification;

public class SmsNotificationHandlerImpl implements NotificationHandler<Customer, Notification> {

    @Override
    public void notify(Customer subject, Notification notification) {
        String customerName = subject.getName();
        String phoneNumber = subject.getPhone();
        System.out.println("Customer %s with phoneNumber: %s was notified by SMS way. Subject %s: "
                .formatted(customerName, phoneNumber, notification.getSubject()));
    }
}
