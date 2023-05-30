package com.adamszablewski.Warehouse.Management.Application.salesorders.helpers;

import com.adamszablewski.Warehouse.Management.Application.product.Product;
import com.adamszablewski.Warehouse.Management.Application.product.repository.ProductRepository;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrder;
import com.adamszablewski.Warehouse.Management.Application.salesorders.SalesOrderItem;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Optional;
@AllArgsConstructor
@Component
public class SalesOrderHelper {

    private final ProductRepository productRepository;

    public double calculateTotalAmountFromSalesOrder(SalesOrder salesOrder){
        double total = 0;
        for (SalesOrderItem soi : salesOrder.getItems()) {
            Optional<Product> optionalProduct = productRepository.findByProductName(soi.getName());
            Product product = optionalProduct.get();
            total += soi.getTotalAmount() * product.getUnitCost();
        }
        return total;
    }

    public boolean areItemsValidChecker(SalesOrder salesOrder){
        for (SalesOrderItem soi : salesOrder.getItems()) {
            Optional<Product> optionalProduct = productRepository.findByProductName(soi.getName());
            if (optionalProduct.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public LocalDate calculateDeliveryDate(int workdays) {
        LocalDate currentDate = LocalDate.now();

        for (int i = 0; i < workdays; i++) {
            currentDate = currentDate.plusDays(1);
            if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                currentDate = currentDate.plusDays(1);
            }
        }

        return currentDate;
    }
}
