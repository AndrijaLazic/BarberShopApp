package com.BarberShop.AdminMicroservice.Controller;

import com.BarberShop.AdminMicroservice.DTOS.AdminLoginDto;
import com.BarberShop.AdminMicroservice.DTOS.AdminUserDto;
import com.BarberShop.AdminMicroservice.Models.AdminUser;
import com.BarberShop.AdminMicroservice.Service.KeycloakService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/Auth")
@RequiredArgsConstructor
public class AuthController {

    private final PasswordEncoder passwordEncoder;
    private final KeycloakService keycloakService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AdminUserDto adminUserDto) {

        Response response = keycloakService.createUser(
                adminUserDto.getEmail(),
                adminUserDto.getPassword(),
                adminUserDto.getEmail(),
                adminUserDto.getFirstName(),
                adminUserDto.getLastName());

        return ResponseEntity.ok().body(response.toString());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminLoginDto adminUserDto) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", "barbershop-api");
        body.add("client_secret", "7Hm8kLf4644clIcnGSrBOFnWh8HTZDIa");
        body.add("grant_type", "client_credentials");
        body.add("username", adminUserDto.getEmail());
        body.add("password", adminUserDto.getPassword());
        body.add("scope", "email");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        RestTemplate template = new RestTemplate();

        ResponseEntity<Map> response = template.postForEntity("http://localhost:7080/realms/BarberShopRealm/protocol/openid-connect/token", request, Map.class);

        if(response.getStatusCode().value()==200)
            return ResponseEntity.ok().body(response.getBody().toString());
        return ResponseEntity.badRequest().body(response.getBody().toString());
    }
}
