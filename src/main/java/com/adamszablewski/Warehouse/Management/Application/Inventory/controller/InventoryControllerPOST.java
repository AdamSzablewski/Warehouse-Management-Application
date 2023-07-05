package com.adamszablewski.Warehouse.Management.Application.Inventory.controller;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class InventoryControllerPOST {

    InventoryService inventoryService;

    @PostMapping("/inventories")
    public ResponseEntity<String> createInventory(@RequestBody Inventory inventory){
        return inventoryService.createInventory(inventory);
    }
}
