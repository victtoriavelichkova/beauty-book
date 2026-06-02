package com.beautybook.service.impl;

import com.beautybook.model.entity.SalonService;
import com.beautybook.repository.SalonServiceRepository;
import com.beautybook.service.interfaces.SalonServiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SalonServiceServiceImpl implements SalonServiceService {

    private final SalonServiceRepository salonServiceRepository;

    public SalonServiceServiceImpl(SalonServiceRepository salonServiceRepository) {
        this.salonServiceRepository = salonServiceRepository;
    }

    @Override
    public SalonService create(SalonService service) {
        return salonServiceRepository.save(service);
    }

    @Override
    public List<SalonService> getAll() {
        return salonServiceRepository.findAll();
    }

    @Override
    public SalonService getById(UUID id) {
        return salonServiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Service not found"));
    }

    @Override
    public SalonService update(UUID id, SalonService service) {
        SalonService existing = getById(id);

        existing.setName(service.getName());
        existing.setDurationMinutes(service.getDurationMinutes());
        existing.setPrice(service.getPrice());

        return salonServiceRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        salonServiceRepository.deleteById(id);
    }
}