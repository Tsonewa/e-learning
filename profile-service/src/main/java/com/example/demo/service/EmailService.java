package com.example.demo.service;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);

    void sendMessageWithAttachment(String to,
                                   String subject,
                                   String text);


}
