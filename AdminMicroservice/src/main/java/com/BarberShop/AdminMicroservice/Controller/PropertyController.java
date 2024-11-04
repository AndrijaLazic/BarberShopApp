package com.BarberShop.AdminMicroservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {
    @Autowired
    Environment environment;

    @GetMapping
    public String getProperty() {
        return environment.getProperty("pomPromenljiva");
    }

    @PostMapping
    public String message(@RequestBody String message) {
        System.out.println(message);
        return "Primljena poruka:" + message;
    }
}
