package com.adamszablewski.Warehouse.Management.Application.purchaseorders.services;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers.InventoryHelper;
import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrderItem;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.purchaseOrderHelpers.PurchaseOrderHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final InventoryHelper inventoryHelper;
    private final InventoryRepository inventoryRepository;
    private final PurchaseOrderHelper purchaseOrderHelper;

    public ResponseEntity<String> purchase(PurchaseOrder purchaseOrder) {
        return purchaseOrderHelper.purchase(purchaseOrder);
    }

    public ResponseEntity<String> markAsDelivered(int id) {
        Optional<PurchaseOrder> optionalPurchaseOrder = purchaseOrderRepository.findById(id);
        if (optionalPurchaseOrder.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such purchase order exists");
        }

        PurchaseOrder purchaseOrder = optionalPurchaseOrder.get();
        if (purchaseOrder.isDelivered()) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Purchase order already marked as delivered");
        }

        addToInventory(purchaseOrder);
        purchaseOrder.setDelivered(true);
        purchaseOrder.setDateOfDelivery(LocalDate.now());
        purchaseOrderRepository.save(purchaseOrder);

        return ResponseEntity.ok("Products successfully added to inventory");
    }

    private void addToInventory(PurchaseOrder purchaseOrder) {
        for (PurchaseOrderItem p : purchaseOrder.getProducts()) {
            Optional<Inventory> optionalInventory = inventoryRepository.findByName(p.getName());
            if (optionalInventory.isEmpty()) {
                // add exception
            } else {
                Inventory inventory = optionalInventory.get();
                inventoryHelper.addToInventory(inventory, p.getAmount());
                inventory.setAwaitedQuantity(inventory.getAwaitedQuantity() - p.getAmount());
                inventoryRepository.save(inventory);
            }
        }
    }

    public List<PurchaseOrder> getAllPurchaseOrders() {
        System.out.println("called");
        return purchaseOrderRepository.findAll();
    }

    public Optional<PurchaseOrder> getPurchaseOrderById(int id) {
        return purchaseOrderRepository.findById(id);
    }
}
