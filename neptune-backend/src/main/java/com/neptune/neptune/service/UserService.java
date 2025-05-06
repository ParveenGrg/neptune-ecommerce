package com.neptune.neptune.service;

import com.neptune.neptune.model.User;
import com.neptune.neptune.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    // Register new user
    public User registerUser(User user) {
        user.setVerified(false);
        user.setVerificationCode(generateVerificationCode());  // You define this method
    userRepository.save(user);
    emailService.sendVerificationEmail(user.getEmail(), user.getVerificationCode());
    return user;
    }

    // Verify user by email and code
    public boolean verifyUser(String email, String code) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getVerificationCode().equals(code)) {
                user.setVerified(true);
                user.setVerificationCode(null);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    // Find user by email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
    }
    private String generateVerificationCode() {
        int code = (int) (Math.random() * 900000) + 100000;
        return String.valueOf(code);
    }
}