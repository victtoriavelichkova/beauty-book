package com.beautybook.service.impl;

import com.beautybook.model.entity.Role;
import com.beautybook.model.entity.User;
import com.beautybook.model.enums.UserRole;
import com.beautybook.repository.RoleRepository;
import com.beautybook.repository.UserRepository;
import com.beautybook.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(String username, String email, String password) {

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        Role role = roleRepository.findByRole(UserRole.USER)
                .orElseThrow();

        user.setRole(role);

        userRepository.save(user);
    }
}
