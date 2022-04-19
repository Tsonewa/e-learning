package com.example.demo.service.impl;

import com.example.demo.service.EmailService;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

import static com.example.demo.constants.Constants.EMAIL_ADDRESS;

@Service
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender emailSender;
    private final SimpleMailMessage template;

    public EmailServiceImpl(JavaMailSender emailSender, SimpleMailMessage template) {

        this.emailSender = emailSender;
        this.template = template;
    }

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(EMAIL_ADDRESS);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(EMAIL_ADDRESS);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
