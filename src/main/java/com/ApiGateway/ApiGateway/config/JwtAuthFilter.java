package com.ApiGateway.ApiGateway.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        logger.info("Starting doFilterInternal method");

        try {
            String authHeader = request.getHeader("Authorization");
            logger.info("Authorization header: {}", authHeader);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                logger.info("JWT Token: {}", token);

                String username = jwtUtils.extractUsername(token);
                logger.info("Extracted username: {}", username);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    logger.info("Loaded user details for username: {}", username);

                    if (jwtUtils.validateToken(token, userDetails)) {
                        logger.info("Token is valid for username: {}", username);

                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);

                        logger.info("Authentication token set for username: {}", username);
                    } else {
                        logger.warn("Token validation failed for username: {}", username);
                    }
                } else {
                    logger.warn("Username is null or authentication is already set for username: {}", username);
                }
            } else {
                logger.warn("Authorization header is missing or does not start with 'Bearer '");
            }
        } catch (Exception e) {
            logger.error("Error processing JWT token", e);
        }

        logger.info("Continuing filter chain");
        filterChain.doFilter(request, response);
    }
}
