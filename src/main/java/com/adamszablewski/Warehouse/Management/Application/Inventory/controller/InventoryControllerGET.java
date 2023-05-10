package com.adamszablewski.Warehouse.Management.Application.Inventory.controller;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryServiceGET;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class InventoryControllerGET {

    InventoryServiceGET inventoryServiceGET;

    @GetMapping("/inventory")
    public List<Inventory> retrieveAllInventories(){
        return inventoryServiceGET.retrieveAllInventories();
    }

    @GetMapping("/inventory/id/{inventory_id}")
    public Optional<Inventory> retrieveInventoryById(@PathVariable int inventory_id){
        return inventoryServiceGET.retrieveInventoryById(inventory_id);
    }


    @GetMapping("/inventory/name/{inventory_name}")
    public Optional<Inventory> retrieveInventoryByName(@PathVariable String inventory_name){
        return inventoryServiceGET.retrieveInventoryByName(inventory_name);
    }

}
