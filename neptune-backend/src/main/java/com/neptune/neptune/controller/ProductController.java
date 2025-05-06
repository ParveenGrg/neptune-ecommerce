package com.neptune.neptune.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import com.neptune.neptune.model.Product;
import com.neptune.neptune.repository.ProductRepository;

import jakarta.validation.Valid;

//This class handles API requests related to Products
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    
    //GET API to list all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    //POST API to add a new product
    @PostMapping
    public Product addproduct(@RequestBody @Valid Product newProduct){
        return productRepository.save(newProduct);
    }
// PUT /products/{id} - Update a product
@PutMapping("/{id}")
public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
    Product existingProduct = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    existingProduct.setName(product.getName());
    existingProduct.setPrice(product.getPrice());
    existingProduct.setDescription(product.getDescription());

    return productRepository.save(existingProduct);
}

// DELETE /products/{id} - Delete a product
@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    if (productRepository.existsById(id)) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    } else {
        return ResponseEntity.notFound().build();
    }
}
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
}
