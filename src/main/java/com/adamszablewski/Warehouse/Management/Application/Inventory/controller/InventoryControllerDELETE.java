package com.adamszablewski.Warehouse.Management.Application.Inventory.controller;

import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/inventories")
public class InventoryControllerDELETE {

    InventoryService inventoryService;

    @DeleteMapping("/id/{inventory_id}")
    public ResponseEntity<String> deleteInventoryById(@PathVariable int inventory_id){
        return inventoryService.deleteInventoryById(inventory_id);
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity<String> deleteInventoryByName(@PathVariable String name){
        return inventoryService.deleteInventoryByName(name);
    }
}
