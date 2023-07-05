package com.adamszablewski.Warehouse.Management.Application.purchaseorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class PurchaseOrderControllerGET {

    PurchaseOrderService purchaseOrderService;

    @GetMapping("/purchase")
    public List<PurchaseOrder> getAllPurchaseOrders(){
        return purchaseOrderService.getAllPurchaseOrders();
    }

    @GetMapping("/purchase/id/{id}")
    public Optional<PurchaseOrder> getPurchaseOrderById(@PathVariable int id){
        return purchaseOrderService.getPurchaseOrderById(id);
    }





}
