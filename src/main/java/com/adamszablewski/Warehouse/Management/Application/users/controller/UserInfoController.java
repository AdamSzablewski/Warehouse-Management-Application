package com.adamszablewski.Warehouse.Management.Application.users.controller;

import com.adamszablewski.Warehouse.Management.Application.users.UserInfo;
import com.adamszablewski.Warehouse.Management.Application.users.service.UserInfoService;
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
public class UserInfoController {

    private final UserInfoService userInfoService;

    @GetMapping("/users")
    public List<UserInfo> getAllUsers(){
        return userInfoService.getAllUsers();
    }
    @GetMapping("/users/username/{username}")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('EMPLOYEE') or principal.username == #username")
    public Optional<UserInfo> getUserByName(@PathVariable String username){
        return userInfoService.getUserByName(username);
    }

}
