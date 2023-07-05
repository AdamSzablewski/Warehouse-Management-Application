package com.adamszablewski.Warehouse.Management.Application.users.repository;

import com.adamszablewski.Warehouse.Management.Application.users.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    Optional<UserInfo> findByUsername(String email);

    boolean existsByUsername(String email);
}
