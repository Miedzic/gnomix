package com.example.gnomix.domain.reports;

import com.example.gnomix.events.TempReservationCreatedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


// obsługa eventów 1 klasa - 1 event
@Component
public class HandleTempReservationCreateEvent implements ApplicationListener<TempReservationCreatedEvent> {
    @Override
    public void onApplicationEvent(final TempReservationCreatedEvent event) {
        System.out.println("Handle event by implementing AppListener");

    }
}
