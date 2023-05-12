package com.adamszablewski.Warehouse.Management.Application.warehouse.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.repository.WarehouseRepository;
import com.adamszablewski.Warehouse.Management.Application.warehouse.services.WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class WarehouseServiceTest {

    @Mock
    WarehouseRepository warehouseRepository;

    WarehouseService warehouseService;

    @BeforeEach
    void setUp(){

        warehouseService = new WarehouseService(warehouseRepository);
    }

    @Test
    void deleteWarehouseById_shouldDelete(){
        Warehouse warehouse = Warehouse.builder()
                .city("Gdansk")
                .id(1)
                .name("G1")
                .build();
        when(warehouseRepository.findById(1)).thenReturn(Optional.of(warehouse));


        ResponseEntity<String> response = warehouseService.deleteWarehouseById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(warehouseRepository).deleteById(1);
    }
    @Test
    void deleteWarehouseById_shouldNotDelete_No_suchID(){
        when(warehouseRepository.findById(1)).thenReturn(Optional.empty());


        ResponseEntity<String> response = warehouseService.deleteWarehouseById(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void deleteWarehouseByName_shouldDelete(){
        Warehouse warehouse = Warehouse.builder()
                .city("Gdansk")
                .id(1)
                .name("G1")
                .build();
        when(warehouseRepository.findByName(warehouse.getName())).thenReturn(Optional.of(warehouse));


        ResponseEntity<String> response = warehouseService.deleteWarehouseByName(warehouse.getName());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(warehouseRepository).deleteByName(warehouse.getName());
    }
    @Test
    void deleteWarehouseByName_shouldNotDelete_No_suchID(){
        when(warehouseRepository.findByName("none")).thenReturn(Optional.empty());


        ResponseEntity<String> response = warehouseService.deleteWarehouseByName("none");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void shouldAddInventoryCategoryToWarehouseById(){
        Warehouse warehouse = Warehouse.builder()
                .city("Gdansk")
                .id(1)
                .name("G1")
                .inventory(new ArrayList<>())
                .build();

        Inventory inventory = Inventory.builder()
                .name("hammer")
                .build();
        when(warehouseRepository.findById(warehouse.getId())).thenReturn(Optional.of(warehouse));

        ResponseEntity<String> response = warehouseService.addInventoryCategoryToWarehouseById(inventory,warehouse.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void shouldNotAddInventoryCategoryToWarehouseById_wrong_id(){

        Inventory inventory = Inventory.builder()
                .name("hammer")
                .build();
        when(warehouseRepository.findById(2)).thenReturn(Optional.empty());

        ResponseEntity<String> response = warehouseService.addInventoryCategoryToWarehouseById(inventory,2);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldAddInventoryCategoryToWarehouseByName(){
        Warehouse warehouse = Warehouse.builder()
                .city("Gdansk")
                .id(1)
                .name("G1")
                .inventory(new ArrayList<>())
                .build();

        Inventory inventory = Inventory.builder()
                .name("hammer")
                .build();
        when(warehouseRepository.findByName(warehouse.getName())).thenReturn(Optional.of(warehouse));

        ResponseEntity<String> response = warehouseService.addInventoryCategoryToWarehouseByName(inventory,warehouse.getName());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void shouldNotAddInventoryCategoryToWarehouseByName_wrong_name(){

        Inventory inventory = Inventory.builder()
                .name("hammer")
                .build();
        when(warehouseRepository.findByName("a1")).thenReturn(Optional.empty());

        ResponseEntity<String> response = warehouseService.addInventoryCategoryToWarehouseByName(inventory,"a1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    void shouldUpdateWarehouse(){

        Warehouse warehouse = Warehouse.builder()
                .city("Gdansk")
                .name("G1")
                .build();
        Warehouse warehouseNew = Warehouse.builder()
                .city("Warszawa")
                .name("WA1")
                .build();
        when(warehouseRepository.findByName(warehouse.getName())).thenReturn(Optional.of(warehouse));

        ResponseEntity<String> response = warehouseService.updateWarehouse(warehouseNew, warehouse.getName());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldNotUpdateWarehouse_WarehouseNotFound(){

        Warehouse warehouseNew = Warehouse.builder()
                .city("Warszawa")
                .name("WA1")
                .build();
        when(warehouseRepository.findByName("GA1")).thenReturn(Optional.empty());

        ResponseEntity<String> response = warehouseService.updateWarehouse(warehouseNew, "GA1");

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


}
