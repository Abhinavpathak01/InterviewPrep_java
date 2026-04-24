package com.abhinav.interviewprep.controller;

import java.util.Map;
import com.abhinav.interviewprep.dto.LoginResponse;
import com.abhinav.interviewprep.entity.User;
import com.abhinav.interviewprep.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @GetMapping("/admin/test")
    public String adminTest() {
        return "Admin API working";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody User user) {
        return userService.loginUser(user.getEmail(), user.getPassword());
    }
    @GetMapping("/test")
    public Map<String, String> test() {

        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return Map.of(
                "message", "Access granted",
                "user", email
        );
    }
}