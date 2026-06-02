package com.beautybook.service.impl;

import com.beautybook.model.entity.Hairdresser;
import com.beautybook.repository.HairdresserRepository;
import com.beautybook.service.interfaces.HairdresserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HairdresserServiceImpl implements HairdresserService {

    private final HairdresserRepository hairdresserRepository;

    public HairdresserServiceImpl(HairdresserRepository hairdresserRepository) {
        this.hairdresserRepository = hairdresserRepository;
    }

    @Override
    public Hairdresser create(Hairdresser hairdresser) {
        return hairdresserRepository.save(hairdresser);
    }

    @Override
    public List<Hairdresser> getAll() {
        return hairdresserRepository.findAll();
    }

    @Override
    public Hairdresser getById(UUID id) {
        return hairdresserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Hairdresser not found"));
    }

    @Override
    public Hairdresser update(UUID id, Hairdresser hairdresser) {
        Hairdresser existing = getById(id);

        existing.setFirstName(hairdresser.getFirstName());
        existing.setLastName(hairdresser.getLastName());
        existing.setSpecialization(hairdresser.getSpecialization());
        existing.setActive(hairdresser.isActive());

        return hairdresserRepository.save(existing);
    }

    @Override
    public void delete(UUID id) {
        hairdresserRepository.deleteById(id);
    }
}