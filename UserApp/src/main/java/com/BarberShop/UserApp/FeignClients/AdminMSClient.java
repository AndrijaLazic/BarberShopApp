package com.BarberShop.UserApp.FeignClients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AdminMicroservice",fallback = AdminFallback.class)
@Headers("Authorization: Bearer {token}")
@Primary
public interface AdminMSClient {
    @PostMapping("")
    String sendMessage(@RequestBody String message);

    @GetMapping("/Test/Test")
    ResponseEntity<String> test(@RequestParam("state") Boolean state);

    @GetMapping("/Test/IsAuthorized")
    ResponseEntity<String> testAuthorized();
}

