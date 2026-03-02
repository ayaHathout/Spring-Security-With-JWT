package com.ayahathout.spring_security_with_jwt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<?> sayHello() {
        return ResponseEntity.ok("Hello in the authenticated endpoint");
    }
}
