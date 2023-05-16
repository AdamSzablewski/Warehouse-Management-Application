package com.adamszablewski.Warehouse.Management.Application.salesorders.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrderItem;
import com.adamszablewski.Warehouse.Management.Application.salesorders.repository.SalesOrderRepository;
import com.adamszablewski.Warehouse.Management.Application.salesorders.soConfirmation.SalesOrderConfirmation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SalesOrderService {

    SalesOrderRepository salesOrderRepository;

    InventoryHelper inventoryHelper;

    InventoryRepository inventoryRepository;

    ProductRepository productRepository;

    InventoryService inventoryService;


    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderRepository.findAll();
    }

    public Optional<SalesOrder> getAllSalesOrdersById(int id) {
        return salesOrderRepository.findById(id);
    }

    public ResponseEntity<String> createSalesOrder(SalesOrder salesOrder) {
        if (!areItemsValidChecker(salesOrder)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some or all of the requested items are not valid");
        }
        salesOrder.setDateReceived(LocalDate.now());
        salesOrder.setInDelivery(false);
        salesOrder.setTransactionClosed(false);
        salesOrder.setTotalAmount(calculateTotalAmountFromSalesOrder(salesOrder));
        salesOrderRepository.save(salesOrder);
        return ResponseEntity.ok("Sales order sent");
    }

    public ResponseEntity<String> changeStatusOfSalesOrderToSent(int id) {
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
                inventoryRepository.save(inventory);
            }
        }
        sendSalesOrderConfirmation(salesOrder);
        return ResponseEntity.ok("Status changed to sent");
    }

    private SalesOrderConfirmation sendSalesOrderConfirmation(SalesOrder salesOrder) {
        SalesOrderConfirmation salesOrderConfirmation = SalesOrderConfirmation.builder()
                .description("some text")
                .purchaseOrder(salesOrder)
                .deliveryDate(calculateDeliveryDate())
                .build();

        //api
        return salesOrderConfirmation;
    }

    private LocalDate calculateDeliveryDate() {
        LocalDate currentDate = LocalDate.now();
        int workdaysToAdd = 14;

        for (int i = 0; i < workdaysToAdd; i++) {
            currentDate = currentDate.plusDays(1);
            if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                currentDate = currentDate.plusDays(1);
            }
        }

        return currentDate;
    }

    public List<SalesOrder> getAllOrdersForCompany(String company) {
        return salesOrderRepository.findAllByCompany(company);
    }

    private double calculateTotalAmountFromSalesOrder(SalesOrder salesOrder){
        double total = 0;
        for (SalesOrderItem soi : salesOrder.getItems()){
            Optional<Product> optionalProduct = productRepository.findByProductName(soi.getName());
            Product product = optionalProduct.get();
            total += soi.getTotalAmount() * product.getUnitCost();
        }
        return total;
    }

    private boolean areItemsValidChecker(SalesOrder salesOrder){
        for (SalesOrderItem soi : salesOrder.getItems()){
            Optional<Product> optionalProduct = productRepository.findByProductName(soi.getName());
            if (optionalProduct.isEmpty()){
                return false;
            }
        }
        return true;
    }

    public ResponseEntity<String> deleteSalesOrderById(int id) {
        Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(id);
        if (optionalSalesOrder.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such sales order exist");
        salesOrderRepository.deleteById(id);
        return ResponseEntity.ok("Sales order removed correctly");
    }
}
