package com.example.gnomix.controller;

import com.example.gnomix.domain.dao.Room;
import com.example.gnomix.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping
    public String reservations(Model model) {
        model.addAttribute("reservations", reservationService.getAll());
        return "reservations";

    }

    @GetMapping("/create/stepone")
    public String beginCreationWizard() {
        return "reservationStepOne";
    }

    @PostMapping("/create/steptwo")
    public String creationWizardStepTwo(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate, int size, Model model) {
        List<String> errors = new ArrayList<>();
        if(size<0|| size>10){
            errors.add("Nieprawidłowa ilość osób, pokoje (apartamenty) mieszczą maksymalnie 10 osób");
        }
        if(fromDate.isEqual(toDate) || toDate.isBefore(fromDate)){
            errors.add("nieprawidłowe daty rezerwacji");
        }
        if(errors.isEmpty()){
            List<Room> rooms = reservationService.getAvailableRooms(fromDate, toDate, size);
            model.addAttribute("rooms",rooms);
            model.addAttribute("fromDate",fromDate);
            model.addAttribute("toDate",toDate);
            return "reservationStepTwo";
        } else{
            model.addAttribute("errors",errors);
            return "reservationsStepOne";
        }


    }
}
