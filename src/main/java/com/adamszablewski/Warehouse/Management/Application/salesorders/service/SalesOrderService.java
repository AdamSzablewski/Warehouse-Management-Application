package com.adamszablewski.Warehouse.Management.Application.salesorders.service;

import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import com.adamszablewski.Warehouse.Management.Application.messages.helpers.MessageSender;
import com.adamszablewski.Warehouse.Management.Application.messages.service.MessageService;
import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.helpers.SalesOrderHelper;
import com.adamszablewski.Warehouse.Management.Application.salesorders.repository.SalesOrderRepository;
import com.adamszablewski.Warehouse.Management.Application.salesorders.soConfirmation.SalesOrderConfirmationRepository;
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

    private final SalesOrderRepository salesOrderRepository;
    private final SalesOrderHelper salesOrderHelper;
    private final InventoryHelper inventoryHelper;
    private final MessageSender messageSender;

    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderRepository.findAll();
    }

    public Optional<SalesOrder> getAllSalesOrdersById(int id) {
        return salesOrderRepository.findById(id);
    }

    public ResponseEntity<String> createSalesOrder(SalesOrder salesOrder) {
        if (!salesOrderHelper.areItemsValidChecker(salesOrder)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some or all of the requested items are not valid");
        }
        salesOrder.setDateReceived(LocalDate.now());
        salesOrder.setInDelivery(false);
        salesOrder.setTransactionClosed(false);
        salesOrder.setTotalAmount(salesOrderHelper.calculateTotalAmountFromSalesOrder(salesOrder));
        salesOrderRepository.save(salesOrder);
        return ResponseEntity.ok("Sales order sent");
    }

    public ResponseEntity<String> changeStatusOfSalesOrderToReceived(int id) {
        Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(id);
        if (optionalSalesOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        SalesOrder salesOrder = optionalSalesOrder.get();
        if (salesOrder.isOrderRecieved() || salesOrder.isInDelivery() || salesOrder.isTransactionClosed()){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("This sales order already has status of received");
        }
        salesOrder.setOrderRecieved(true);
        salesOrderRepository.save(salesOrder);
        messageSender.sendSalesOrderConfirmation(salesOrder);
        inventoryHelper.createAutomaticReordersIfNeeded(salesOrder);
        return ResponseEntity.ok("Status changed to received");
    }

    public ResponseEntity<String> changeStatusOfSalesOrderToInDelivery(int id) {
        Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(id);
        if (optionalSalesOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        SalesOrder salesOrder = optionalSalesOrder.get();

        if (!salesOrder.isOrderRecieved() || salesOrder.isInDelivery() || salesOrder.isTransactionClosed()){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Status of sales order must first be changed to received," +
                    "already is set as in delivery, or transaction has been already closed");
        }

        if (!inventoryHelper.canCompleteOrder(salesOrder)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Not sufficient amount in inventory to complete order");
        }
        salesOrder.setInDelivery(true);
        salesOrderRepository.save(salesOrder);
        inventoryHelper.removeFromInventory(salesOrder);
        messageSender.sendInDeliveryMail(salesOrder);
        return ResponseEntity.ok("Status changed to sent");
    }

    public ResponseEntity<String> changeStatusOfSalesOrderToInDelivery(int id, LocalDate dateOfDelivery) {

        if (dateOfDelivery.isBefore(LocalDate.now())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Delivery date must me in the future");
        }
        Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(id);
        if (optionalSalesOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        SalesOrder salesOrder = optionalSalesOrder.get();

        if (!salesOrder.isOrderRecieved() || salesOrder.isInDelivery() || salesOrder.isTransactionClosed()){
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("Status of sales order must first be changed to received," +
                    "already is set as in delivery, or transaction has been already closed");
        }
        if (!inventoryHelper.canCompleteOrder(salesOrder)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Not sufficient amount in inventory to complete order");
        }
        salesOrder.setInDelivery(true);
        salesOrderRepository.save(salesOrder);
        inventoryHelper.removeFromInventory(salesOrder);
        messageSender.sendInDeliveryMail(salesOrder, dateOfDelivery);
        return ResponseEntity.ok("Status changed to sent");
        }

        public List<SalesOrder> getAllOrdersForCompany (String company){
            return salesOrderRepository.findAllByCompany(company);
        }

        public ResponseEntity<String> deleteSalesOrderById ( int id){
            Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(id);
            if (optionalSalesOrder.isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such sales order exist");
            salesOrderRepository.deleteById(id);
            return ResponseEntity.ok("Sales order removed correctly");
        }

        public ResponseEntity<String> changeStatusOfSalesOrderToClosed ( int id){
            Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(id);
            if (optionalSalesOrder.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            SalesOrder salesOrder = optionalSalesOrder.get();
            if (!salesOrder.isOrderRecieved() || !salesOrder.isInDelivery() || salesOrder.isTransactionClosed()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status of sales order must first be changed to received," +
                        " in delivery, or transaction has been already closed");
            }
            salesOrder.setTransactionClosed(true);
            salesOrderRepository.save(salesOrder);

            messageSender.sendInternalMessageToVendor(salesOrder);

            return ResponseEntity.ok("Status changed to closed");
        }


    public List<SalesOrder> getAllOrdersForBuyerTrackingId(String id) {
        return salesOrderRepository.findAllByBuyerTrackingId(id);
    }
}

