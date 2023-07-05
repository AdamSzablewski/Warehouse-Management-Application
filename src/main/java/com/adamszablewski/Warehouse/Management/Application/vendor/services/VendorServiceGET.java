//package com.adamszablewski.Warehouse.Management.Application.vendor.services;
//
//import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
//import com.adamszablewski.Warehouse.Management.Application.vendor.ropository.VendorRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@AllArgsConstructor
//public class VendorServiceGET {
//
//    VendorRepository vendorRepository;
//
//    public List<Vendor> getAllVendors() {
//        return vendorRepository.findAll();
//    }
//
//
//    public Optional<Vendor> getVendorById(int vendor_id) {
//        return vendorRepository.findById(vendor_id);
//    }
//
//    public Optional<Vendor> getVendorByName(String name) {
//        return vendorRepository.findByName(name);
//    }
//}
