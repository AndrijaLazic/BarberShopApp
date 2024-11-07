package com.BarberShop.UserApp.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AdminMicroservice",fallback = AdminFallback.class)
@Primary
public interface AdminMSClient {
    @PostMapping("")
    String sendMessage(@RequestBody String message);

    @GetMapping("/Test")
    ResponseEntity<String> test(@RequestParam("state") Boolean state);
}

