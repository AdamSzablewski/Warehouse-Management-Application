package com.adamszablewski.Warehouse.Management.Application.Inventory.repository;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Optional<Inventory> findByName(String inventoryName);

    void deleteByName(String name);


}


