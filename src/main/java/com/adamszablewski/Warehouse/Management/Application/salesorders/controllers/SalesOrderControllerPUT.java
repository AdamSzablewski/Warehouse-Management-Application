package com.adamszablewski.Warehouse.Management.Application.salesorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.salesorders.service.SalesOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/sales/status")
public class SalesOrderControllerPUT {

    SalesOrderService salesOrderService;

    @PutMapping("/receive/id{id}")
    public ResponseEntity<String> changeStatusOfSalesOrderToReceived(@PathVariable int id){
        return salesOrderService.changeStatusOfSalesOrderToReceived(id);
    }

    @PutMapping("/in_delivery/id{id}")
    public ResponseEntity<String> changeStatusOfSalesOrderToInDeliverAuto(@PathVariable int id){
        return salesOrderService.changeStatusOfSalesOrderToInDelivery(id);
    }

    @PutMapping("/in_delivery/id{id}/custom")
    public ResponseEntity<String> changeStatusOfSalesOrderToInDeliverCustom(@PathVariable int id, @RequestBody LocalDate date){
        return salesOrderService.changeStatusOfSalesOrderToInDelivery(id, date);
    }

    @PutMapping("/close/id{id}")
    public ResponseEntity<String> changeStatusOfSalesOrderToClosed(@PathVariable int id){
        return salesOrderService.changeStatusOfSalesOrderToClosed(id);
    }
}
