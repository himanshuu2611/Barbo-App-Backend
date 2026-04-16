package com.barbo.controller;

import com.barbo.dto.AuthResponse;
import com.barbo.dto.LoginRequest;
import com.barbo.entity.User;
import com.barbo.service.UserService;
import com.barbo.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "https://barbo-app-frontend.vercel.app") // ✅ frontend access
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    // 🔐 LOGIN
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {

        User user = userService.login(request.getEmail(), request.getPassword());

        // ✅ Generate token with ROLE
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .role(user.getRole()) // 🔥 ROLE sent to frontend
                .userId(user.getId())
                .build();
    }

    // 👤 REGISTER USER
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // 📋 GET ALL USERS (ADMIN SHOULD USE THIS)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🔍 GET USER BY ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }
}
