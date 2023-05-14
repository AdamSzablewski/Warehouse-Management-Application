package com.adamszablewski.Warehouse.Management.Application.Inventory.controller;

import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class InventoryControllerDELETE {

    InventoryService inventoryService;

    @DeleteMapping("/inventory/id/{inventory_id}")
    public ResponseEntity<String> deleteInventoryById(@PathVariable int inventory_id){
        return inventoryService.deleteInventoryById(inventory_id);
    }

    @DeleteMapping("/inventory/name/{name}")
    public ResponseEntity<String> deleteInventoryByName(@PathVariable String name){
        return inventoryService.deleteInventoryByName(name);
    }
}
