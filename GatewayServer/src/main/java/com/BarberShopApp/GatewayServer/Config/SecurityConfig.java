package com.BarberShopApp.GatewayServer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Bean
    SecurityWebFilterChain defaultSecurityFilterChain(ServerHttpSecurity http){
        http.authorizeExchange(exchange->{
            exchange.pathMatchers("/user/**").authenticated();
            exchange.pathMatchers("/user/actuator/**").permitAll();
            exchange.pathMatchers("/user/swagger-ui.html").permitAll();
            exchange.pathMatchers("/user/v3/api-docs/**").permitAll();
            exchange.pathMatchers("/admin/Auth/login").permitAll();
            exchange.pathMatchers("/admin/actuator/**").permitAll();
            exchange.pathMatchers("/admin/swagger-ui.html").permitAll();
            exchange.pathMatchers("/admin/v3/api-docs/**").permitAll();
            exchange.pathMatchers("/admin/**").authenticated();
            exchange.pathMatchers("/**").permitAll();
        }).oauth2ResourceServer(rsc->rsc.jwt(Customizer.withDefaults()));

        http.csrf(csrfSpec -> csrfSpec.disable());
        return http.build();
    }
}
