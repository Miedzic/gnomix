package com.example.gnomix.domain.reports;

import com.example.gnomix.events.TempReservationCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
// obsługa eventów 1 klasa - wszystkie eventy
@Component
public class HandlerEvents {

    @Async
    @EventListener
    public void handleTempReservationCreatedEvent(TempReservationCreatedEvent event){
        System.out.println("Handle event by annotation");
    }

}
