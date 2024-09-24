package service.impl;

import model.Notification;
import service.api.EmailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailServiceImpl implements EmailService<Notification> {
    private String host = "smtp.gmail.com";
    private String port = "587";
    final String username = "your-email@gmail.com"; // ваша почта
    final String password = "your-password";

    @Override
    public void send(String emailTo, Notification notification) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username)); // Отправитель
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo)); // Получатель
        message.setSubject(notification.getSubject());
        message.setText(notification.getBody());
        Transport.send(message);
    }
}
