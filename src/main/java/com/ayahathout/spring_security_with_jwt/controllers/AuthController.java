package com.ayahathout.spring_security_with_jwt.controllers;

import com.ayahathout.spring_security_with_jwt.dtos.LoginRequestDTO;
import com.ayahathout.spring_security_with_jwt.dtos.RegisterationRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterationRequestDTO registerationRequestDTO) {
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return null;
    }
}
