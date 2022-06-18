package com.example.gnomix.controller;

import com.example.gnomix.domain.dao.Room;
import com.example.gnomix.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class RoomController {
    private final RoomService roomService;

    @GetMapping(path = "/rooms")
    public String getRooms(Model model) {

        model.addAttribute("rooms", roomService.findAll());
        return "rooms";
    }
}
