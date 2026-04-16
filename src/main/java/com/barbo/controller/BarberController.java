package com.barbo.controller;

import com.barbo.entity.Barber;
import com.barbo.service.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbers") // 👤 USER ACCESS
@RequiredArgsConstructor
public class BarberController {

    private final BarberService barberService;

    // 👤 GET ALL
    @GetMapping
    public List<Barber> getAllBarbers() {
        return barberService.getAllBarbers();
    }

    // 👤 GET BY CITY
    @GetMapping("/city/{city}")
    public List<Barber> getBarbersByCity(@PathVariable String city) {
        return barberService.getBarbersByCity(city);
    }

    // 👤 GET BY ID
    @GetMapping("/{id}")
    public Barber getBarber(@PathVariable String id) {
        return barberService.getBarberById(id);
    }
}