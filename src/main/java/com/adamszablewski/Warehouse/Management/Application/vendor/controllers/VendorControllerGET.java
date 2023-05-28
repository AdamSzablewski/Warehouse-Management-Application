package com.adamszablewski.Warehouse.Management.Application.vendor.controllers;

import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import com.adamszablewski.Warehouse.Management.Application.vendor.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class VendorControllerGET {

    VendorService vendorService;

    @GetMapping("/vendors")
    public List<Vendor> getAllVendors(){
        return vendorService.getAllVendors();
    }

    @GetMapping("/vendors/id/{vendor_id}")
    public Optional<Vendor> getVendorById(@PathVariable int vendor_id){
        return vendorService.getVendorById(vendor_id);
    }

    @GetMapping("/vendors/name/{name}")
    public Optional<Vendor> getVendorById(@PathVariable String name){
        return vendorService.getVendorByName(name);
    }
}
