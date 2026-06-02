package com.beautybook.service.interfaces;

import com.beautybook.model.entity.SalonService;

import java.util.List;
import java.util.UUID;

public interface SalonServiceService {

    SalonService create(SalonService service);

    List<SalonService> getAll();

    SalonService getById(UUID id);

    SalonService update(UUID id, SalonService service);

    void delete(UUID id);
}