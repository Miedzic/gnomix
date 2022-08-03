package com.example.gnomix.service.impl;

import com.example.gnomix.domain.dao.Reservation;
import com.example.gnomix.domain.dao.Room;
import com.example.gnomix.events.TempReservationCreatedEvent;
import com.example.gnomix.repository.ReservationRepository;
import com.example.gnomix.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomServiceImpl roomService;
    private final ApplicationEventPublisher publisher;

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public List<Room> getAvailableRooms(final LocalDate from, final LocalDate to, final int size) {
        List<Room> availableRooms = new ArrayList<>();

        if (size < 0 || size > 10) {
            throw new IllegalArgumentException("Wrong size param [1-10]");
        }

        if (from.isEqual(to) || to.isBefore(from)) {
            throw new IllegalArgumentException("Wrong dates");
        }

        List<Room> roomsWithProperSize = roomService.getRoomsForSize(size);

        for (final Room room : roomsWithProperSize) {
            if (checkIfRoomIsAvailableForDates(room, from, to)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    public boolean checkIfRoomIsAvailableForDates(final Room room, final LocalDate from, final LocalDate to) {
        List<Reservation> reservations = getAllReservationsForRoom(room);
        Optional<Reservation> any = reservations
                .stream()
                .filter(reservationStartsAtTheSameDate(from)
                        .or(reservationEndsAtTheSameDate(to))
                        .or(reservationsStartsBetween(to, from))
                        .or(reservationEndsBetween(to, from))
                        .or(reservationContains(from, to)))
                .findAny();


        return any.isEmpty();
    }

    public Predicate<Reservation> reservationContains(final LocalDate from, final LocalDate to) {
        return reservation -> reservation.getFromDate().isBefore(from) && reservation.getToDate().isAfter(to);
    }

    public Predicate<Reservation> reservationEndsBetween(final LocalDate to, final LocalDate from) {
        return reservation -> reservation.getToDate().isAfter(from) && reservation.getToDate().isBefore(to);
    }

    public Predicate<Reservation> reservationsStartsBetween(final LocalDate to, final LocalDate from) {
        return reservation -> reservation.getFromDate().isAfter(from) && reservation.getFromDate().isBefore(to);

    }

    @Override
    public boolean confirmReservation(final long reservationId) {
        Optional<Reservation> byId = reservationRepository.findById(reservationId);
        if (byId.isPresent()){
            reservationRepository.save(byId.get());
            byId.get().confirm();
            return true;
        }
        return false;
    }

    @Override
    public void removeById(final long id) {
        reservationRepository.deleteById(id);
    }

    static Predicate<Reservation> reservationEndsAtTheSameDate(final LocalDate to) {
        return reservation -> reservation.getToDate().equals(to);
    }

    static Predicate<Reservation> reservationStartsAtTheSameDate(final LocalDate from) {
        return reservation -> reservation.getFromDate().equals(from);
    }

    public List<Reservation> getAllReservationsForRoom(final Room room) {
        return reservationRepository.findAll()
                .stream()
                .filter(reservation -> reservation.getRoom().getId() == room.getId())
                .collect(Collectors.toList());
    }

    @Override
    public boolean createTemporaryReservation(final long roomId, final LocalDate fromDate, final LocalDate toDate, final String email) {
        Optional<Room> room = roomService.getRoomById(roomId);
        room.ifPresent(r -> {
            Reservation tmp = new Reservation(fromDate, toDate, r, email);
            reservationRepository.save(tmp);
            TempReservationCreatedEvent event = new TempReservationCreatedEvent(this,email,tmp.getId());
            publisher.publishEvent(event);
            System.out.println("UDALO SIE UTWORZYC REZERWACJE");
        });
        return room.isPresent();
    }
}
