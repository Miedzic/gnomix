package com.example.gnomix.domain.dao;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Guest {
    private final String firstName;
    private final String lastName;
    private final LocalDate dateOfBirth;
    private final Gender gender;
}
