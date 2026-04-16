package com.barbo.service;

import com.barbo.dto.AppointmentResponse;
import com.barbo.entity.Appointment;
import com.barbo.entity.Barber;
import com.barbo.entity.SalonService;
import com.barbo.entity.Status;
import com.barbo.repository.AppointmentRepository;
import com.barbo.repository.BarberRepository;
import com.barbo.repository.SalonServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository repository;
    private final SalonServiceRepository salonServiceRepository;
    private final BarberRepository barberRepository;


    // 👤 BOOK (WITH SLOT CHECK 🔥)
    public Appointment book(Appointment appointment) {

        // 🚨 prevent double booking
        boolean exists = repository.existsByBarberIdAndDateAndTimeSlot(
                appointment.getBarberId(),
                appointment.getDate(),
                appointment.getTimeSlot()
        );

        if (exists) {
            throw new RuntimeException("Slot already booked ❌");
        }

        appointment.setStatus(Status.BOOKED);
        appointment.setCreatedAt(LocalDateTime.now());


        return repository.save(appointment);
    }
    public List<AppointmentResponse> getAllAppointments() {

        List<Appointment> appointments = repository.findAll();

        return appointments.stream().map(a -> {

            SalonService service = salonServiceRepository.findById(a.getServiceId()).orElse(null);
            Barber barber = barberRepository.findById(a.getBarberId()).orElse(null);

            return AppointmentResponse.builder()
                    .id(a.getId())
                    .date(String.valueOf(a.getDate()))
                    .timeSlot(a.getTimeSlot())
                    .status(String.valueOf(a.getStatus()))
                    .serviceName(service != null ? service.getName() : "N/A")
                    .barberShopName(barber != null ? barber.getShopName() : "N/A")
                    .build();

        }).toList();
    }

    public Appointment updateStatus(String id, String status) {

        Appointment appointment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(Status.valueOf(status.toUpperCase()));

        return repository.save(appointment);
    }

    // 👤 USER APPOINTMENTS
    public List<Appointment> getByUser(String userId) {
        return repository.findByUserId(userId);
    }

    // 👨‍💼 BARBER APPOINTMENTS
    public List<Appointment> getByBarber(String barberId) {
        return repository.findByBarberId(barberId);
    }

    // 👤 CANCEL
    public Appointment cancel(String id) {

        Appointment appt = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (appt.getStatus() == Status.COMPLETED) {
            throw new RuntimeException("Cannot cancel completed appointment ❌");
        }

        appt.setStatus(Status.CANCELLED);

        return repository.save(appt);
    }
    public List<AppointmentResponse> getUserAppointments(String userId) {

        List<Appointment> appointments = repository.findByUserId(userId);

        return appointments.stream().map(a -> {

            SalonService service = salonServiceRepository.findById(a.getServiceId()).orElse(null);
            Barber barber = barberRepository.findById(a.getBarberId()).orElse(null);

            return AppointmentResponse.builder()
                    .id(a.getId())
                    .date(String.valueOf(a.getDate()))
                    .timeSlot(a.getTimeSlot())
                    .status(String.valueOf(a.getStatus()))
                    .serviceName(service != null ? service.getName() : "N/A")
                    .barberShopName(barber != null ? barber.getShopName() : "N/A")
                    .build();

        }).toList();
    }
}