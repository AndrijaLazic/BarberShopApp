package com.BarberShop.UserApp.FeignClients;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collection;
import java.util.Map;

@Component
public class FeignClientRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // Retrieve the current HTTP request
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String jwtToken = request.getHeader("Authorization");

        if(jwtToken!=null)
            requestTemplate.header("Authorization", "Bearer " + jwtToken.substring(7));

    }
}