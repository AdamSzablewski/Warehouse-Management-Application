package com.adamszablewski.Warehouse.Management.Application.salesorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.salesorders.service.SalesOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SalesOrderControllerPUT {

    SalesOrderService salesOrderService;

    @PutMapping("sales/receive/id{id}")
    public ResponseEntity<String> changeStatusOfSalesOrderToReceived(@PathVariable int id){
        return salesOrderService.changeStatusOfSalesOrderToRecieved(id);
    }

    @PutMapping("sales/send/id{id}")
    public ResponseEntity<String> changeStatusOfSalesOrderToSent(@PathVariable int id){
        return salesOrderService.changeStatusOfSalesOrderToSent(id);
    }

    @PutMapping("sales/close/id{id}")
    public ResponseEntity<String> changeStatusOfSalesOrderToClosed(@PathVariable int id){
        return salesOrderService.changeStatusOfSalesOrderToClosed(id);
    }
}
