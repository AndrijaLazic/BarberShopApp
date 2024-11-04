package com.BarberShop.UserApp.Controllers;

import com.BarberShop.UserApp.FeignClients.AdminMSClient;
import org.springframework.beans.factory.annotation.Autowired;
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
}
