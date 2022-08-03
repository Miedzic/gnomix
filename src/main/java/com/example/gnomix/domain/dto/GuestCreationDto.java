package com.example.gnomix.domain.dto;

import com.example.gnomix.domain.dao.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
public class GuestCreationDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Past(message = "Data urodzenia musi być w przeszłości")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dateOfBirth;

    private Gender gender;

    private boolean vip;

    GuestCreationDto() {
        System.out.println("DEFAULT!");
    }

    GuestCreationDto(String firstName, String lastName, LocalDate dateOfBirth, Gender gender, String vip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;

        if (vip == null || !vip.equals("on")) {
            this.vip = false;
        } else this.vip = true;
    }


}
