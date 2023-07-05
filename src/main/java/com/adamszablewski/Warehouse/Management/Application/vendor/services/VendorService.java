package com.adamszablewski.Warehouse.Management.Application.vendor.services;

import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import com.adamszablewski.Warehouse.Management.Application.vendor.ropository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VendorService {

    VendorRepository vendorRepository;

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }


    public Optional<Vendor> getVendorById(int vendor_id) {
        return vendorRepository.findById(vendor_id);
    }

    public Optional<Vendor> getVendorByName(String name) {
        return vendorRepository.findByName(name);
    }

    public ResponseEntity<String> createVendor(Vendor vendor) {
        vendorRepository.save(vendor);
        return ResponseEntity.ok("Vendor created successfully");
    }

    public ResponseEntity<String> updateVendorById(int vendor_id, Vendor vendorInfo) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(vendor_id);
        if (optionalVendor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Vendor exist");
        }

        Vendor vendor = optionalVendor.get();

        vendor.setAddress(vendorInfo.getAddress());
        vendor.setName(vendorInfo.getName());
        vendor.setPhone(vendorInfo.getPhone());
        vendor.setEmail(vendorInfo.getEmail());
        vendorRepository.save(vendor);

        return ResponseEntity.ok("Vendor updated successfully");

    }

    public ResponseEntity<String> updateVendorByName(String name, Vendor vendorInfo) {
        Optional<Vendor> optionalVendor = vendorRepository.findByName(name);
        if (optionalVendor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Vendor exist");
        }

        Vendor vendor = optionalVendor.get();

        vendor.setAddress(vendorInfo.getAddress());
        vendor.setName(vendorInfo.getName());
        vendor.setPhone(vendorInfo.getPhone());
        vendor.setEmail(vendorInfo.getEmail());
        vendorRepository.save(vendor);

        return ResponseEntity.ok("Vendor updated successfully");
    }

    public ResponseEntity<String> deleteVendorById(int vendor_id) {
        if (vendorRepository.findById(vendor_id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such vendor exist");
        }

        vendorRepository.deleteById(vendor_id);

        return ResponseEntity.ok("Vendor deleted successfully");
    }


    public ResponseEntity<String> deleteVendorByName(String name) {
        if (vendorRepository.findByName(name).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such vendor exist");
        }

        vendorRepository.deleteByName(name);

        return ResponseEntity.ok("Vendor deleted successfully");
    }
}
