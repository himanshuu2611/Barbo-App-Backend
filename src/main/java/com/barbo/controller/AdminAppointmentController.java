package com.barbo.controller;

import com.barbo.dto.AppointmentResponse;
import com.barbo.entity.Appointment;
import com.barbo.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/appointments") // 👨‍💼 ADMIN ONLY
@RequiredArgsConstructor
public class AdminAppointmentController {

    private final AppointmentService service;

    // 👨‍💼 VIEW ALL BOOKINGS
    @GetMapping
    public List<AppointmentResponse> getAll() {
        return service.getAllAppointments(); // 🔥 add this method
    }

    // 👨‍💼 VIEW BY BARBER
    @GetMapping("/barber/{barberId}")
    public List<Appointment> getByBarber(@PathVariable String barberId) {
        return service.getByBarber(barberId);
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable String id,
            @RequestParam String status) {

        return ResponseEntity.ok(service.updateStatus(id, status));
    }
}