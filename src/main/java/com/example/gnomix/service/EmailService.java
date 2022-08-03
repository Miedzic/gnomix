package com.example.gnomix.service;

public interface EmailService {
    void sendConfirmationEmail(String email, long reservationId);
}
