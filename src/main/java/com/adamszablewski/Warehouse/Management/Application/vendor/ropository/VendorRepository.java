package com.adamszablewski.Warehouse.Management.Application.vendor.ropository;

import com.adamszablewski.Warehouse.Management.Application.vendor.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

    Optional<Vendor> findByName(String name);
    void deleteByName(String name);
}
