package com.example.gnomix.service;

import com.example.gnomix.domain.dao.Room;

import java.util.List;

public interface RoomService {
    List<Room> findAll();

    Room createNewRoom(String number, String bedsDesc);

    void removeById(long id);

    Room findById(long id);

    void update(long id, String number, String bedsDesc);
}
