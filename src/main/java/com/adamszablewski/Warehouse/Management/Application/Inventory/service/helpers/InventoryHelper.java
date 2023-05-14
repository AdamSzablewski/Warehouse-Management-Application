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

    public int addToInventory(Inventory inventory, int amount){int updatedInventoryLevel = inventory.getQuantity() + amount;
       inventory.setQuantity(updatedInventoryLevel);
       return updatedInventoryLevel;
    }

    public Inventory removeFromInventory(Inventory inventory, int amount){
        if (inventory.getQuantity() - amount > inventory.getMinimumStockLevel()){
            int quantityAfter = inventory.getQuantity() - amount;
            int difference = inventory.getMinimumStockLevel() - quantityAfter;
            automaticReorder(inventory, difference);
        }
        if (inventory.getQuantity() - amount < 0) {
            return null;
        }
        inventory.setQuantity(inventory.getQuantity() - amount);
        return inventory;
    }

    private PurchaseOrder automaticReorder(Inventory inventory, int difference){

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
        return purchaseOrder;
    }
}
