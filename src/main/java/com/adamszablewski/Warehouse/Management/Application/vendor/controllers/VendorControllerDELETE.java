package com.adamszablewski.Warehouse.Management.Application.vendor.controllers;

import com.adamszablewski.Warehouse.Management.Application.vendor.services.VendorServiceDELETE;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class VendorControllerDELETE {

    VendorServiceDELETE vendorServiceDELETE;

    @DeleteMapping("/vendors/id/{vendor_id}")
    public ResponseEntity<String> deleteVendorById(@PathVariable int vendor_id){
       return vendorServiceDELETE.deleteVendorById(vendor_id);
    }

    @DeleteMapping("/vendors/name/{name}")
    public ResponseEntity<String> deleteVendorByName(@PathVariable String name){
        return vendorServiceDELETE.deleteVendorByName(name);
    }

}
