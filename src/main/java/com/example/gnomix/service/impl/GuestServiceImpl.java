package com.example.gnomix.service.impl;

import com.example.gnomix.domain.dao.Guest;
import com.example.gnomix.domain.dto.GuestCreationDto;
import com.example.gnomix.domain.dto.GuestUpdateDto;
import com.example.gnomix.repository.GuestRepository;
import com.example.gnomix.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {
    private final GuestRepository guestRepository;

    @Autowired
    public List<Guest> findAll() {
        return this.guestRepository.findAll();
    }

    public void createNewGuest(GuestCreationDto dto) {
        Guest newOne = new Guest(dto.getFirstName(), dto.getLastName(), dto.getDateOfBirth(), dto.getGender(),dto.isVip());
        this.guestRepository.save(newOne);
    }

    @Override
    public void removeById(final Long id) {
        guestRepository.deleteById(id);
    }

    public Guest getById(long id) {
        return this.guestRepository.getById(id);
    }

    public void update(GuestUpdateDto updatedGuest) {
        Guest byId = this.guestRepository.getById(updatedGuest.getId());
        byId.update(
                updatedGuest.getFirstName(),
                updatedGuest.getLastName(),
                updatedGuest.getDateOfBirth(),
                updatedGuest.getGender()
        );
        this.guestRepository.save(byId);
    }
}
