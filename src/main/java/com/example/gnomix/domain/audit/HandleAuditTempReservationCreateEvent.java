package com.example.gnomix.domain.audit;


import com.example.gnomix.events.TempReservationCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HandleAuditTempReservationCreateEvent {

    @Async
    @EventListener
    public void HandleTempReservationCreatedEvent(final TempReservationCreatedEvent event) {
        System.out.println("Audit - Handle event by implementing AppListener");

    }
}
