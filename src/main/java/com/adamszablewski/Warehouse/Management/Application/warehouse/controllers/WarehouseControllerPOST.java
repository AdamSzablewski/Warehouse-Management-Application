package com.adamszablewski.Warehouse.Management.Application.warehouse.controllers;

import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.services.WarehouseServicePOST;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WarehouseControllerPOST {

    WarehouseServicePOST warehouseServicePOST;


    @PostMapping("/warehouses")
    public ResponseEntity<String> createWarehouse(@RequestBody Warehouse warehouse){
        return warehouseServicePOST.createWarehouse(warehouse);
    }
}
