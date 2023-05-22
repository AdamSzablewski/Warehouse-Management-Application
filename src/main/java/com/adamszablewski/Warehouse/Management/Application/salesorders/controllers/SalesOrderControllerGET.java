package com.adamszablewski.Warehouse.Management.Application.salesorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.service.SalesOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class SalesOrderControllerGET {

    SalesOrderService salesOrderService;

    @GetMapping("/sales")
    public List<SalesOrder> getAllSalesOrders(){
        return salesOrderService.getAllSalesOrders();
    }

    @GetMapping("/sales/id/{id}")
    public Optional<SalesOrder> getAllSalesOrdersById(@PathVariable int id){
        return salesOrderService.getAllSalesOrdersById(id);
    }

    @GetMapping("/sales/company/{company}")
    public List<SalesOrder> getAllOrdersForCompany(@PathVariable String company){
        return salesOrderService.getAllOrdersForCompany(company);
    }

    @GetMapping("/sales/custom-id/{id}")
    public List<SalesOrder> getAllOrdersByBuyerTrackingId(@PathVariable String id){
        return salesOrderService.getAllOrdersForBuyerTrackingId(id);
    }

}
