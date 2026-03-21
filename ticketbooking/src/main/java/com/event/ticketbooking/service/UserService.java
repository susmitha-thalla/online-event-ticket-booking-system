package com.event.ticketbooking.service;

import com.event.ticketbooking.dto.LoginRequest;
import com.event.ticketbooking.dto.RegisterRequest;
import com.event.ticketbooking.dto.LoginResponse;
import com.event.ticketbooking.dto.RegisterRequest;
import com.event.ticketbooking.model.User;
import com.event.ticketbooking.repository.UserRepository;
import com.event.ticketbooking.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import com.event.ticketbooking.security.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Autowired
    private JwtUtil jwtUtil;

    // REGISTER USER / ORGANIZER
    public String registerUser(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return "Email already exists!";
        }

        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(encoder.encode(request.getPassword())); // encrypted password
        user.setPhone(request.getPhone());

        // Allow only USER or ORGANIZER
        String role;
        if (request.getRole() == null || request.getRole().trim().isEmpty()) {
            role = "USER";
        } else if (request.getRole().equalsIgnoreCase("ORGANIZER")) {
            role = "ORGANIZER";
        } else {
            role = "USER";
        }

        user.setRole(role);
        user.setAccountStatus("ACTIVE");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        if (role.equals("ORGANIZER")) {
            return "Organizer Registered Successfully";
        } else {
            return "User Registered Successfully";
        }
    }

    // LOGIN USER / ORGANIZER
    public LoginResponse loginUser(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponse("Login Successful", token, user.getRole(), user.getEmail());
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}