package com.adamszablewski.Warehouse.Management.Application.purchaseorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PurchaseOrderControllerPUT {

    PurchaseOrderService purchaseOrderService;

    @PutMapping("/purchase/delivered/id/{id}")
    public ResponseEntity<String> markAsDelivered(@PathVariable int id){
        return purchaseOrderService.markAsDelivered(id);
    }


}
