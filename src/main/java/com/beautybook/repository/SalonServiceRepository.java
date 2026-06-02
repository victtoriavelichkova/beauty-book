package com.beautybook.repository;

import com.beautybook.model.entity.SalonService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SalonServiceRepository extends JpaRepository<SalonService, UUID> {
}