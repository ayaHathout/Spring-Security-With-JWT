package com.ayahathout.spring_security_with_jwt.configurations;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    private String AUTHORIZATION_JWT_HEADER, jwtToken, usernameFromToken;

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
        jwtToken = AUTHORIZATION_JWT_HEADER.substring(7);

        // 2) Extract the username | userEmail (whatever I put in the sub in JWT) from JWTService
        usernameFromToken = jwtService.getUsernameFromToken(jwtToken);

        // Check whether the username != null & It's not authenticated as if it's authenticated, we do not need to do the whole checks
        if (usernameFromToken != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(usernameFromToken);

            if (jwtService.isValidToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
