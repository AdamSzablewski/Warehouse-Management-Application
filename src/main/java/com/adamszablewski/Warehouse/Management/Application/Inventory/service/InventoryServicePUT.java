package com.adamszablewski.Warehouse.Management.Application.Inventory.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class InventoryServicePUT {

    InventoryRepository inventoryRepository;
    public ResponseEntity<String> updateInventory(Inventory inventoryInfo, int id) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such inventory exist");
        }

        Inventory inventory = optionalInventory.get();
        inventory.setName(inventoryInfo.getName());
        inventory.setDateAdded(inventoryInfo.getDateAdded());
        inventory.setQuantity(inventory.getQuantity());
        inventory.setMaximumOrderQuantity(inventory.getMaximumOrderQuantity());
        inventory.setMaximumStockLevel(inventory.getMinimumStockLevel());

        inventoryRepository.save(inventory);
        return ResponseEntity.ok("Inventory updated successfully");
    }
}
