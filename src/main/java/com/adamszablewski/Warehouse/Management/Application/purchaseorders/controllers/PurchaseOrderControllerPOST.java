package com.adamszablewski.Warehouse.Management.Application.purchaseorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.PurchaseOrderServiceGET;
import com.adamszablewski.Warehouse.Management.Application.purchaseorders.services.PurchaseOrderServicePOST;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class PurchaseOrderControllerPOST {

    PurchaseOrderServicePOST purchaseOrderServicePOST;


}
