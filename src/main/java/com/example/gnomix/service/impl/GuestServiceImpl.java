package com.example.gnomix.service.impl;

import com.example.gnomix.domain.dao.Gender;
import com.example.gnomix.domain.dao.Guest;
import com.example.gnomix.domain.dto.GuestDto;
import com.example.gnomix.repository.GuestRepository;
import com.example.gnomix.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;


    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }


    public void createNewGuest(GuestDto guestDto){
        System.out.println(guestDto);
        guestRepository.createNewGuest(guestDto.getFirstName(),guestDto.getLastName(),guestDto.getDateOfBirth(),guestDto.getGender());
    }
}
