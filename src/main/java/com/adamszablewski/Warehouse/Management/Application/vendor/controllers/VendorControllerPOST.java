package com.adamszablewski.Warehouse.Management.Application.vendor.controllers;

import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import com.adamszablewski.Warehouse.Management.Application.vendor.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VendorControllerPOST {

    VendorService vendorService;

    @PostMapping("/vendors")
    public ResponseEntity<String> createVendor(@RequestBody Vendor vendor){
        return vendorService.createVendor(vendor);
    }
}
