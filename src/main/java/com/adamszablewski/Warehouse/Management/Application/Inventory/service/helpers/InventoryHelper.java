package com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class InventoryHelper {

    PurchaseOrderRepository purchaseOrderRepository;

    public void addToInventory(Inventory inventory, int amount){
        inventory.setQuantity(inventory.getQuantity() + amount);
    }

    public boolean removeFromInventory(Inventory inventory, int amount){
        if (inventory.getQuantity() - amount > inventory.getMinimumStockLevel()){
            int quantityAfter = inventory.getQuantity() - amount;
            int difference = inventory.getMinimumStockLevel() - quantityAfter;
            automaticReorder(inventory, difference);
        }
        if (inventory.getQuantity() - amount < 0) {
            return false;
        }
        inventory.setQuantity(inventory.getQuantity() - amount);
        return true;
    }

    private void automaticReorder(Inventory inventory, int difference){

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .dateOfPurchase(LocalDate.now())
                .netPrice(inventory.getProduct().getUnitCost())
                .product(inventory.getProduct())
                .amount(difference)
                .build();

        purchaseOrderRepository.save(purchaseOrder);
    }
}
