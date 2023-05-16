package com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrderItem;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.PurchaseOrderService;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.purchaseOrderHelpers.PurchaseOrderHelper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class InventoryHelper {

    PurchaseOrderRepository purchaseOrderRepository;
    //PurchaseOrderService purchaseOrderService;
    PurchaseOrderHelper purchaseOrderHelper;




    public int addToInventory(Inventory inventory, int amount){int updatedInventoryLevel = inventory.getQuantity() + amount;
       inventory.setQuantity(updatedInventoryLevel);
       return updatedInventoryLevel;
    }

    public Inventory removeFromInventory(Inventory inventory, int amount){
        int theoreticalQuantity = inventory.getQuantity() + inventory.getAwaitedQuantity();

        if (inventory.getQuantity() - amount < inventory.getMinimumStockLevel()){
            int quantityAfter = theoreticalQuantity - amount;
            int difference = inventory.getMinimumStockLevel() - quantityAfter;
            automaticReorder(inventory, difference);
        }

            inventory.setQuantity(inventory.getQuantity() - amount);



        return inventory;
    }

    public PurchaseOrder automaticReorder(Inventory inventory, int difference){

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

        purchaseOrderHelper.purchase(purchaseOrder);
        return purchaseOrder;
    }


}
