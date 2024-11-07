package com.BarberShop.UserApp.FeignClients;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminFallback implements AdminMSClient {

    @Override
    public String sendMessage(String message) {
        return "Admin service is not available";
    }

    @Override
    public ResponseEntity<String> fail(Boolean state) {
        return ResponseEntity.internalServerError().body("Admin service is not available");
    }
}
