package com.BarberShop.AdminMicroservice.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        http.csrf(csrfConfig -> csrfConfig.disable())
        .authorizeHttpRequests((request)->{
            request.requestMatchers("/Test/**").authenticated()
                    .requestMatchers("/**").permitAll();
        });

        http.oauth2ResourceServer(rsc->rsc.jwt(jwtConfigurer -> {
            jwtConfigurer.jwtAuthenticationConverter(jwtAuthenticationConverter);
        }));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        int saltLength = 16;      // Salt length in bytes
        int hashLength = 64;      // Hash length in bytes
        int parallelism = 1;      // How many threads to use
        int memory = 4096;        // Memory usage in KiB (e.g., 4096 = 4MB)
        int iterations = 3;       // Number of iterations

        return new Argon2PasswordEncoder(saltLength, hashLength, parallelism, memory, iterations);
    }
}
