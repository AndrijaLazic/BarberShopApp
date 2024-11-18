package com.BarberShopApp.GatewayServer.Filters;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import org.springframework.http.HttpHeaders;
import java.util.List;

@Component
public class FilterUtility {

    public static final String CORRELATION_ID = "correlation-id";

    public String getCorrelationId(HttpHeaders requestHeaders) {
        if (requestHeaders.get(CORRELATION_ID) != null) {
            List<String> requestHeaderList = requestHeaders.get(CORRELATION_ID);
            return requestHeaderList.stream().findFirst().get();
        } else {
            return null;
        }
    }

    public ServerWebExchange setRequestHeader(ServerWebExchange exchange, String name, String value) {
        //This change is needed to avoid concurrent readonly headers
        HttpHeaders mutableHeaders = new HttpHeaders();
        mutableHeaders.putAll(exchange.getRequest().getHeaders());

        mutableHeaders.set(name, value);

        return exchange.mutate()
                .request(exchange.getRequest().mutate()
                        .headers(httpHeaders -> httpHeaders.putAll(mutableHeaders))
                        .build())
                .build();
    }

    public ServerWebExchange setCorrelationId(ServerWebExchange exchange, String correlationId) {
        return this.setRequestHeader(exchange, CORRELATION_ID, correlationId);
    }

}