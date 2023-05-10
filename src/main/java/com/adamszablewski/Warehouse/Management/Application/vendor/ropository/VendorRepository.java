package com.adamszablewski.Warehouse.Management.Application.vendor.ropository;

import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    Optional<Vendor> findByName(String name);
    void deleteByName(String name);
}
