package com.adamszablewski.Warehouse.Management.Application.salesorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.service.SalesOrderService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SalesOrderControllerPOST {

    SalesOrderService salesOrderService;

    @PostMapping("/sales")
    public ResponseEntity<String> newSalesOrder(@Valid @RequestBody SalesOrder salesOrder){
        return salesOrderService.createSalesOrder(salesOrder);
        }
    }

