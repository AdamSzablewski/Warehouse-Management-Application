package com.adamszablewski.Warehouse.Management.Application.salesorders.repository;

import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Integer> {

    List<SalesOrder> findAllByCompany(String company);
    List<SalesOrder> findAllByBuyerTrackingId(String company);
}
