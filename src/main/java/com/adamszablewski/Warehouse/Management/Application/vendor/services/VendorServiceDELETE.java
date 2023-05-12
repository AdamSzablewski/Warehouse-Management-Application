package com.adamszablewski.Warehouse.Management.Application.vendor.services;

import com.adamszablewski.Warehouse.Management.Application.vendor.ropository.VendorRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VendorServiceDELETE {

    VendorRepository vendorRepository;

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
