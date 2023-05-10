package com.adamszablewski.Warehouse.Management.Application.Inventory.service.helpers;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class InventoryHelper {

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
// add purchase order class
    }
}
