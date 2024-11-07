package com.BarberShop.UserApp.Controllers;

import com.BarberShop.UserApp.FeignClients.AdminMSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    AdminMSClient adminMSClient;

    @PostMapping
    public String message(@RequestBody String message) {
        adminMSClient.sendMessage(message);
        return "Poslata poruka poruka:" + message;
    }

    @GetMapping("/Test")
    public ResponseEntity<String> test(Boolean state) {
        return adminMSClient.fail(state);
    }
}
