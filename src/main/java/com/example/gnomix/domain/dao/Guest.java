package com.example.gnomix.domain.dao;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Setter(value = AccessLevel.NONE)
@NoArgsConstructor
@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long id;
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
