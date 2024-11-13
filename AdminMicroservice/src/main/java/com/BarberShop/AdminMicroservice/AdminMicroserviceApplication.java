package com.BarberShop.AdminMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableWebSecurity(debug = true)
public class AdminMicroserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(AdminMicroserviceApplication.class, args);
	}
}
