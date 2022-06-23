package com.example.gnomix.service.impl;

import com.example.gnomix.domain.dao.BedType;
import com.example.gnomix.domain.dao.Room;
import com.example.gnomix.repository.RoomRepository;
import com.example.gnomix.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room createNewRoom(final String number, final String bedsDesc) {
        String[] splitedBedDec = bedsDesc.split("\\+");
        List<BedType> beds = Arrays.stream(splitedBedDec)
                .map(stringToBedTypeMapping)
                .collect(Collectors.toList());
        return this.roomRepository.createNewRoom(number, beds);
    }

    private final Function<String, BedType> stringToBedTypeMapping = value -> {
        if ("1".equals(value)) {
            return BedType.SINGLE;
        } else if ("2".equals(value)) {
            return BedType.DOUBLE;
        } else {
            throw new IllegalArgumentException();
        }
    };
}
