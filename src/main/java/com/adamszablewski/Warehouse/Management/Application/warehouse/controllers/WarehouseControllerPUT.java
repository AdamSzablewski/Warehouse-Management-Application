package com.adamszablewski.Warehouse.Management.Application.warehouse.controllers;

import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.services.WarehouseServicePUT;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WarehouseControllerPUT {

    WarehouseServicePUT warehouseServicePUT;

    @PutMapping("/warehouses/name/{name}")
    public ResponseEntity<String> updateWarehouse(@RequestBody Warehouse warehouse, @PathVariable String name){
        return warehouseServicePUT.updateWarehouse(warehouse, name);
    }
}
