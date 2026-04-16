package com.barbo.service;

import com.barbo.entity.User;
import com.barbo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ CREATE USER (with ROLE + ENCRYPTION)
    public User createUser(User user) {

        // default role
        if (user.getRole() == null) {
            user.setRole("ROLE_USER");
        }

        // encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    // ✅ GET ALL
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ GET BY ID
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    // ✅ LOGIN (SECURE)
    public User login(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 🔐 secure password match
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}