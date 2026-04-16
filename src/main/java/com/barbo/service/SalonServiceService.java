package com.barbo.service;

import com.barbo.entity.SalonService;
import com.barbo.repository.SalonServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceService {

    private final SalonServiceRepository repository;

    // 👨‍💼 ADMIN: CREATE
    public SalonService createService(SalonService service) {

        if (service.getName() == null || service.getBarberId() == null) {
            throw new RuntimeException("Service name and barberId are required");
        }

        return repository.save(service);
    }

    // 👨‍💼 ADMIN: UPDATE
    public SalonService updateService(String id, SalonService updatedService) {

        SalonService service = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        service.setName(updatedService.getName());
        service.setPrice(updatedService.getPrice());
        service.setDuration(updatedService.getDuration());
        service.setCategory(updatedService.getCategory());
        service.setDescription(updatedService.getDescription());

        return repository.save(service);
    }

    // 👤 USER: GET BY BARBER
    public List<SalonService> getServicesByBarber(String barberId) {
        return repository.findByBarberId(barberId);
    }

    // 👤 USER: FILTER BY CATEGORY
    public List<SalonService> getServicesByCategory(String barberId, String category) {
        return repository.findByBarberIdAndCategory(barberId, category);
    }

    // 👤 USER: GET ALL
    public List<SalonService> getAllServices() {
        return repository.findAll();
    }

    // 👨‍💼 ADMIN: DELETE
    public void deleteService(String id) {
        repository.deleteById(id);
    }
}