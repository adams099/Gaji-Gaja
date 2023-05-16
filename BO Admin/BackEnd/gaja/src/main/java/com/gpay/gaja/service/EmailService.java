package com.gpay.gaja.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
