package com.adamszablewski.Warehouse.Management.Application.warehouse.services;

import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class WarehouseServicePOST {

    WarehouseRepository warehouseRepository;
    public ResponseEntity<String> createWarehouse(Warehouse warehouse) {
        warehouseRepository.save(warehouse);

        return ResponseEntity.ok("Warehouse saved");
    }
}
