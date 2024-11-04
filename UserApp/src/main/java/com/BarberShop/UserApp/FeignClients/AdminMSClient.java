package com.BarberShop.UserApp.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AdminMicroservice",url = "http://192.168.0.200:8081")
public interface AdminMSClient {
    @PostMapping("")
    String sendMessage(@RequestBody String message);
}
