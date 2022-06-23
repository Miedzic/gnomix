package com.example.gnomix.controller;

import com.example.gnomix.domain.dao.Room;
import com.example.gnomix.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("rooms")
public class RoomController {
    private final RoomService roomService;

    @GetMapping
    public String getRooms(Model model) {

        model.addAttribute("rooms", roomService.findAll());
        return "rooms";
    }
    @GetMapping(path = "/create")
    public String createNewRoom(){
        return "createNewRoom";
    }
    @PostMapping
    public String handleCreateNewRoom(String number, String bedsDesc){
        roomService.createNewRoom(number,bedsDesc);
        return "redirect:rooms";

    }
}
