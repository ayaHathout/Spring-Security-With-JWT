package com.ayahathout.spring_security_with_jwt.controllers;

import com.ayahathout.spring_security_with_jwt.dtos.AuthenticationResponseDTO;
import com.ayahathout.spring_security_with_jwt.dtos.LoginRequestDTO;
import com.ayahathout.spring_security_with_jwt.dtos.RegisterationRequestDTO;
import com.ayahathout.spring_security_with_jwt.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterationRequestDTO registerationRequestDTO) {
        System.out.println("In register method in AuthController");

        AuthenticationResponseDTO authenticationResponseDTO = authService.register(registerationRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        System.out.println("In login method in AuthController");

        AuthenticationResponseDTO authenticationResponseDTO = authService.login(loginRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(authenticationResponseDTO);
    }
}
