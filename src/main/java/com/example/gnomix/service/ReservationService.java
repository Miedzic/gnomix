package com.example.gnomix.service;

import com.example.gnomix.domain.dao.Reservation;
import com.example.gnomix.domain.dao.Room;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    List<Reservation> getAll();


    List<Room> getAvailableRooms(LocalDate from, LocalDate to, int size);
}
