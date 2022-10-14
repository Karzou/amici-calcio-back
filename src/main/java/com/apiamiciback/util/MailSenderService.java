package com.apiamiciback.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class MailSenderService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendEmail(String toEmail, String subject, String body) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setFrom("kvanconingsloo@gmail.com"); // to change with client
        message.setText(body + " " + new Date(System.currentTimeMillis()));
        message.setTo(toEmail);

        mailSender.send(message);

        log.info("Mail sent successfully to {}", toEmail);
    }
}
