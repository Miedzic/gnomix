package com.example.gnomix.service.impl;

import com.example.gnomix.service.EmailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;

    @Value("${gnomix.protocol}")
    private String protocol;

    @Value("${gnomix.domain}")
    private String domain;

    @Value("${gnomix.port}")
    private String port;

    private String confirmationEndpoint = "reservations/confirm";

    @Override
    public void sendConfirmationEmail(final String email, final long reservationId) {
        SimpleMailMessage mail = new SimpleMailMessage();

        String endpoint = "http://localhost:8080/reservations/confirm/" + reservationId;
        String.format("%s://%s:%s/%s/%d", protocol, domain, port, confirmationEndpoint, reservationId);

        mail.setTo(email);
        mail.setFrom("robert.miedzic@gmail.com");
        mail.setSubject("Potwierdź rezerwację");
        mail.setText("Dziękujemy za dokonanie rezerwacji, by ją potwierdzić kliknij w link: " + endpoint);

        sender.send(mail);
    }
}
