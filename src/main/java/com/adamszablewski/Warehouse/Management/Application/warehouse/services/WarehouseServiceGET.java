package com.adamszablewski.Warehouse.Management.Application.warehouse.services;

import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WarehouseServiceGET {

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
}
