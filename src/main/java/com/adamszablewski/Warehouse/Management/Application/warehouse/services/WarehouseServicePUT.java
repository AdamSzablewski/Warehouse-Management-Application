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
public class WarehouseServicePUT {

    WarehouseRepository warehouseRepository;
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
