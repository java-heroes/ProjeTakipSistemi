package org.kodluyoruz.projetakipsistemi.core.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl{

    @Autowired
    private JavaMailSender emailSender;

    public boolean sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        try {
            message.setFrom("support@projetakipsistemi.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
        }catch (Exception e){
            return false;
        }
        emailSender.send(message);
        return true;
    }
}