package com.example.gnomix.repository;

import com.example.gnomix.domain.dao.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
