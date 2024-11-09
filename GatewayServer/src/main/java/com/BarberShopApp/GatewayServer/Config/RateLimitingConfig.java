package com.BarberShopApp.GatewayServer.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class RateLimitingConfig {

    KeyResolver ipKeyResolver() {
        return exchange -> {
            String ip = Optional.ofNullable(exchange.getRequest().getRemoteAddress())
                    .map(address -> address.getAddress().getHostAddress())
                    .orElse("unknown");
            return Mono.just(ip);
        };
    }


    public RedisRateLimiter oneRequestPerSecond() {
        return new RedisRateLimiter(1,1,1);
    }

}