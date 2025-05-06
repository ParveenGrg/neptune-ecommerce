package com.neptune.neptune.service;

import com.neptune.neptune.model.Admin;
import com.neptune.neptune.model.Vendor;
import com.neptune.neptune.repository.AdminRepository;
import com.neptune.neptune.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Autowired
    private VendorRepository vendorRepository;

    // Create a new vendor
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    // Get all vendors
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    // Find admin by username
    public Admin getAdminByUsername(String username) {
        return adminRepository.findByUsername(username).orElse(null);
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }
    
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin not found with ID: " + id));
    }
    
    public void deleteAdmin(Long id) {
        if (!adminRepository.existsById(id)) {
            throw new RuntimeException("Admin not found with ID: " + id);
        }
        adminRepository.deleteById(id);
    }
}