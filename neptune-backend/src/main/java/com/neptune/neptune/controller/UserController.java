package com.neptune.neptune.controller;

import com.neptune.neptune.model.User;
import com.neptune.neptune.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles API endpoints for user-related operations
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Register new user and send verification code
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("Verification code sent to email.");
    }

    // Verify user's email with code
    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String email, @RequestParam String code) {
        boolean verified = userService.verifyUser(email, code);
        if (verified) {
            return ResponseEntity.ok("User verified successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid verification code.");
        }
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}