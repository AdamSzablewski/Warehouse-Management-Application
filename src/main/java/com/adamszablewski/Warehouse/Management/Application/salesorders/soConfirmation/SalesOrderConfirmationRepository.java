package com.adamszablewski.Warehouse.Management.Application.salesorders.soConfirmation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface SalesOrderConfirmationRepository extends JpaRepository<SalesOrderConfirmation, Integer> {
}
