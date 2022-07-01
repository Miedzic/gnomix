package com.example.gnomix.domain.dto;

import com.example.gnomix.domain.dao.Gender;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
public class GuestDto {

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @Past(message = "Data urodzenia musi być w przeszłości")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)

    private final LocalDate dateOfBirth;

    private final Gender gender;
}
