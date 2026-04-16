package com.barbo.controller;

import com.barbo.entity.Appointment;
import com.barbo.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/appointments") // 👤 USER ONLY
@RequiredArgsConstructor
public class UserAppointmentController {

    private final AppointmentService service;


    // 👤 BOOK
    @PostMapping
    public Appointment book(@RequestBody Appointment appointment) {
        return service.book(appointment);
    }

    // 👤 VIEW OWN BOOKINGS
    @GetMapping("/{userId}")
    public List<Appointment> getByUser(@PathVariable String userId) {
        return service.getByUser(userId);
    }
    @GetMapping("/my/{userId}")
    public ResponseEntity<?> getUserAppointments(@PathVariable String userId) {
        return ResponseEntity.ok(service.getUserAppointments(userId));
    }
    // 👤 BARBER BOOKINGS (🔥 NEW)
    @GetMapping("/barber/{barberId}")
    public List<Appointment> getByBarber(@PathVariable String barberId) {
        return service.getByBarber(barberId);
    }
    // 👤 CANCEL
    @PatchMapping("/{id}/cancel")
    public Appointment cancel(@PathVariable String id) {
        return service.cancel(id);
    }
}