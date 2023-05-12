package com.adamszablewski.Warehouse.Management.Application.warehouse.repository;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class WarehouseRepositoryTest {

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @BeforeEach
    void setUp(){
        inventoryRepository.deleteAll();
        warehouseRepository.deleteAll();

    }

    @Test
    void shouldReturnWarehouseByCity(){
        Warehouse warehouse = Warehouse.builder()
                .city("Gdansk")
                .name("W1")
                .build();

        warehouseRepository.save(warehouse);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByCity(warehouse.getCity());

        Warehouse warehousefromDB = optionalWarehouse.get();
        assertThat(warehousefromDB.getName()).isEqualTo(warehouse.getName());
    }
    @Test
    void shouldReturnWarehouseByName(){
        Warehouse warehouse = Warehouse.builder()
                .city("Gdansk")
                .name("W1")
                .build();

        warehouseRepository.save(warehouse);

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findByName(warehouse.getName());

        Warehouse warehousefromDB = optionalWarehouse.get();
        assertThat(warehousefromDB.getName()).isEqualTo(warehouse.getName());
    }
}
