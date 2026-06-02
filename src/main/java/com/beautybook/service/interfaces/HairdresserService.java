package com.beautybook.service.interfaces;

import com.beautybook.model.entity.Hairdresser;

import java.util.List;
import java.util.UUID;

public interface HairdresserService {

    Hairdresser create(Hairdresser hairdresser);

    List<Hairdresser> getAll();

    Hairdresser getById(UUID id);

    Hairdresser update(UUID id, Hairdresser hairdresser);

    void delete(UUID id);
}