package com.adamszablewski.Warehouse.Management.Application.warehouse.controllers;

import com.adamszablewski.Warehouse.Management.Application.warehouse.services.WarehouseService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class WarehouseControllerDELETE {

    WarehouseService warehouseService;

    @DeleteMapping("/warehouses/id/{warehouse_id}")
    public ResponseEntity<String> deleteWarehouseById(@PathVariable int warehouse_id){
       return warehouseService.deleteWarehouseById(warehouse_id);
    }
    @DeleteMapping("/warehouses/name/{name}")
    public ResponseEntity<String> deleteWarehouseByName(@PathVariable String name){
        return warehouseService.deleteWarehouseByName(name);
    }
}
