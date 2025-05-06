package com.neptune.neptune.repository;

import com.neptune.neptune.model.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// This interface allows you to perform CRUD operations on the Admin entity
public interface AdminRepository extends JpaRepository<Admin, Long> {
    // You can define custom queries here, like finding by username
    Optional<Admin> findByUsername(String username);
}