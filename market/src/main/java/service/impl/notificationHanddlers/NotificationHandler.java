package service.impl.notificationHanddlers;

import javax.mail.MessagingException;

public interface NotificationHandler<T, M> {
    void notify(T subject, M notification) throws MessagingException;
}
