package com.springboot.demoquanlysieuthi.service;

import com.springboot.demoquanlysieuthi.model.request.LoginRequest;
import com.springboot.demoquanlysieuthi.security.CustomEmployeeDetailService;
import com.springboot.demoquanlysieuthi.security.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final CustomEmployeeDetailService customEmployeeDetailService;
    private final JwtUtils jwtUtils;

    public AuthService(AuthenticationManager authenticationManager,CustomEmployeeDetailService customEmployeeDetailService, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.customEmployeeDetailService = customEmployeeDetailService;
        this.jwtUtils = jwtUtils;
    }

    public String login(LoginRequest loginRequest) {
        // Create authentication object
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );
        try {
            // Process authentication
            Authentication authentication = authenticationManager.authenticate(token);

            // Save authentication object to SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(token);

            // Get userDetails
            UserDetails userDetails = customEmployeeDetailService.loadUserByUsername(authentication.getName());

            // Create Jwt token
            return jwtUtils.generateToken(userDetails);

        } catch (Exception e) {
            log.error("Error while login: ", e);
            throw new RuntimeException(e.getMessage());
        }
    }
}
