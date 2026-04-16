package com.barbo.repository;

import com.barbo.entity.SalonService;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SalonServiceRepository extends MongoRepository<SalonService, String> {

    // 👤 USER: get services of a barber
    List<SalonService> findByBarberId(String barberId);

    // 🔍 filter by category (Hair, Beard, Facial)
    List<SalonService> findByBarberIdAndCategory(String barberId, String category);

    // 🔍 search by name
    List<SalonService> findByNameContainingIgnoreCase(String name);
}