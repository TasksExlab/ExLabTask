package team.exlab.tasks.service.impl;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import team.exlab.tasks.service.interfaces.IEmailService;

import java.io.IOException;

import static team.exlab.tasks.util.EmailUtil.NOREPLY_ADDRESS;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {
    private final JavaMailSender mailSender;

    @Value("classpath:/static/")
    private Resource resourceFile;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(NOREPLY_ADDRESS);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String htmlBody)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setFrom(NOREPLY_ADDRESS);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, false);
//        helper.addInline("styles.css", resourceFile.createRelative("css/styles.css"));
//        helper.addInline("logo_medium 1.png", resourceFile.createRelative("assets/logo_medium 1.png"));
//        helper.addInline("Illustration.png", resourceFile.createRelative("assets/Illustration.png"));
        mailSender.send(message);
    }
}

