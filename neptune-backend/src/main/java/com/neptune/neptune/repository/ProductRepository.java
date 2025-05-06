package com.neptune.neptune.repository;

import com.neptune.neptune.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository interface for managing products in the database
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Custom method to find all products created by a specific vendor
    List<Product> findByVendorId(Long vendorId);
}