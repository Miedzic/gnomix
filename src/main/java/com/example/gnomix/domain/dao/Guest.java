package com.example.gnomix.domain.dao;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Guest {
    private final long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;

    public Guest(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public void update(String firstName, String lastName, LocalDate dateOfBirth, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

}
