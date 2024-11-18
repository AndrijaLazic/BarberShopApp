package com.BarberShop.AdminMicroservice.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public ResponseEntity<String> test(@RequestParam(name = "state") Boolean state) {
        if(state)
            return ResponseEntity.ok("Test is ok");
        return ResponseEntity.badRequest().body("Simulated Bad Request for Circuit Breaker Testing");
    }

    @GetMapping("/IsAuthorized")
    public ResponseEntity<String> notAuthorized(@RequestHeader Map<String, String> headers) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication){
            return ResponseEntity.status(200).body("Authorized"+authentication.getName());
        }else{
            return ResponseEntity.status(200).body("Not Authorized route");
        }
    }
}
