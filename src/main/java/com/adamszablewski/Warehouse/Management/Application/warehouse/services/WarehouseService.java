package com.adamszablewski.Warehouse.Management.Application.warehouse.services;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WarehouseService {

    WarehouseRepository warehouseRepository;
    public List<Warehouse> retrieveAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> retrieveWarehouseById(int warehouse_id) {
        return warehouseRepository.findById(warehouse_id);
    }

    public Optional<Warehouse> retrieveWarehouseByCity(String city) {
        return warehouseRepository.findByCity(city);
    }

    public Optional<Warehouse> retrieveWarehouseByName(String name) {
        return warehouseRepository.findByName(name);
    }

    public ResponseEntity<String> deleteWarehouseById(int warehouse_id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(warehouse_id);
        if (optionalWarehouse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such warehouse exist");
        }
        warehouseRepository.deleteById(warehouse_id);
        return ResponseEntity.ok("Warehouse deleted");
    }


    public ResponseEntity<String> deleteWarehouseByName(String name) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByName(name);
        if (optionalWarehouse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such warehouse exist");
        }
        warehouseRepository.deleteByName(name);
        return ResponseEntity.ok("Warehouse deleted");
    }

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

    public ResponseEntity<String> createWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);

        return ResponseEntity.ok("Warehouse saved");
    }

    public ResponseEntity<String> updateWarehouse(Warehouse warehouseInfo, String name) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByName(name);
        if (optionalWarehouse.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such warehouse exist");
        }

        Warehouse warehouse = optionalWarehouse.get();

        warehouse.setAddress(warehouseInfo.getAddress());
        warehouse.setCity(warehouseInfo.getCity());
        warehouse.setZipCode(warehouseInfo.getZipCode());
        warehouse.setProvince(warehouseInfo.getProvince());

        warehouseRepository.save(warehouse);

        return ResponseEntity.ok("Warehouse updated succesfully");
    }
}
