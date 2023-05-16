package com.adamszablewski.Warehouse.Management.Application.salesorders.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrderItem;
import com.adamszablewski.Warehouse.Management.Application.salesorders.repository.SalesOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class SalesOrderServiceTest {
    @Mock
    SalesOrderRepository salesOrderRepository;

    SalesOrderService salesOrderService;

    @Mock
    ProductRepository productRepository;
    @Mock
    InventoryHelper inventoryHelper;
    @Mock
    InventoryRepository inventoryRepository;
    @Mock
    InventoryService inventoryService;

    @BeforeEach
    void setUp(){
        salesOrderService = new SalesOrderService(salesOrderRepository, inventoryHelper,
                inventoryRepository, productRepository, inventoryService);
    }

    @Test
    void changeStatusOfSalesOrder_should_return_ok(){

        SalesOrderItem soi1 = SalesOrderItem.builder()
                .name("hammer")
                .totalAmount(4)
                .build();

        SalesOrderItem soi2 = SalesOrderItem.builder()
                .name("saw")
                .totalAmount(3)
                .build();

        Inventory i1 = Inventory.builder()
                .name("hammer")
                .quantity(100)
                .build();

        Inventory i2 = Inventory.builder()
                .name("hammer")
                .quantity(100)
                .build();

        List<SalesOrderItem> soiList = List.of(soi1, soi2);

        SalesOrder salesOrder = SalesOrder.builder()
                .id(1)
                .items(soiList)
                .inDelivery(false)
                .build();

        when(salesOrderRepository.findById(salesOrder.getId())).thenReturn(Optional.of(salesOrder));

        when(inventoryService.retrieveInventoryByName(soi1.getName())).thenReturn(Optional.of(i1));
        when(inventoryService.retrieveInventoryByName(soi2.getName())).thenReturn(Optional.of(i2));



        ResponseEntity<String> response = salesOrderService.changeStatusOfSalesOrderToSent(1);

        assertThat(salesOrder.isInDelivery()).isTrue();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        verify(inventoryHelper, times(2)).removeFromInventory(any(Inventory.class), anyInt());
    }
}
