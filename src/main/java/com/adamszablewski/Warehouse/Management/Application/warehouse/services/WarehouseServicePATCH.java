package com.adamszablewski.Warehouse.Management.Application.warehouse.services;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.repository.WarehouseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServicePATCH {

    WarehouseRepository warehouseRepository;


    public ResponseEntity<String> addInventoryCategoryToWarehouseById(Inventory inventory, int warehouseId) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouseId);
        if (optionalWarehouse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such warehouse exist");
        }

        Warehouse warehouse = optionalWarehouse.get();
        List<Inventory> inventories = warehouse.getInventory();
        inventories.add(inventory);


        warehouseRepository.save(warehouse);

        return ResponseEntity.ok("Warehouse updated succesfully");
    }


    public ResponseEntity<String> addInventoryCategoryToWarehouseByName(Inventory inventory, String name) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByName(name);
        if (optionalWarehouse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such warehouse exist");
        }

        Warehouse warehouse = optionalWarehouse.get();
        List<Inventory> inventories = warehouse.getInventory();
        inventories.add(inventory);


        warehouseRepository.save(warehouse);

        return ResponseEntity.ok("Warehouse updated succesfully");
    }
}
