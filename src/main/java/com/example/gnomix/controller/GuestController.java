package com.example.gnomix.controller;

import com.example.gnomix.domain.dao.Guest;
import com.example.gnomix.domain.dto.GuestDto;
import com.example.gnomix.mapper.GuestMapper;
import com.example.gnomix.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("guests")
public class GuestController {
    private final GuestService guestService;

    @GetMapping
    public String guests(Model model) {
        model.addAttribute("guests", guestService.findAll());
        return "guests";
    }

    @GetMapping(path = "/create")
    public String createNewGuest() {
        return "createNewGuest";
    }

    @PostMapping
    public String handleCreateNewGuest(GuestDto dto) {
       guestService.createNewGuest(dto);
        return "redirect:guests";
    }
    @GetMapping("/delete/{id}")
    public String removeGuest(@PathVariable long id){
        guestService.removeById(id);
        return "redirect:/guests";
    }
    @GetMapping("/update/{id}")
    public String updateGuest(@PathVariable long id,Model model) {
        Guest guest = guestService.getById(id);
        model.addAttribute("guest", guest);
        return "updateGuest";
    }
    @PostMapping("/update")
    public String updateGuest(GuestDto guestDto) {
        guestService.update(guestDto);
        return "redirect:/guests";
    }
}
