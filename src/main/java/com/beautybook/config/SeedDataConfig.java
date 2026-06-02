package com.beautybook.config;

import com.beautybook.model.entity.Role;
import com.beautybook.model.entity.User;
import com.beautybook.model.enums.UserRole;
import com.beautybook.repository.RoleRepository;
import com.beautybook.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedDataConfig implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SeedDataConfig(RoleRepository roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setRole(UserRole.ADMIN);

            Role user = new Role();
            user.setRole(UserRole.USER);

            roleRepository.save(admin);
            roleRepository.save(user);
        }

        if (userRepository.count() == 0) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode("admin"));

            admin.setRole(roleRepository.findByRole(UserRole.ADMIN).get());

            userRepository.save(admin);
        }

        if (roleRepository.count() == 0) {
            Role admin = new Role();
            admin.setRole(UserRole.ADMIN);

            Role user = new Role();
            user.setRole(UserRole.USER);

            roleRepository.saveAll(List.of(admin, user));
        }

    }
}