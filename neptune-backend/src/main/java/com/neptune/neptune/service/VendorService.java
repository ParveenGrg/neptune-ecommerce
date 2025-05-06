package com.neptune.neptune.service;

import com.neptune.neptune.model.Product;
import com.neptune.neptune.model.Vendor;
import com.neptune.neptune.repository.ProductRepository;
import com.neptune.neptune.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ProductRepository productRepository;

    // Create a new vendor
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    // Get all vendors
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    // Get vendor by ID
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id).orElseThrow(() -> new RuntimeException("Vendor not found"));
    }

    // Delete vendor by ID
    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }

    // Get all products for a vendor
    public List<Product> getProductsByVendorId(Long vendorId) {
        return productRepository.findByVendorId(vendorId);
    }

    // Add a product to a vendor
    public Product addProduct(Product product, Long vendorId) {
        Vendor vendor = vendorRepository.findById(vendorId)
            .orElseThrow(() -> new RuntimeException("Vendor not found"));
        product.setVendor(vendor);
        return productRepository.save(product);
    }

    // Update a product
    public Product updateProduct(Product updatedProduct) {
        Product existingProduct = productRepository.findById(updatedProduct.getId())
            .orElseThrow(() -> new RuntimeException("Product not found"));
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        return productRepository.save(existingProduct);
    }

    // Delete a product
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}