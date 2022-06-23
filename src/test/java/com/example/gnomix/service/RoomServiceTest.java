package com.example.gnomix.service;

import com.example.gnomix.domain.dao.BedType;
import com.example.gnomix.repository.RoomRepository;
import com.example.gnomix.service.impl.RoomServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoomServiceTest {
    @Test
    public void createNewRoomValidData() {
        //give
        RoomRepository roomRepository = Mockito.mock(RoomRepository.class);
        ArgumentCaptor<String> numberCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<List<BedType>> bedsCaptor = ArgumentCaptor.forClass(List.class);
        RoomService rs = new RoomServiceImpl(roomRepository);
        //when
        rs.createNewRoom("102", "2+1+1");
        //then
        Mockito.verify(roomRepository).createNewRoom(numberCaptor.capture(), bedsCaptor.capture());
        assertEquals("102", numberCaptor.getValue());
        assertEquals(3, bedsCaptor.getValue().size());
        assertEquals(BedType.DOUBLE, bedsCaptor.getValue().get(0));
        assertEquals(BedType.SINGLE, bedsCaptor.getValue().get(1));
        assertEquals(BedType.SINGLE, bedsCaptor.getValue().get(2));
    }
}