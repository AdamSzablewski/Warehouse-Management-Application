package com.adamszablewski.Warehouse.Management.Application.salesorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.service.SalesOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SalesOrderControllerDELETE {

    SalesOrderService salesOrderService;

    @DeleteMapping("/sales/id/{id}")
    public ResponseEntity<String> deleteSalesOrderById(@PathVariable int id){
        return salesOrderService.deleteSalesOrderById(id);
    }



}
