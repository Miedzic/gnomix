package com.example.gnomix.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne(fetch = FetchType.LAZY)
    private Guest owner;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

}
