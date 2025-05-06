package com.neptune.neptune.repository;

import com.neptune.neptune.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for handling product image data
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    // You can use this to find all images for a given product
}