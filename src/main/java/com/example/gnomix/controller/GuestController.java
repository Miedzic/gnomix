package com.example.gnomix.controller;

import com.example.gnomix.domain.dto.GuestDto;
import com.example.gnomix.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
public class GuestController {
    private final GuestService guestService;

    @GetMapping(path = "/guests")
    public String guests(Model model) {
        model.addAttribute("guests", guestService.findAll());
        return "guests";
    }

    @GetMapping(path = "/createNewGuest")
    public String createNewGuest() {
        return "createNewGuest";
    }

    @PostMapping(path = "/createNewGuest")
    public String handleCreateNewGuest(GuestDto dto) {
       guestService.createNewGuest(dto);
        return "redirect:guests";
    }
}
