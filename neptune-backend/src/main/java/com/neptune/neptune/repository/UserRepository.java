package com.neptune.neptune.repository;

import com.neptune.neptune.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// This interface handles database operations for the User entity
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}