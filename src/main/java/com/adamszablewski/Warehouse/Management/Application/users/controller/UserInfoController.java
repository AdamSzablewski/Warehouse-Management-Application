package com.adamszablewski.Warehouse.Management.Application.users.controller;

import com.adamszablewski.Warehouse.Management.Application.users.UserInfo;
import com.adamszablewski.Warehouse.Management.Application.users.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class UserInfoController {

    UserInfoService userInfoService;

    @GetMapping("/users")
    public List<UserInfo> getAllUsers(){
        return userInfoService.getAllUsers();
    }
    @GetMapping("/users/id/{user_id}")
    public Optional<UserInfo> getUserById(@PathVariable int user_id){
        return userInfoService.getUserById(user_id);
    }
    @GetMapping("/users/username/{username}")
    public Optional<UserInfo> getUserByName(@PathVariable String username){
        return userInfoService.getUserByName(username);
    }

}
