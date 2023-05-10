package com.adamszablewski.Warehouse.Management.Application.warehouse.services;

import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class WarehouseServiceDELETE {

    WarehouseRepository warehouseRepository;
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
}
