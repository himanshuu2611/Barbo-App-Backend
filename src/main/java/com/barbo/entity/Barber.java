package com.barbo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "barbers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Barber {

    @Id
    private String id;

    private String name;
    private String email;
    private String shopName;
    private String city;
    private String experience;

    private double rating;      // ⭐ show in UI
    private String image;       // 🖼 barber photo
    private String phone;       // 📞 contact
}