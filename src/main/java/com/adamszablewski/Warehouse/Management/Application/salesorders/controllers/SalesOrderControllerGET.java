package com.adamszablewski.Warehouse.Management.Application.salesorders.controllers;

import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.service.SalesOrderService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class SalesOrderControllerGET {

    SalesOrderService salesOrderService;

    @GetMapping("/sales")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public List<SalesOrder> getAllSalesOrders(){
        return salesOrderService.getAllSalesOrders();
    }

    @GetMapping("/sales/username/{username}/id/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or principal.username == #username")
    public Optional<SalesOrder> getAllSalesOrdersById(@PathVariable int id){
        return salesOrderService.getAllSalesOrdersById(id);
    }

    @GetMapping("/sales/company/{company}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE')")
    public List<SalesOrder> getAllOrdersForCompany(@PathVariable String company){
        return salesOrderService.getAllOrdersForCompany(company);
    }

    @GetMapping("/sales/username/{username}/custom-id/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or principal.username == #username")
    public List<SalesOrder> getAllOrdersByBuyerTrackingId(@PathVariable String id){
        return salesOrderService.getAllOrdersForBuyerTrackingId(id);
    }

}
