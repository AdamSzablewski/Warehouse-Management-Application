package com.adamszablewski.Warehouse.Management.Application.vendor.controllers;

import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import com.adamszablewski.Warehouse.Management.Application.vendor.services.VendorServiceGET;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class VendorControllerGET {

    VendorServiceGET vendorServiceGET;

    @GetMapping("/vendors")
    public List<Vendor> getAllVendors(){
        return vendorServiceGET.getAllVendors();
    }

    @GetMapping("/vendors/id/{vendor_id}")
    public Optional<Vendor> getVendorById(@PathVariable int vendor_id){
        return vendorServiceGET.getVendorById(vendor_id);
    }

    @GetMapping("/vendors/name/{name}")
    public Optional<Vendor> getVendorById(@PathVariable String name){
        return vendorServiceGET.getVendorByName(name);
    }
}
