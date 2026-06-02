package com.beautybook.repository;

import com.beautybook.model.entity.Role;
import com.beautybook.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    Optional<Role> findByRole(UserRole role);
}