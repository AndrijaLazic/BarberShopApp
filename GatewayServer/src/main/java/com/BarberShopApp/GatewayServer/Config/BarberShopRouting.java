package com.BarberShopApp.GatewayServer.Config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BarberShopRouting {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/user/**")
                        .filters(f -> f.rewritePath("/user/(?<segment>.*)","/${segment}"))
                        .uri("lb://USERAPP"))
                .route(r -> r.path("/admin/**")
                        .filters(f -> f.rewritePath("/admin/(?<segment>.*)","/${segment}"))
                        .uri("lb://ADMINMICROSERVICE"))
                .build();
    }
}
