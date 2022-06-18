package com.example.gnomix.controller;

import com.example.gnomix.domain.dao.Room;
import com.example.gnomix.service.GuestService;
import com.example.gnomix.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoomService roomService;
    @Test
    public void testBasicExample() throws Exception {
        Room room = new Room("1408");

        Mockito.when(roomService.findAll()).thenReturn(Arrays.asList(room));
        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rooms"))
                .andExpect(view().name("rooms"))
                .andExpect(content().string(containsString("1408")));
    }
}
