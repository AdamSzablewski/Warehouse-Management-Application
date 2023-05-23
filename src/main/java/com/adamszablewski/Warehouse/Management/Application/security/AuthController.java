//package com.adamszablewski.Warehouse.Management.Application.security;
//
//
//import com.adamszablewski.Warehouse.Management.Application.dto.AuthResponseDto;
//import com.adamszablewski.Warehouse.Management.Application.dto.LoginDto;
//import com.adamszablewski.Warehouse.Management.Application.dto.RegisterDto;
//import com.adamszablewski.Warehouse.Management.Application.role.Role;
//import com.adamszablewski.Warehouse.Management.Application.role.RoleRepository;
//import com.adamszablewski.Warehouse.Management.Application.users.UserInfo;
//import com.adamszablewski.Warehouse.Management.Application.users.repository.UserInfoRepository;
//
//import lombok.AllArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Collections;
//
//@RestController
//@RequestMapping("/auth")
//@Service
//@AllArgsConstructor
//public class AuthController {
//
//    private AuthenticationManager authenticationManager;
//    private com.adamszablewski.Warehouse.Management.Application.users.repository.UserInfoRepository UserInfoRepository;
//    private PasswordEncoder passwordEncoder;
//    private RoleRepository roleRepository;
//    private TokenGenerator tokenGenerator;
//
//
//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto){
//        if (UserInfoRepository.existsByUsername(registerDto.getEmail())){
//            return new ResponseEntity<>("username is taken", HttpStatus.BAD_REQUEST);
//        }
//        UserInfo user = new UserInfo();
//        user.setUsername(registerDto.getEmail());
//        user.setName(registerDto.getFirstName());
//        user.setLastName(registerDto.getLastName());
//        Role role = roleRepository.findByName("CUSTOMER").get();
//        System.out.println(role);
//        user.setRoles(Collections.singletonList(role));
//        String password = passwordEncoder.encode(registerDto.getPassword());
//        user.setPassword(password);
//        System.out.println(password);
//
//
//
//        UserInfoRepository.save(user);
//        return new ResponseEntity<>("User registered", HttpStatus.OK);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto user){
//        System.out.println(user);
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String token = tokenGenerator.generateToken(authentication);
//
//        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
//    }
//
//}
