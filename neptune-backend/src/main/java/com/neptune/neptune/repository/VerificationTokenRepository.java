package com.neptune.neptune.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neptune.neptune.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
