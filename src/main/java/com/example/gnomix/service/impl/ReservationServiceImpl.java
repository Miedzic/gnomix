package com.example.gnomix.service.impl;

import com.example.gnomix.domain.dao.Reservation;
import com.example.gnomix.domain.dao.Room;
import com.example.gnomix.repository.ReservationRepository;
import com.example.gnomix.repository.RoomRepository;
import com.example.gnomix.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Room> getAvailableRooms(final LocalDate from, final LocalDate to, final int size) {
        if(size<0|| size>10){
           throw new IllegalArgumentException("Wrong size param [1-10]");
        }
        if(from.isEqual(to) || to.isBefore(from)){
            throw new IllegalArgumentException("Wrong dates");
        }
        return roomRepository.findAll();
    }
}
