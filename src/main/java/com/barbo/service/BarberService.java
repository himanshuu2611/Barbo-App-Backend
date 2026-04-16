package com.barbo.service;

import com.barbo.entity.Barber;
import com.barbo.repository.BarberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarberService {

    private final BarberRepository barberRepository;

    // 👨‍💼 ADMIN: CREATE
    public Barber createBarber(Barber barber) {

        // basic validation
        if (barber.getName() == null || barber.getShopName() == null) {
            throw new RuntimeException("Name and Shop Name are required");
        }

        return barberRepository.save(barber);
    }

    // 👨‍💼 ADMIN: UPDATE
    public Barber updateBarber(String id, Barber updatedBarber) {

        Barber barber = barberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barber not found"));

        barber.setName(updatedBarber.getName());
        barber.setShopName(updatedBarber.getShopName());
        barber.setCity(updatedBarber.getCity());
        barber.setExperience(updatedBarber.getExperience());
        barber.setRating(updatedBarber.getRating());
        barber.setImage(updatedBarber.getImage());
        barber.setPhone(updatedBarber.getPhone());

        return barberRepository.save(barber);
    }

    // 👨‍💼 ADMIN: DELETE
    public void deleteBarber(String id) {
        barberRepository.deleteById(id);
    }

    // 👤 USER: GET ALL
    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    // 👤 USER: FILTER BY CITY
    public List<Barber> getBarbersByCity(String city) {
        return barberRepository.findByCity(city);
    }

    // 👤 USER: SEARCH
    public List<Barber> searchBarbers(String keyword) {
        return barberRepository.findByNameContainingIgnoreCase(keyword);
    }

    // 👤 USER: GET BY ID
    public Barber getBarberById(String id) {
        return barberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barber not found"));
    }
}