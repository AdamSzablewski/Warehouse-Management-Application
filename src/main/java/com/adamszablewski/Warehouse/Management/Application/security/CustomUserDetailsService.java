//package com.adamszablewski.Warehouse.Management.Application.security;
//
//
//import com.adamszablewski.Warehouse.Management.Application.role.Role;
//import com.adamszablewski.Warehouse.Management.Application.users.UserInfo;
//import com.adamszablewski.Warehouse.Management.Application.users.repository.UserInfoRepository;
//
//import lombok.AllArgsConstructor;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private UserInfoRepository UserInfoRepository;
//
//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserInfo user = UserInfoRepository.findByUsername(username)
//                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
//        return new User(
//                user.getUsername()
//                ,user.getPassword()
//                ,mapRolesTOAuthorities(user.getRoles())
//        );
//    }
//
//
//    private Collection<GrantedAuthority> mapRolesTOAuthorities(List<Role> roles){
//        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
//    }
//}
