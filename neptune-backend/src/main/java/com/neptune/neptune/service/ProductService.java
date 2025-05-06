package com.neptune.neptune.service;

import com.neptune.neptune.model.Product;
import com.neptune.neptune.model.Vendor;
import com.neptune.neptune.repository.ProductRepository;
import com.neptune.neptune.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VendorRepository vendorRepository;

    // Create a product and assign it to a vendor
    public Product createProduct(Product product, Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        product.setVendor(vendor);
        return productRepository.save(product);
    }

    // Get all products (admin use)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get all products for a specific vendor
    public List<Product> getProductsByVendor(Long vendorId) {
        return productRepository.findByVendorId(vendorId);
    }

    // Update product
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existing = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        // Optional: handle images update separately if needed

        return productRepository.save(existing);
    }

    // Delete product
    public void deleteProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found");
        }
        productRepository.deleteById(productId);
    }

    // Optional: Get a single product by ID
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }
}