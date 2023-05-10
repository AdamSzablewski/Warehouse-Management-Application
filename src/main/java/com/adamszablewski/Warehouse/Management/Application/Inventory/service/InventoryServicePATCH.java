package com.adamszablewski.Warehouse.Management.Application.Inventory.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@AllArgsConstructor
public class InventoryServicePATCH {

    InventoryRepository inventoryRepository;
    InventoryHelper inventoryHelper;
    public ResponseEntity<String> addItemsToInventoryById(int inventory_id, int amount) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventory_id);
        if (optionalInventory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Inventory exist");
        }

        Inventory inventory = optionalInventory.get();

        inventory.setQuantity(inventory.getQuantity()+ amount);

        inventoryRepository.save(inventory);

        return ResponseEntity.ok("Items added succesfully");
    }


    public ResponseEntity<String> addItemsToInventoryByName(String name, int amount) {
        Optional<Inventory> optionalInventory = inventoryRepository.findByName(name);
        if (optionalInventory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Inventory exist");
        }

        Inventory inventory = optionalInventory.get();

        inventoryHelper.addToInventory(inventory, amount);

        inventoryRepository.save(inventory);

        return ResponseEntity.ok("Items added successfully");
    }

    public ResponseEntity<String> removeItemsToInventoryByName(String name, int amount) {
        Optional<Inventory> optionalInventory = inventoryRepository.findByName(name);
        if (optionalInventory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Inventory exist");
        }

        Inventory inventory = optionalInventory.get();

        boolean removed = inventoryHelper.removeFromInventory(inventory, amount);
        if (!removed){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("Amount to be removed exceeds the amount of stored units");
        }
        inventoryRepository.save(inventory);

        return ResponseEntity.ok("Items added successfully");
    }

    public ResponseEntity<String> removeItemsToInventoryById(int inventory_id, int amount) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventory_id);
        if (optionalInventory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Inventory exist");
        }

        Inventory inventory = optionalInventory.get();

        boolean removed = inventoryHelper.removeFromInventory(inventory, amount);
        if (!removed){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("Amount to be removed exceeds the amount of stored units");
        }
        inventoryRepository.save(inventory);

        return ResponseEntity.ok("Items added successfully");
    }

}
