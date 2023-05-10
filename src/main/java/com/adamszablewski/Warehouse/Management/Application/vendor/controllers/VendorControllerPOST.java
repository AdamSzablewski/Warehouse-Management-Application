package com.adamszablewski.Warehouse.Management.Application.vendor.controllers;

import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import com.adamszablewski.Warehouse.Management.Application.vendor.services.VendorServicePOST;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VendorControllerPOST {

    VendorServicePOST vendorServicePOST;

    @PostMapping("/vendors")
    public ResponseEntity<String> createVendor(@RequestBody Vendor vendor){
        return vendorServicePOST.createVendor(vendor);
    }
}
