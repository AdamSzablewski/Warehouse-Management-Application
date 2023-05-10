package com.adamszablewski.Warehouse.Management.Application.warehouse.controllers;

import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.services.WarehouseServiceGET;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class WarehouseControllerGET {

    WarehouseServiceGET warehouseServiceGET;


    @GetMapping("/warehouses")
    public List<Warehouse> retrieveAllWarehouses(){
       return warehouseServiceGET.retrieveAllWarehouses();
    }

    @GetMapping("/warehouses/id/{warehouse_id}")
    public Optional<Warehouse> retrieveWarehouseById(@PathVariable int warehouse_id){
        return warehouseServiceGET.retrieveWarehouseById(warehouse_id);
    }

    @GetMapping("/warehouses/location/{location}")
    public Optional<Warehouse> retrieveWarehouseByCity(@PathVariable String city){
        return warehouseServiceGET.retrieveWarehouseByCity(city);
    }

    @GetMapping("/warehouses/name/{name}")
    public Optional<Warehouse> retrieveWarehouseByName(@PathVariable String name){
        return warehouseServiceGET.retrieveWarehouseByName(name);
    }
}
