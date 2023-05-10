package com.adamszablewski.Warehouse.Management.Application.warehouse.repository;

import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {

    Optional<Warehouse> findByCity(String city);
    Optional<Warehouse> findByName(String city);

    void deleteByName(String city);
}
