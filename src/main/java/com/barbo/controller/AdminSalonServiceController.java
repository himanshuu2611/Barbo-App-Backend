package com.barbo.controller;

import com.barbo.entity.SalonService;
import com.barbo.service.SalonServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/services") // 👨‍💼 ADMIN ONLY
@RequiredArgsConstructor
public class AdminSalonServiceController {

    private final SalonServiceService service;

    // 👨‍💼 CREATE
    @PostMapping
    public SalonService create(@RequestBody SalonService salonService) {
        return service.createService(salonService);
    }

    // 👨‍💼 UPDATE
    @PutMapping("/{id}")
    public SalonService update(@PathVariable String id, @RequestBody SalonService salonService) {
        return service.updateService(id, salonService);
    }

    // 👨‍💼 DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        service.deleteService(id);
        return "Deleted successfully";
    }
}