package com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrderItem;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

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

        int minimumPurchaseAmount = 0;
        if (inventory.getReorderQuantity() >= difference){
            minimumPurchaseAmount = inventory.getReorderQuantity();
        }
        else {
            minimumPurchaseAmount =difference + difference % inventory.getReorderQuantity();
        }



        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .dateOfPurchase(LocalDate.now())
                .products(List.of(
                        PurchaseOrderItem.builder()
                                .name(inventory.getName())
                                .amount(minimumPurchaseAmount)
                                .netPrice(inventory.getProduct().getUnitCost())
                                .build())
                )
                .netPrice(inventory.getProduct().getUnitCost()*minimumPurchaseAmount)
                .build();

        purchaseOrderRepository.save(purchaseOrder);
    }
}
