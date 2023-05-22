package com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.purchaseOrderHelpers;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrderItem;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PurchaseOrderHelper {

    PurchaseOrderRepository purchaseOrderRepository;

    InventoryRepository inventoryRepository;

    public ResponseEntity<String> purchase(PurchaseOrder purchaseOrder) {
        purchaseOrderRepository.save(purchaseOrder);
        for (PurchaseOrderItem poi : purchaseOrder.getProducts()){
            Optional<Inventory> optionalInventory = inventoryRepository.findByName(poi.getName());
            if (optionalInventory.isEmpty()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No such inventory exist");
            }
            Inventory inventory = optionalInventory.get();
            inventory.setAwaitedQuantity(inventory.getAwaitedQuantity()+poi.getAmount());
            inventoryRepository.save(inventory);
            System.out.println(purchaseOrder.toString());
        }
        //sending it out
        return ResponseEntity.ok("Purchase order sent succesfully to the vendor");
    }
}
