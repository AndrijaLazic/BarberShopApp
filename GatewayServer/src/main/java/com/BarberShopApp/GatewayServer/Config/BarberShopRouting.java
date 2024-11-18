package com.BarberShopApp.GatewayServer.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarberShopRouting {

    @Autowired
    private RateLimitingConfig rateLimitingConfig;

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/user/**")
                        .filters(f -> f.rewritePath("/user/(?<segment>.*)","/${segment}")
//                                .circuitBreaker(config -> {
//                                    config.setName("userCircuitBreaker");
//                                    config.setFallbackUri("forward:/contactSupport");
//                                })
                        )
                        .uri("lb://USERAPP"))

                .route(r -> r.path("/admin/**")
                        .filters(f -> f.rewritePath("/admin/(?<segment>.*)","/${segment}")
//                                .circuitBreaker(config -> {
//                                    config.setName("adminCircuitBreaker");
//                                    config.setFallbackUri("forward:/contactSupport");
//                                })
                                .requestRateLimiter(rate->rate.setRateLimiter(rateLimitingConfig.oneRequestPerSecond()).setKeyResolver(rateLimitingConfig.ipKeyResolver()))
                        )
                        .uri("lb://ADMINMICROSERVICE"))
                .build();
    }
}
