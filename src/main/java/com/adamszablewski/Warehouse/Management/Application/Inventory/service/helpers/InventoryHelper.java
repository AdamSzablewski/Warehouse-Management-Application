package com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.repository.InventoryRepository;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrderItem;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.repository.PurchaseOrderRepository;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.purchaseOrderHelpers.PurchaseOrderHelper;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrderItem;
import lombok.AllArgsConstructor;
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

    InventoryRepository inventoryRepository;




    public int addToInventory(Inventory inventory, int amount){int updatedInventoryLevel = inventory.getQuantity() + amount;
       inventory.setQuantity(updatedInventoryLevel);
       return updatedInventoryLevel;
    }

    public void createAutomaticReordersIfNeeded(SalesOrder salesOrder){

        int amount = 0;

        for (SalesOrderItem soi : salesOrder.getItems()) {
            amount = soi.getTotalAmount();
            Optional<Inventory> optionalInventory = inventoryRepository.findByName(soi.getName());
            if (optionalInventory.isEmpty()) {

            } else {
                Inventory inventory = optionalInventory.get();
                int totalAmount = 0;

                if (inventory.getQuantity() - amount < inventory.getMinimumStockLevel()){
                    if(inventory.getAwaitedQuantity() > 0 ){
                        totalAmount = soi.getTotalAmount();
                    }else {
                        int quantityAfter = inventory.getQuantity() - amount;
                        totalAmount = inventory.getMinimumStockLevel() - quantityAfter;
                    }
                    automaticReorder(inventory, totalAmount);
                }
            }
        }

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

    public boolean removeFromInventoryNoReorder(Inventory inventory, int amount){
        if (inventory.getQuantity() - amount < 0 ){
            return false;
        }
        inventory.setQuantity(inventory.getQuantity() - amount);
        return true;
    }

    public boolean checkIfReorderNeeded(Inventory inventory, int amount){
        return inventory.getQuantity() - amount < 0;
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
