package com.barbo.controller;

import com.barbo.entity.Barber;
import com.barbo.service.BarberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/barbers") // 👨‍💼 ADMIN ONLY
@RequiredArgsConstructor
public class AdminBarberController {

    private final BarberService barberService;

    // 👨‍💼 CREATE
    @PostMapping
    public Barber createBarber(@RequestBody Barber barber) {
        return barberService.createBarber(barber);
    }

    // 👨‍💼 UPDATE
    @PutMapping("/{id}")
    public Barber updateBarber(@PathVariable String id, @RequestBody Barber barber) {
        return barberService.updateBarber(id, barber);
    }

    // 👨‍💼 DELETE
    @DeleteMapping("/{id}")
    public String deleteBarber(@PathVariable String id) {
        barberService.deleteBarber(id);
        return "Barber deleted successfully";
    }
}