package com.example.gnomix.service;

import com.example.gnomix.domain.dao.Reservation;
import com.example.gnomix.domain.dao.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

public interface ReservationService {

    List<Reservation> getAll();


    List<Room> getAvailableRooms(LocalDate from, LocalDate to, int size);

    boolean createTemporaryReservation(long roomId, LocalDate fromDate, LocalDate toDate, String email);


    Predicate<Reservation> reservationContains(LocalDate from, LocalDate to);
    Predicate<Reservation> reservationEndsBetween(LocalDate from, LocalDate to);
    Predicate<Reservation> reservationsStartsBetween(LocalDate from, LocalDate to);

    boolean confirmReservation(long reservationId);

    void removeById(long id);
}
