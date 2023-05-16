package com.adamszablewski.Warehouse.Management.Application.Inventory.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class InventoryService {

    InventoryRepository inventoryRepository;

    InventoryHelper inventoryHelper;

    public List<Inventory> retrieveAllInventories() {
        return inventoryRepository.findAll();
    }

    public Optional<Inventory> retrieveInventoryById(int inventory_id) {
        return inventoryRepository.findById(inventory_id);
    }

    public Optional<Inventory> retrieveInventoryByName(String inventory_name) {
        return inventoryRepository.findByName(inventory_name);
    }



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

    public ResponseEntity<String> updateInventory(Inventory inventoryInfo, int id) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(id);
        if (optionalInventory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such inventory exist");
        }

        Inventory inventory = optionalInventory.get();
        inventory.setName(inventoryInfo.getName());
        inventory.setQuantity(inventoryInfo.getQuantity());
        inventory.setStorageLocation(inventoryInfo.getStorageLocation());
        inventory.setMaximumOrderQuantity(inventoryInfo.getMaximumOrderQuantity());
        inventory.setMaximumStockLevel(inventoryInfo.getMinimumStockLevel());

        inventoryRepository.save(inventory);
        return ResponseEntity.ok("Inventory updated successfully");
    }

    public ResponseEntity<String> removeItemsFromInventoryByName(String name, int amount) {
        Optional<Inventory> optionalInventory = inventoryRepository.findByName(name);
        if (optionalInventory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Inventory exist");
        }

        Inventory inventory = optionalInventory.get();

        Inventory updatedInventory = inventoryHelper.removeFromInventory(inventory, amount);
        if (updatedInventory == null){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("Amount to be removed exceeds the amount of stored units");
        }
        inventory = updatedInventory;
        inventoryRepository.save(inventory);

        return ResponseEntity.ok("Items added successfully");
    }

    public ResponseEntity<String> removeItemsToInventoryById(int inventory_id, int amount) {
        Optional<Inventory> optionalInventory = inventoryRepository.findById(inventory_id);
        if (optionalInventory.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such Inventory exist");
        }

        Inventory inventory = optionalInventory.get();

        Inventory updatedInventory = inventoryHelper.removeFromInventory(inventory, amount);
        if (updatedInventory == null){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("Amount to be removed exceeds the amount of stored units");
        }
        inventory = updatedInventory;
        inventoryRepository.save(inventory);

        return ResponseEntity.ok("Items removed successfully");
    }

    public ResponseEntity<String> createInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        return ResponseEntity.ok("Inventory saved successfully");
    }

    public ResponseEntity<String> deleteInventoryById(int inventory_id) {
        if (inventoryRepository.findById(inventory_id).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such inventory exist");
        }
        inventoryRepository.deleteById(inventory_id);
        return ResponseEntity.ok("Inventory deleted");
    }


    public ResponseEntity<String> deleteInventoryByName(String name) {
        if (inventoryRepository.findByName(name).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such inventory exist");
        }
        inventoryRepository.deleteByName(name);
        return ResponseEntity.ok("Inventory deleted");
    }
}
