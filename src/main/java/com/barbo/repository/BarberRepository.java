package com.barbo.repository;

import com.barbo.entity.Barber;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BarberRepository extends MongoRepository<Barber, String> {

    // 🔍 Filter by city (user feature)
    List<Barber> findByCity(String city);

    // 🔍 Search by shop name
    List<Barber> findByShopNameContainingIgnoreCase(String shopName);

    // 🔍 Search by barber name
    List<Barber> findByNameContainingIgnoreCase(String name);
}