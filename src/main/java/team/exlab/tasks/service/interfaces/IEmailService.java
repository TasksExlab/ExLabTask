package team.exlab.tasks.service.interfaces;

import jakarta.mail.MessagingException;

import java.io.IOException;

public interface IEmailService {

    void sendSimpleMessage(String to, String subject, String text);

    void sendMessageWithAttachment(String to, String subject, String htmlBody) throws MessagingException, IOException;
}
