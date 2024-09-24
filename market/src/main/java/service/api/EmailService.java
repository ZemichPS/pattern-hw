package service.api;

import javax.mail.MessagingException;

public interface EmailService<T> {
    void send(String emailTo, T notification) throws MessagingException;
}
