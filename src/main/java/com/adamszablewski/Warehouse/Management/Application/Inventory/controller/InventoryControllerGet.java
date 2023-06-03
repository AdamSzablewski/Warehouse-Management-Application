package com.adamszablewski.Warehouse.Management.Application.Inventory.controller;

import com.adamszablewski.Warehouse.Management.Application.Inventory.Inventory;
import com.adamszablewski.Warehouse.Management.Application.Inventory.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/inventories")
public class InventoryControllerGet {

    InventoryService inventoryService;

    @GetMapping("/get")
    public List<Inventory> retrieveAllInventories(){
        return inventoryService.retrieveAllInventories();
    }

    @GetMapping("/get/id/{inventory_id}")
    public Optional<Inventory> retrieveInventoryById(@PathVariable int inventory_id){
        return inventoryService.retrieveInventoryById(inventory_id);
    }


    @GetMapping("/get/name/{inventory_name}")
    public Optional<Inventory> retrieveInventoryByName(@PathVariable String inventory_name){
        return inventoryService.retrieveInventoryByName(inventory_name);
    }

}
