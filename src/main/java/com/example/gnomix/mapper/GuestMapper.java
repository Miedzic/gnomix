package com.example.gnomix.mapper;

import com.example.gnomix.domain.dao.Guest;
import com.example.gnomix.domain.dto.GuestCreationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GuestMapper {
    GuestCreationDto guestToGuestDto(Guest guest);
    Guest guestDtoToGuest(GuestCreationDto guestCreationDto);
}
