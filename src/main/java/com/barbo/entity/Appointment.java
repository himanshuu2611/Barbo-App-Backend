package com.barbo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "appointments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    private String id;

    private String userId;       // 👤 who booked
    private String barberId;     // 💈 barber
    private String serviceId;    // ✂️ service

    private String serviceName;  // 🔥 store name (UI use)
    private double price;        // 💰 store price (history safe)

    private LocalDate date;      // ✅ better than String
    private String timeSlot;     // "10:00 AM"

    private Status status;       // BOOKED / CANCELLED

    private LocalDateTime createdAt; // 📅 booking time
}