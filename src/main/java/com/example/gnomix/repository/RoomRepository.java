package com.example.gnomix.repository;

import com.example.gnomix.domain.dao.BedType;
import com.example.gnomix.domain.dao.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {

    List<Room> rooms = new ArrayList<>();

    public List<Room> findAll(){

        Room room = new Room("1408", List.of(BedType.SINGLE));
        Room r = new Room("1409", List.of(BedType.DOUBLE));
        return Arrays.asList(room, r);
    }

    public Room createNewRoom(String number,List<BedType> beds) {
        Room newOne = new Room(number,beds);
        this.rooms.add(newOne);
        return newOne;
    }
}
