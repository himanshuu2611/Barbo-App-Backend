package com.barbo.repository;

import com.barbo.entity.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    // 👤 USER BOOKINGS
    List<Appointment> findByUserId(String userId);

    // 💈 BARBER BOOKINGS
    List<Appointment> findByBarberId(String barberId);

    // 📅 BOOKINGS BY DATE (important)
    List<Appointment> findByBarberIdAndDate(String barberId, LocalDate date);

    // ⛔ CHECK SLOT AVAILABILITY (MOST IMPORTANT)
    boolean existsByBarberIdAndDateAndTimeSlot(
            String barberId,
            LocalDate date,
            String timeSlot
    );
}