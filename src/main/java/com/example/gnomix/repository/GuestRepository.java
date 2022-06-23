package com.example.gnomix.repository;

import com.example.gnomix.domain.dao.Gender;
import com.example.gnomix.domain.dao.Guest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class GuestRepository {

    List<Guest> guests = new ArrayList<>();

    public GuestRepository() {
        Guest guest = new Guest("Paweł", "Cwik", LocalDate.of(1986, 11, 13), Gender.MALE);
        Guest gabriel = new Guest("Gabriel", "Cwik", LocalDate.of(2016, 12, 13), Gender.MALE);
        guests.add(guest);
        guests.add(gabriel);
    }
    public List<Guest> findAll() {
        return guests;
    }

    public void createNewGuest(String firstName, String lastName, LocalDate birthdate, Gender gender) {
        Guest newOne = new Guest(firstName, lastName, birthdate, gender);
       guests.add(newOne);
    }
    public void removeById(long id){
        Guest guestToBeDelete = getById(id);
                /*guests.stream()
                .filter(guest -> guest.getId() == id)
                .findFirst()
                .orElseThrow();*/
        guests.remove(guestToBeDelete);
    }

    public Guest getById(final long id) {
        return guests.stream()
                .filter(guest -> guest.getId() == id)
                .findFirst()
                .orElseThrow();
    }
}
