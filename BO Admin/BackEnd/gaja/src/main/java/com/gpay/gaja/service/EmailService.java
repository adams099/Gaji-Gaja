package com.gpay.gaja.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
    void sendEmailV2(String ke,String subject, String body);
    void otp(String ke,String subject, String body);
    void register(String ke,String subject, String body);
}
