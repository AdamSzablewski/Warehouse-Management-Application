package com.adamszablewski.Warehouse.Management.Application.vendor.services;

import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import com.adamszablewski.Warehouse.Management.Application.vendor.ropository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VendorServicePUT {

    VendorRepository vendorRepository;

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
}
