package com.example.gnomix.domain.dto;

import com.example.gnomix.domain.dao.Gender;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class GuestDto {
    private final String firstName;
    private final String lastName;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate dateOfBirth;
    private final Gender gender;
}
