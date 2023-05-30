package com.adamszablewski.Warehouse.Management.Application.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import com.adamszablewski.Warehouse.Management.Application.messages.helpers.MessageSender;
import com.adamszablewski.Warehouse.Management.Application.messages.service.MessageService;
import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrderItem;
import com.adamszablewski.Warehouse.Management.Application.salesorders.helpers.SalesOrderHelper;
import com.adamszablewski.Warehouse.Management.Application.salesorders.repository.SalesOrderRepository;
import com.adamszablewski.Warehouse.Management.Application.salesorders.service.SalesOrderService;
import com.adamszablewski.Warehouse.Management.Application.salesorders.soConfirmation.SalesOrderConfirmationRepository;
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
    InventoryHelper inventoryHelper;
    @Mock
    MessageSender messageSender;

    @Mock
    SalesOrderHelper salesOrderHelper;

    @BeforeEach
    void setUp(){
        salesOrderService = new SalesOrderService(salesOrderRepository, salesOrderHelper, inventoryHelper,
                messageSender);
    }

    @Test
    void changeStatusOfSalesOrderToRecieved_should_return_ok(){

        SalesOrderItem soi1 = SalesOrderItem.builder()
                .name("hammer")
                .totalAmount(4)
                .build();

        SalesOrderItem soi2 = SalesOrderItem.builder()
                .name("saw")
                .totalAmount(3)
                .build();

        List<SalesOrderItem> soiList = List.of(soi1, soi2);

        SalesOrder salesOrder = SalesOrder.builder()
                .id(1)
                .items(soiList)
                .inDelivery(false)
                .build();
        when(salesOrderRepository.findById(salesOrder.getId())).thenReturn(Optional.of(salesOrder));

        ResponseEntity<String> response = salesOrderService.changeStatusOfSalesOrderToReceived(1);

        assertThat(salesOrder.isOrderRecieved()).isTrue();
        verify(salesOrderRepository).save(any(SalesOrder.class));
        verify(inventoryHelper).createAutomaticReordersIfNeeded(any(SalesOrder.class));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void changeStatusOfSalesOrderToRecieved_should_return_NOT_FOUND(){

        SalesOrderItem soi1 = SalesOrderItem.builder()
                .name("hammer")
                .totalAmount(4)
                .build();

        SalesOrderItem soi2 = SalesOrderItem.builder()
                .name("saw")
                .totalAmount(3)
                .build();

        List<SalesOrderItem> soiList = List.of(soi1, soi2);

        SalesOrder salesOrder = SalesOrder.builder()
                .id(1)
                .items(soiList)
                .inDelivery(false)
                .build();

        when(salesOrderRepository.findById(salesOrder.getId())).thenReturn(Optional.empty());


        ResponseEntity<String> response = salesOrderService.changeStatusOfSalesOrderToReceived(1);


        assertThat(salesOrder.isOrderRecieved()).isFalse();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

  @Test
    void changeStatusOfSalesOrderToInDelivery_SHOULD_RETURN_OK(){
      SalesOrderItem soi1 = SalesOrderItem.builder()
              .name("hammer")
              .totalAmount(4)
              .build();

      SalesOrderItem soi2 = SalesOrderItem.builder()
              .name("saw")
              .totalAmount(3)
              .build();

      List<SalesOrderItem> soiList = List.of(soi1, soi2);

      SalesOrder salesOrder = SalesOrder.builder()
              .id(1)
              .items(soiList)
              .orderRecieved(true)
              .inDelivery(false)
              .build();

        when(salesOrderRepository.findById(salesOrder.getId())).thenReturn(Optional.of(salesOrder));
        when(inventoryHelper.canCompleteOrder(salesOrder)).thenReturn(true);

        ResponseEntity<String> response = salesOrderService.changeStatusOfSalesOrderToInDelivery(salesOrder.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
  }
    @Test
    void changeStatusOfSalesOrderToInDelivery_SHOULD_RETURN_NOT_FOUND(){
        SalesOrderItem soi1 = SalesOrderItem.builder()
                .name("hammer")
                .totalAmount(4)
                .build();

        SalesOrderItem soi2 = SalesOrderItem.builder()
                .name("saw")
                .totalAmount(3)
                .build();

        List<SalesOrderItem> soiList = List.of(soi1, soi2);

        SalesOrder salesOrder = SalesOrder.builder()
                .id(1)
                .items(soiList)
                .orderRecieved(true)
                .inDelivery(false)
                .build();

        when(salesOrderRepository.findById(salesOrder.getId())).thenReturn(Optional.empty());

        ResponseEntity<String> response = salesOrderService.changeStatusOfSalesOrderToInDelivery(salesOrder.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void changeStatusOfSalesOrderToInDelivery_SHOULD_RETURN_METHOD_NOT_ALLOWED(){
        SalesOrderItem soi1 = SalesOrderItem.builder()
                .name("hammer")
                .totalAmount(4)
                .build();

        SalesOrderItem soi2 = SalesOrderItem.builder()
                .name("saw")
                .totalAmount(3)
                .build();

        List<SalesOrderItem> soiList = List.of(soi1, soi2);

        SalesOrder salesOrder = SalesOrder.builder()
                .id(1)
                .items(soiList)
                .orderRecieved(false)
                .inDelivery(false)
                .build();

        when(salesOrderRepository.findById(salesOrder.getId())).thenReturn(Optional.of(salesOrder));

        ResponseEntity<String> response = salesOrderService.changeStatusOfSalesOrderToInDelivery(salesOrder.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Test
    void changeStatusOfSalesOrderToInDelivery_SHOULD_RETURN_CONFLICT(){
        SalesOrderItem soi1 = SalesOrderItem.builder()
                .name("hammer")
                .totalAmount(4)
                .build();

        SalesOrderItem soi2 = SalesOrderItem.builder()
                .name("saw")
                .totalAmount(3)
                .build();

        List<SalesOrderItem> soiList = List.of(soi1, soi2);

        SalesOrder salesOrder = SalesOrder.builder()
                .id(1)
                .items(soiList)
                .orderRecieved(true)
                .inDelivery(false)
                .build();

        when(salesOrderRepository.findById(salesOrder.getId())).thenReturn(Optional.of(salesOrder));
        when(inventoryHelper.canCompleteOrder(salesOrder)).thenReturn(false);
        ResponseEntity<String> response = salesOrderService.changeStatusOfSalesOrderToInDelivery(salesOrder.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
    }


}
