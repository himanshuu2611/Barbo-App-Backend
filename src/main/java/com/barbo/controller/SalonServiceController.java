package com.barbo.controller;

import com.barbo.entity.SalonService;
import com.barbo.service.SalonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services") // 👤 USER ACCESS
@RequiredArgsConstructor
public class SalonServiceController {

    private final SalonServiceService service;

    // 👤 GET ALL
    @GetMapping
    public List<SalonService> getAll() {
        return service.getAllServices();
    }

    // 👤 GET BY BARBER
    @GetMapping("/barber/{barberId}")
    public List<SalonService> getByBarber(@PathVariable String barberId) {
        return service.getServicesByBarber(barberId);
    }

    // 👤 FILTER BY CATEGORY (optional)
    @GetMapping("/barber/{barberId}/category/{category}")
    public List<SalonService> getByCategory(
            @PathVariable String barberId,
            @PathVariable String category) {
        return service.getServicesByCategory(barberId, category);
    }
}