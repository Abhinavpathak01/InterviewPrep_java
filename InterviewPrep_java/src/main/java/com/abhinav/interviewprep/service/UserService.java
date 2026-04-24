package com.abhinav.interviewprep.service;

import com.abhinav.interviewprep.dto.LoginResponse;
import com.abhinav.interviewprep.entity.User;
import com.abhinav.interviewprep.repository.UserRepository;
import com.abhinav.interviewprep.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    // ✅ Signup (with duplicate check + hashing)
    public User registerUser(User user) {

        // 🔥 Prevent duplicate users
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        // 🔐 Hash password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 👤 Default role
        user.setRole("USER");

        return userRepository.save(user);
    }

    // ✅ Login (secure authentication)
    public LoginResponse loginUser(String email, String password) {

        User user = userRepository.findByEmail(email);

        if (user == null) {
            return new LoginResponse("User not found", false, null);
        }

        // 🔐 Compare encrypted password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return new LoginResponse("Invalid password", false, null);
        }

        // 🎟️ Generate JWT token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole());

        return new LoginResponse("Login successful", true, token);
    }
}