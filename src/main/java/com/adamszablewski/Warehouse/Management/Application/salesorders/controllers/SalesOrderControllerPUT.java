package com.adamszablewski.Warehouse.Management.Application.salesorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.salesorders.service.SalesOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@AllArgsConstructor
public class SalesOrderControllerPUT {

    SalesOrderService salesOrderService;

    @PutMapping("sales/receive/id{id}")
    public ResponseEntity<String> changeStatusOfSalesOrderToReceived(@PathVariable int id){
        return salesOrderService.changeStatusOfSalesOrderToReceived(id);
    }

    @PutMapping("sales/send/id{id}")
    public ResponseEntity<String> changeStatusOfSalesOrderToInDeliverAuto(@PathVariable int id){
        return salesOrderService.changeStatusOfSalesOrderToInDelivery(id);
    }

    @PutMapping("sales/send/id{id}/custom")
    public ResponseEntity<String> changeStatusOfSalesOrderToInDeliverCustom(@PathVariable int id, @RequestBody LocalDate date){
        return salesOrderService.changeStatusOfSalesOrderToInDelivery(id, date);
    }

    @PutMapping("sales/close/id{id}")
    public ResponseEntity<String> changeStatusOfSalesOrderToClosed(@PathVariable int id){
        return salesOrderService.changeStatusOfSalesOrderToClosed(id);
    }
}
