package com.example.gnomix.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private LocalDate fromDate;
    private LocalDate toDate;
    private boolean confirmed;
    private LocalDateTime creationDate;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    private Guest owner;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    public Reservation(final LocalDate fromDate, final LocalDate toDate, final Room room, final String email) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.room = room;
        this.email = email;
        this.confirmed = false;
        this.creationDate = LocalDateTime.now();
    }
    public void confirm(){
        this.confirmed = true;
    }
}
