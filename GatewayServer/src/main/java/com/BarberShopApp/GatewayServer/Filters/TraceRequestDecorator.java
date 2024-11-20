package com.BarberShopApp.GatewayServer.Filters;

import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;

import java.time.LocalDate;

public class TraceRequestDecorator extends ServerWebExchangeDecorator {
    String correlationId;
    public TraceRequestDecorator(ServerWebExchange delegate, String correlationId) {
        super(delegate);
        this.correlationId = correlationId;
    }

    @Override
    public ServerHttpRequest getRequest() {
        return new ServerHttpRequestDecorator(super.getRequest()) {
            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.putAll(super.getHeaders());
                headers.add(FilterUtility.CORRELATION_ID, correlationId);
                return headers;
            }
        };
    }
}
