package com.ayahathout.spring_security_with_jwt.configurations;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private String AUTHORIZATION_JWT_HEADER;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1) Check whether the JWT token is found or missing
        AUTHORIZATION_JWT_HEADER = request.getHeader("Authorization");
        if (AUTHORIZATION_JWT_HEADER == null || !AUTHORIZATION_JWT_HEADER.startsWith("Bearer ")) {
            // Error 403 => Missing JWT token
            // Go to the next filter in the filter chain
            filterChain.doFilter(request, response);
            return;
        }

        // JWT is found
        // 2) Extract the username | userEmail (whatever I put in the sub in JWT) from JWTService

    }
}
