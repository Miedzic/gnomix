package com.example.gnomix.repository;

import com.example.gnomix.domain.dao.Room;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {
    public List<Room> findAll(){
        Room room = new Room("1408");
        Room r = new Room("1409");
        return Arrays.asList(room,r);
    }
}
