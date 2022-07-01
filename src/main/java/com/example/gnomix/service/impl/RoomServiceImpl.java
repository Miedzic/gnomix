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

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room createNewRoom(String roomNumber, String bedsDesc) {
        List<BedType> beds = getBedTypesList(bedsDesc);
        Room newOne = new Room(roomNumber, beds);
        return this.roomRepository.save(newOne);
    }

    public void removeById(long id) {
        this.roomRepository.deleteById(id);
    }

    public Room findById(long id) {
        return this.roomRepository.getById(id);
    }

    public void update(long id, String number, String bedsDesc) {
        Room toUpdate = this.roomRepository.getById(id);
        List<BedType> beds = getBedTypesList(bedsDesc);
        toUpdate.update(number, beds);
        this.roomRepository.save(toUpdate);
    }

    private List<BedType> getBedTypesList(String bedsDesc) {
        String[] splitedBedDec = bedsDesc.split("\\+");
        return Arrays.stream(splitedBedDec)
                .map(stringToBedTypeMapping)
                .collect(Collectors.toList());
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
