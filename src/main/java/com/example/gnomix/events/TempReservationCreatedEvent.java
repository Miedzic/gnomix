package com.example.gnomix.events;

import lombok.Data;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;
@Getter
public class TempReservationCreatedEvent extends ApplicationEvent {

    public final LocalDateTime creationDate;
    public final String email;
    public final long reservationId;

    public TempReservationCreatedEvent(Object context, String email, long reservationId) {
        super(context);
        this.creationDate = LocalDateTime.now();
        this.reservationId = reservationId;
        this.email = email;
    }
}
