package com.neptune.neptune.controller;

import com.neptune.neptune.model.Product;
import com.neptune.neptune.model.Vendor;
import com.neptune.neptune.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    // Create a new vendor
    @PostMapping
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        Vendor createdVendor = vendorService.createVendor(vendor);
        return ResponseEntity.ok(createdVendor);
    }

    // Get all vendors
    @GetMapping
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorService.getAllVendors());
    }

    // Get a specific vendor by ID
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable Long id) {
        return ResponseEntity.ok(vendorService.getVendorById(id));
    }

    // Delete a vendor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }

    // Add a product for a specific vendor
    @PostMapping("/{vendorId}/products")
    public ResponseEntity<Product> addProductToVendor(@PathVariable Long vendorId, @RequestBody Product product) {
        Product createdProduct = vendorService.addProduct(product, vendorId);
        return ResponseEntity.ok(createdProduct);
    }

    // Get all products for a specific vendor
    @GetMapping("/{vendorId}/products")
    public ResponseEntity<List<Product>> getProductsByVendor(@PathVariable Long vendorId) {
        return ResponseEntity.ok(vendorService.getProductsByVendorId(vendorId));
    }

    // Update a product (by vendor)
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateVendorProduct(@PathVariable Long productId, @RequestBody Product product) {
        product.setId(productId); // Ensure product ID is set
        return ResponseEntity.ok(vendorService.updateProduct(product));
    }

    // Delete a product (by vendor)
    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteVendorProduct(@PathVariable Long productId) {
        vendorService.deleteProduct(productId);
        return ResponseEntity.noContent().build();
    }
}