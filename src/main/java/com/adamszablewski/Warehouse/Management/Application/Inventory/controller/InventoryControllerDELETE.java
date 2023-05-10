package com.adamszablewski.Warehouse.Management.Application.Inventory.controller;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryServiceDELETE;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryServicePOST;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class InventoryControllerDELETE {

    InventoryServiceDELETE inventoryServiceDELETE;

    @DeleteMapping("/inventory/id/{inventory_id}")
    public ResponseEntity<String> deleteInventoryById(@PathVariable int inventory_id){
        return inventoryServiceDELETE.deleteInventoryById(inventory_id);
    }

    @DeleteMapping("/inventory/name/{name}")
    public ResponseEntity<String> deleteInventoryByName(@PathVariable String name){
        return inventoryServiceDELETE.deleteInventoryByName(name);
    }
}
