package com.adamszablewski.Warehouse.Management.Application.purchaseorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.purchaseorders.PurchaseOrder;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PurchaseOrderControllerPOST {

    PurchaseOrderService purchaseOrderService;

    @PostMapping("/purchase")
    public ResponseEntity<String> purchase(@RequestBody PurchaseOrder purchaseOrder){
        return purchaseOrderService.purchase(purchaseOrder);
    }


}
