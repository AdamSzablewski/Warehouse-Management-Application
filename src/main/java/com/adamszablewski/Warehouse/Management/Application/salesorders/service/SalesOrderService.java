package com.adamszablewski.Warehouse.Management.Application.salesorders.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrderItem;
import com.adamszablewski.Warehouse.Management.Application.salesorders.repository.SalesOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SalesOrderService {

    SalesOrderRepository salesOrderRepository;

    InventoryHelper inventoryHelper;

    InventoryRepository inventoryRepository;

    InventoryService inventoryService;


    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderRepository.findAll();
    }

    public Optional<SalesOrder> getAllSalesOrdersById(int id) {
        return salesOrderRepository.findById(id);
    }

    public ResponseEntity<String> createSalesOrder(SalesOrder salesOrder) {
        salesOrderRepository.save(salesOrder);
        return ResponseEntity.ok("Sales order sent");
    }

    public ResponseEntity<String> changeStatusOfSalesOrder(int id) {
        Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(id);
        if (optionalSalesOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        SalesOrder salesOrder = optionalSalesOrder.get();
        salesOrder.setInDelivery(true);
        salesOrderRepository.save(salesOrder);

        for (SalesOrderItem soi : salesOrder.getItems()) {
            Optional<Inventory> optionalInventory = inventoryService.retrieveInventoryByName(soi.getName());
            if (optionalInventory.isEmpty()) {

            } else {
                Inventory inventory = optionalInventory.get();
                inventoryHelper.removeFromInventory(inventory, soi.getTotalAmount());


            }
        }
        return ResponseEntity.ok("Status changed to sent");
    }

    public List<SalesOrder> getAllOrdersForCompany(String company) {
        return salesOrderRepository.findAllByCompany(company);
    }
}
