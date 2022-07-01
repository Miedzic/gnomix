package com.example.gnomix.repository;

import com.example.gnomix.domain.dao.Gender;
import com.example.gnomix.domain.dao.Guest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public interface GuestRepository extends JpaRepository<Guest,Long> {


}
