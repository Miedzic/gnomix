package com.example.gnomix.util;


import com.example.gnomix.events.TempReservationCreatedEvent;
import com.example.gnomix.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HandleMailTempReservationCreateEvent implements ApplicationListener<TempReservationCreatedEvent> {

    private final EmailService emailService;
    @Override
    public void onApplicationEvent(final TempReservationCreatedEvent event) {
        System.out.println("MAIL: Handle event by implementing AppListener");
        emailService.sendConfirmationEmail(event.getEmail(),event.getReservationId());
    }
}
