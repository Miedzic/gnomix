package com.example.gnomix.service.impl;

import com.example.gnomix.domain.dao.Room;
import com.example.gnomix.repository.RoomRepository;
import com.example.gnomix.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {
    private final RoomRepository roomRepository;
    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }
}
