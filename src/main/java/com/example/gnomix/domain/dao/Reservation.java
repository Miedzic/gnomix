package com.example.gnomix.domain.dao;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Reservation {
    private LocalDate fromDate;
    private LocalDate toDate;
    private Guest guest;
    private Room room;

}
