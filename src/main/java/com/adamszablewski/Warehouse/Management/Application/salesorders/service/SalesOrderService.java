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

    SalesOrderRepository salesOrderRepository;
    InventoryHelper inventoryHelper;
    InventoryRepository inventoryRepository;
    ProductRepository productRepository;
    InventoryService inventoryService;
    SalesOrderConfirmationRepository salesOrderConfirmationRepository;


    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderRepository.findAll();
    }

    public Optional<SalesOrder> getAllSalesOrdersById(int id) {
        return salesOrderRepository.findById(id);
    }

    public ResponseEntity<String> createSalesOrder(SalesOrder salesOrder) {
        if (!areItemsValidChecker(salesOrder)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Some or all of the requested items are not valid");
        }
        salesOrder.setDateReceived(LocalDate.now());
        salesOrder.setInDelivery(false);
        salesOrder.setTransactionClosed(false);
        salesOrder.setTotalAmount(calculateTotalAmountFromSalesOrder(salesOrder));
        salesOrderRepository.save(salesOrder);
        return ResponseEntity.ok("Sales order sent");
    }

    public ResponseEntity<String> changeStatusOfSalesOrderToRecieved(int id) {
        Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(id);
        if (optionalSalesOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        SalesOrder salesOrder = optionalSalesOrder.get();
        salesOrder.setOrderRecieved(true);
        salesOrderRepository.save(salesOrder);
        sendSalesOrderConfirmation(salesOrder);
        inventoryHelper.createAutomaticReordersIfNeeded(salesOrder);
        return ResponseEntity.ok("Status changed to received");
    }

    public ResponseEntity<String> changeStatusOfSalesOrderToInDelivery(int id) {
        Optional<SalesOrder> optionalSalesOrder = salesOrderRepository.findById(id);
        if (optionalSalesOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        SalesOrder salesOrder = optionalSalesOrder.get();

        if (!salesOrder.isOrderRecieved()) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("Status of sales order must first be changed to received");
        }
        if (!inventoryHelper.canCompleteOrder(salesOrder)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Not sufficient amount in inventory to complete order");
        }
        salesOrder.setInDelivery(true);
        salesOrderRepository.save(salesOrder);
        inventoryHelper.removeFromInventory(salesOrder);
        sendInDeliveryMail(salesOrder);
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

        if (!salesOrder.isOrderRecieved()) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                    .body("Status of sales order must first be changed to received");
        }
        if (!inventoryHelper.canCompleteOrder(salesOrder)) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Not sufficient amount in inventory to complete order");
        }
        salesOrder.setInDelivery(true);
        salesOrderRepository.save(salesOrder);
        inventoryHelper.removeFromInventory(salesOrder);
        sendInDeliveryMail(salesOrder, dateOfDelivery);
        return ResponseEntity.ok("Status changed to sent");
    }

    private void sendInDeliveryMail (SalesOrder salesOrder){
        SalesOrderConfirmation salesOrderConfirmation = SalesOrderConfirmation.builder()
                .description("some text")
                .contactEmail(salesOrder.getContactEmail())
                .contactPerson(salesOrder.getContactPerson())
                .deliveryAddress(salesOrder.getDeliveryAdress())
                .company(salesOrder.getCompany())
                .buyerTrackingId(salesOrder.getBuyerTrackingId() == null ? "" : salesOrder.getBuyerTrackingId())
                .purchaseOrder(salesOrder)
                .deliveryDate(calculateDeliveryDate(3))
                .build();

        //further api call here
    }
    private void sendInDeliveryMail (SalesOrder salesOrder, LocalDate date){
        SalesOrderConfirmation salesOrderConfirmation = SalesOrderConfirmation.builder()
                .description("some text")
                .contactEmail(salesOrder.getContactEmail())
                .contactPerson(salesOrder.getContactPerson())
                .deliveryAddress(salesOrder.getDeliveryAdress())
                .company(salesOrder.getCompany())
                .buyerTrackingId(salesOrder.getBuyerTrackingId() == null ? "" : salesOrder.getBuyerTrackingId())
                .purchaseOrder(salesOrder)
                .deliveryDate(date)
                .build();

        //further api call here
    }


        private SalesOrderConfirmation sendSalesOrderConfirmation (SalesOrder salesOrder){
            SalesOrderConfirmation salesOrderConfirmation = SalesOrderConfirmation.builder()
                    .description("some text")
                    .contactEmail(salesOrder.getContactEmail())
                    .contactPerson(salesOrder.getContactPerson())
                    .deliveryAddress(salesOrder.getDeliveryAdress())
                    .company(salesOrder.getCompany())
                    .buyerTrackingId(salesOrder.getBuyerTrackingId() == null ? "" : salesOrder.getBuyerTrackingId())
                    .purchaseOrder(salesOrder)
                    .deliveryDate(calculateDeliveryDate(14))
                    .build();

            salesOrderConfirmationRepository.save(salesOrderConfirmation);
            //further api call here
            return salesOrderConfirmation;
        }

        private LocalDate calculateDeliveryDate (int workdays) {
            LocalDate currentDate = LocalDate.now();

            for (int i = 0; i < workdays; i++) {
                currentDate = currentDate.plusDays(1);
                if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    currentDate = currentDate.plusDays(1);
                }
            }

            return currentDate;
        }

        public List<SalesOrder> getAllOrdersForCompany (String company){
            return salesOrderRepository.findAllByCompany(company);
        }

        private double calculateTotalAmountFromSalesOrder (SalesOrder salesOrder){
            double total = 0;
            for (SalesOrderItem soi : salesOrder.getItems()) {
                Optional<Product> optionalProduct = productRepository.findByProductName(soi.getName());
                Product product = optionalProduct.get();
                total += soi.getTotalAmount() * product.getUnitCost();
            }
            return total;
        }

        private boolean areItemsValidChecker (SalesOrder salesOrder){
            for (SalesOrderItem soi : salesOrder.getItems()) {
                Optional<Product> optionalProduct = productRepository.findByProductName(soi.getName());
                if (optionalProduct.isEmpty()) {
                    return false;
                }
            }
            return true;
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
            salesOrder.setTransactionClosed(true);
            salesOrderRepository.save(salesOrder);

            return ResponseEntity.ok("Status changed to closed");
        }


    public List<SalesOrder> getAllOrdersForBuyerTrackingId(String id) {
        return salesOrderRepository.findAllByBuyerTrackingId(id);
    }
}

