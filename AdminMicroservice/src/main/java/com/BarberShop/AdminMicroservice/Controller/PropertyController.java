package com.BarberShop.AdminMicroservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Test")
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

    @GetMapping("/Test")
    public ResponseEntity<String> test(Boolean state) {
        if(state)
            return ResponseEntity.ok("Test is ok");
        return ResponseEntity.badRequest().body("Simulated Bad Request for Circuit Breaker Testing");
    }
}
