package com.BarberShop.AdminMicroservice.Filters;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@Slf4j
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication){
            log.info("CustomFilter: {}", authentication.getName());
        }else{
            log.info("CustomFilter: User is not authenticated");
        }
        chain.doFilter(request, response);
    }
}
