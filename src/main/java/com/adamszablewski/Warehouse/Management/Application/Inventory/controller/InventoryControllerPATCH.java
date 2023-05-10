package com.adamszablewski.Warehouse.Management.Application.Inventory.controller;

import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryServicePATCH;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InventoryControllerPATCH {

    InventoryServicePATCH inventoryServicePATCH;

    @PatchMapping("/inventories/id/{inventory_id}/add/quantity/{amount}")
    public ResponseEntity<String> addItemsToInventoryById(@PathVariable int inventory_id, @PathVariable int amount){
        return inventoryServicePATCH.addItemsToInventoryById(inventory_id, amount);
    }

    @PatchMapping("/inventories/name/{name}/add/quantity/{amount}")
    public ResponseEntity<String> addItemsToInventoryByName(@PathVariable String name, @PathVariable int amount){
        return inventoryServicePATCH.addItemsToInventoryByName(name, amount);
    }
    @PatchMapping("/inventories/id/{inventory_id}/remove/quantity/{amount}")
    public ResponseEntity<String> removeItemsToInventoryById(@PathVariable int inventory_id, @PathVariable int amount){
        return inventoryServicePATCH.removeItemsToInventoryById(inventory_id, amount);
    }

    @PatchMapping("/inventories/name/{name}/remove/quantity/{amount}")
    public ResponseEntity<String> removeItemsToInventoryByName(@PathVariable String name, @PathVariable int amount){
        return inventoryServicePATCH.removeItemsToInventoryByName(name, amount);
    }
}
