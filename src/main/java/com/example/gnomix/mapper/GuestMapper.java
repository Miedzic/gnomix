package com.example.gnomix.mapper;

import com.example.gnomix.domain.dao.Guest;
import com.example.gnomix.domain.dto.GuestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestDto guestToGuestDto(Guest guest);
    Guest guestDtoToGuest(GuestDto guestDto);
}
