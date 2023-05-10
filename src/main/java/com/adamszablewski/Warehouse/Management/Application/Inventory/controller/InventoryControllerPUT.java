package com.adamszablewski.Warehouse.Management.Application.Inventory.controller;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryServicePUT;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class InventoryControllerPUT {

    InventoryServicePUT inventoryServicePUT;

    @PutMapping("/inventory/id/{inventory_id}")
    public ResponseEntity<String> updateInventory(@RequestBody Inventory inventory, @PathVariable int inventory_id){
        return inventoryServicePUT.updateInventory(inventory, inventory_id);
    }
}
