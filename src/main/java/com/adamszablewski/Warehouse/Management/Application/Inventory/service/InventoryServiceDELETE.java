package com.adamszablewski.Warehouse.Management.Application.Inventory.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InventoryServiceDELETE {

    InventoryRepository inventoryRepository;
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
