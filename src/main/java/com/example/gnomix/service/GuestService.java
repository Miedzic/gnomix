package com.example.gnomix.service;

import com.example.gnomix.domain.dao.Guest;
import com.example.gnomix.domain.dto.GuestDto;

import java.util.List;

public interface GuestService {
    List<Guest> findAll();

    void createNewGuest(GuestDto dto);
}
