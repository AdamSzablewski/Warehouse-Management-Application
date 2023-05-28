package com.adamszablewski.Warehouse.Management.Application.vendor.controllers;

import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import com.adamszablewski.Warehouse.Management.Application.vendor.services.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class VendorControllerPUT {

    VendorService vendorService;

    @PutMapping("/vendors/id/{vendor_id}")
    public ResponseEntity<String> updateVendorById(@PathVariable int vendor_id, @RequestBody Vendor vendor){
           return vendorService.updateVendorById(vendor_id, vendor);
        }

    @PutMapping("/vendors/name/{name}")
    public ResponseEntity<String> updateVendorByName(@PathVariable String name, @RequestBody Vendor vendor){
        return vendorService.updateVendorByName(name, vendor);
    }


}
