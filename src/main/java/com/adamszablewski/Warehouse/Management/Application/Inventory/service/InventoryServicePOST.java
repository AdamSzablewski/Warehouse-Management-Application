package com.adamszablewski.Warehouse.Management.Application.Inventory.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryServicePOST {

    InventoryRepository inventoryRepository;
    public ResponseEntity<String> createInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        return ResponseEntity.ok("Inventory saved successfully");
    }
}
