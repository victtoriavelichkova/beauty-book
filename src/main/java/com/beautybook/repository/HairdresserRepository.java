package com.beautybook.repository;

import com.beautybook.model.entity.Hairdresser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HairdresserRepository extends JpaRepository<Hairdresser, UUID> {
}