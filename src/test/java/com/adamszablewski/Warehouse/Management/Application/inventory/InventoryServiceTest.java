package com.adamszablewski.Warehouse.Management.Application.inventory;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class InventoryServiceTest {

    InventoryService inventoryService;

    @Mock
    InventoryRepository inventoryRepository;

    @Mock
    InventoryHelper inventoryHelper;

    @BeforeEach
    void setup(){
        inventoryService = new InventoryService(inventoryRepository, inventoryHelper);

    }


    @Test
    void addItemsToInventoryById_shouldReturnOk(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(20)
                .build();

        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.of(inventory));

        ResponseEntity<String> response = inventoryService.addItemsToInventoryById(inventory.getId(), 5);

        verify(inventoryRepository).save(inventory);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void addItemsToInventoryById_shouldReturnNotFound(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(20)
                .build();

        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.empty());

        ResponseEntity<String> response = inventoryService.addItemsToInventoryById(inventory.getId(), 5);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void addItemsToInventoryByName_shouldReturnOk(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(20)
                .build();

        when(inventoryRepository.findByName(inventory.getName())).thenReturn(Optional.of(inventory));

        ResponseEntity<String> response = inventoryService.addItemsToInventoryByName(inventory.getName(), 5);

        verify(inventoryRepository).save(inventory);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void addItemsToInventoryByName_shouldReturnNotFound(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(20)
                .build();

        when(inventoryRepository.findByName(inventory.getName())).thenReturn(Optional.empty());

        ResponseEntity<String> response = inventoryService.addItemsToInventoryByName(inventory.getName(), 5);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void updateInventory_shouldUpdateAllFields(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(20)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();
        Inventory newInventory = Inventory.builder()
                .name("big hammer")
                .id(1)
                .quantity(30)
                .minimumStockLevel(40)
                .maximumStockLevel(150)
                .storageLocation("B2")
                .build();
        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.of(inventory));

        ResponseEntity<String> response = inventoryService.updateInventory(newInventory, 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(inventoryRepository).save(inventory);
    }
    @Test
    void updateInventory_shouldReturnNotFound(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(20)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();
        Inventory newInventory = Inventory.builder()
                .name("big hammer")
                .id(1)
                .quantity(30)
                .minimumStockLevel(40)
                .maximumStockLevel(150)
                .storageLocation("B2")
                .build();
        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.empty());

        ResponseEntity<String> response = inventoryService.updateInventory(newInventory, 1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }

    @Test
    void removeItemsFromInventoryByName_shouldReturnOk(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();
        Inventory updatedInventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(20)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();

        when(inventoryRepository.findByName(inventory.getName())).thenReturn(Optional.of(inventory));
        when(inventoryHelper.removeFromInventory(inventory, 20)).thenReturn(updatedInventory);

        ResponseEntity<String> response = inventoryService.removeItemsFromInventoryByName(inventory.getName(), 20);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(inventoryRepository).save(inventory);

    }
    @Test
    void removeItemsFromInventoryByName_shouldReturnMethodNotFound(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();


        when(inventoryRepository.findByName(inventory.getName())).thenReturn(Optional.empty());


        ResponseEntity<String> response = inventoryService.removeItemsFromInventoryByName(inventory.getName(), 20);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    void removeItemsFromInventoryByName_shouldReturnMethodNotAllowed(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();
        Inventory updatedInventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(20)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();

        when(inventoryRepository.findByName(inventory.getName())).thenReturn(Optional.of(inventory));
        when(inventoryHelper.removeFromInventory(inventory, 20)).thenReturn(null);

        ResponseEntity<String> response = inventoryService.removeItemsFromInventoryByName(inventory.getName(), 20);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);


    }

    @Test
    void removeItemsFromInventoryById_shouldReturnOk(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();
        Inventory updatedInventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(20)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();

        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.of(inventory));
        when(inventoryHelper.removeFromInventory(inventory, 20)).thenReturn(updatedInventory);

        ResponseEntity<String> response = inventoryService.removeItemsToInventoryById(inventory.getId(), 20);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(inventoryRepository).save(inventory);

    }
    @Test
    void removeItemsFromInventoryById_shouldReturnMethodNotFound(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();


        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.empty());


        ResponseEntity<String> response = inventoryService.removeItemsToInventoryById(inventory.getId(), 20);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
    @Test
    void removeItemsFromInventoryById_shouldReturnMethodNotAllowed(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();

        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.of(inventory));
        when(inventoryHelper.removeFromInventory(inventory, 20)).thenReturn(null);

        ResponseEntity<String> response = inventoryService.removeItemsToInventoryById(inventory.getId(), 20);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);


    }

    @Test
    void deleteInventoryById_shouldReturnOk(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();
        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.of(inventory));

        ResponseEntity<String> response = inventoryService.deleteInventoryById(inventory.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
       verify(inventoryRepository).deleteById(inventory.getId());
    }

    @Test
    void deleteInventoryById_shouldReturnNotFound(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();
        when(inventoryRepository.findById(inventory.getId())).thenReturn(Optional.empty());

        ResponseEntity<String> response = inventoryService.deleteInventoryById(inventory.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
    @Test
    void deleteInventoryByName_shouldReturnOk(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();
        when(inventoryRepository.findByName(inventory.getName())).thenReturn(Optional.of(inventory));

        ResponseEntity<String> response = inventoryService.deleteInventoryByName(inventory.getName());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(inventoryRepository).deleteByName(inventory.getName());
    }

    @Test
    void deleteInventoryByName_shouldReturnNotFound(){
        Inventory inventory = Inventory.builder()
                .name("hammer")
                .id(1)
                .quantity(70)
                .minimumStockLevel(20)
                .maximumStockLevel(100)
                .storageLocation("A1")
                .build();
        when(inventoryRepository.findByName(inventory.getName())).thenReturn(Optional.empty());

        ResponseEntity<String> response = inventoryService.deleteInventoryByName(inventory.getName());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);

    }
}
