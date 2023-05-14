//package com.adamszablewski.Warehouse.Management.Application.Inventory.service;
//
//import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
//import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//@Service
//@AllArgsConstructor
//public class InventoryServiceGET {
//
//    InventoryRepository inventoryRepository;
//    public List<Inventory> retrieveAllInventories() {
//        return inventoryRepository.findAll();
//    }
//
//    public Optional<Inventory> retrieveInventoryById(int inventory_id) {
//        return inventoryRepository.findById(inventory_id);
//    }
//
//    public Optional<Inventory> retrieveInventoryByName(String inventory_name) {
//        return inventoryRepository.findByName(inventory_name);
//    }
//}
