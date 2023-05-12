package com.adamszablewski.Warehouse.Management.Application.purchaseOrder.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrderItem;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.PurchaseOrderService;
import com.adamszablewski.Warehouse.Management.Application.warehouse.services.WarehouseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class PurchaseOrderServiceTest {

    PurchaseOrderService purchaseOrderService;

    @Mock
    InventoryRepository inventoryRepository;

    @Mock
    InventoryHelper inventoryHelper;


    @Mock
    PurchaseOrderRepository purchaseOrderRepository;

    @BeforeEach
    void setUp(){
        purchaseOrderService = new PurchaseOrderService(purchaseOrderRepository, inventoryHelper, inventoryRepository);
    }

    @Test
    void markAsDeliveredTest_should_return_Ok(){
        Product pr1 = Product.builder()
                .productName("hammer")
                .unitCost(15)
                .build();
        Product pr2 = Product.builder()
                .productName("saw")
                .unitCost(24)
                .build();

        PurchaseOrderItem p1 = PurchaseOrderItem.builder()
                .amount(2)
                .netPrice(pr1.getUnitCost())
                .build();
        PurchaseOrderItem p2 = PurchaseOrderItem.builder()
                .amount(4)
                .netPrice(pr2.getUnitCost())
                .build();

        List<PurchaseOrderItem> products = List.of(p1, p2);
        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .dateOfPurchase(LocalDate.of(2023, 5, 3))
                .delivered(false)
                .POid(1)
                .products(products)
                .dateOfDelivery(null)
                .build();

        Inventory i1 = Inventory.builder()
                .name("hammer")
                .quantity(100)
                .build();
        Inventory i2 = Inventory.builder()
                .name("hammer")
                .quantity(100)
                .build();


        when(purchaseOrderRepository.findById(purchaseOrder.getPOid())).thenReturn(Optional.of(purchaseOrder));


        when(inventoryRepository.findByName(p1.getName())).thenReturn(Optional.of(i1));
        when(inventoryRepository.findByName(p2.getName())).thenReturn(Optional.of(i2));



        ResponseEntity<String> response = purchaseOrderService.markAsDelivered(1);




        verify(inventoryHelper, times(2)).addToInventory(any(Inventory.class), anyInt());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(purchaseOrder.isDelivered());
        assertNotNull(purchaseOrder.getDateOfDelivery());
        verify(inventoryRepository, times(2)).save(any(Inventory.class));
    }

    @Test
    void markAsDeliveredTest_should_return_NOT_FOUND(){

        when(purchaseOrderRepository.findById(1)).thenReturn(Optional.empty());

        ResponseEntity<String> response = purchaseOrderService.markAsDelivered(1);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

}
