package com.barbo.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentResponse {

    private String id;
    private String date;
    private String timeSlot;
    private String status;

    private String serviceName;
    private String barberShopName;
}