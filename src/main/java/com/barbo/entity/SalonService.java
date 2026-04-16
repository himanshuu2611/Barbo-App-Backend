package com.barbo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalonService {

    @Id
    private String id;

    private String name;
    private double price;
    private int duration; // in minutes

    private String barberId; // 🔥 link to barber

    private String category;   // Hair, Beard, Facial etc.
    private String description; // short details for UI
}