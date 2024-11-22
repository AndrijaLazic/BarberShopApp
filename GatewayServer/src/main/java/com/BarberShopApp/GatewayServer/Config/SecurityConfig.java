package com.BarberShopApp.GatewayServer.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final String[] freeResourceUrls = {
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/swagger-resources/**",
            "/api-docs/**",
            "/aggregate/**"
    };

    @Bean
    SecurityWebFilterChain defaultSecurityFilterChain(ServerHttpSecurity http){

        http.authorizeExchange(exchange->{
            for (int i = 0; i < freeResourceUrls.length; i++) {
                exchange.pathMatchers("/user"+freeResourceUrls[i]).permitAll();
                exchange.pathMatchers("/admin"+freeResourceUrls[i]).permitAll();
            }
            exchange.pathMatchers("/user/actuator/**").permitAll();
            exchange.pathMatchers("/user/**").authenticated();
            exchange.pathMatchers("/admin/Auth/login").permitAll();
            exchange.pathMatchers("/admin/actuator/**").permitAll();
            exchange.pathMatchers("/admin/**").authenticated();
            exchange.pathMatchers("/**").permitAll();
        }).oauth2ResourceServer(rsc->rsc.jwt(Customizer.withDefaults()));

        http.csrf(csrfSpec -> csrfSpec.disable());
        return http.build();
    }

    @Bean
    CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("*"));
        corsConfig.addAllowedMethod("*");
        corsConfig.addAllowedHeader("*");
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
