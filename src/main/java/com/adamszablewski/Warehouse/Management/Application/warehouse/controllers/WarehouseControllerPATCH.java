package com.adamszablewski.Warehouse.Management.Application.warehouse.controllers;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.warehouse.Warehouse;
import com.adamszablewski.Warehouse.Management.Application.warehouse.services.WarehouseServicePATCH;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@AllArgsConstructor
public class WarehouseControllerPATCH {

    WarehouseServicePATCH warehouseServicePATCH;

    @PatchMapping("/warehouses/add_inventories/warehouse/id/{warehouseId}")
    public ResponseEntity<String> addInventoryCategoryToWarehouse(@RequestBody Inventory inventory, int warehouseId){
        return warehouseServicePATCH.addInventoryCategoryToWarehouseById(inventory, warehouseId);
    }

    @PatchMapping("/warehouses/add_inventories/warehouse/name/{name}")
    public ResponseEntity<String> addInventoryCategoryToWarehouse(@RequestBody  Inventory inventory, String name){
        return warehouseServicePATCH.addInventoryCategoryToWarehouseByName(inventory, name);
    }
}
