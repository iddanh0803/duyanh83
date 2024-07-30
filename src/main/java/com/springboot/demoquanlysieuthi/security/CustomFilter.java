package com.springboot.demoquanlysieuthi.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@AllArgsConstructor
public class CustomFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final CustomEmployeeDetailService customEmployeeDetailService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        // Get header from request
        String authHeader = request.getHeader("Authorization");
        log.info("authHeader : {}", authHeader);

        // Check header has Bearer token or not
        if (authHeader == null || !authHeader.contains("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get jwt token from header
        String jwtToken = authHeader.substring(7);
        log.info("jwtToken : {}", jwtToken);

        // Get email from token
        String userEmail = jwtUtils.extractUsername(jwtToken);
        log.info("userEmail : {}", userEmail);

        // Check email is not null and security context has not been set
        if (userEmail != null  && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customEmployeeDetailService.loadUserByUsername(userEmail);
            log.info("userDetails : {}", userDetails);
            log.info("userDetails.getAuthorities() : {}", userDetails.getAuthorities());
            log.info("userDetails.getUsername() : {}", userDetails.getUsername());

            if (jwtUtils.isTokenValid(jwtToken,userDetails)){
                log.info("jwt token is valid");
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
