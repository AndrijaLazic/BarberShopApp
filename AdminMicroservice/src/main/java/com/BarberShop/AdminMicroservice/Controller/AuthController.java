package com.BarberShop.AdminMicroservice.Controller;

import com.BarberShop.AdminMicroservice.DTOS.AdminUserDto;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AdminUserDto adminUserDto) {
        return ResponseEntity.ok().body(adminUserDto.toString());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminUserDto adminUserDto) {
        return ResponseEntity.ok().body(adminUserDto.toString());
    }
}
