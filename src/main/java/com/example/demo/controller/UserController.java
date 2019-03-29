package com.example.demo.controller;

import com.example.demo.model.TokenClaims;
import com.example.demo.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * User endpoints, require authentication
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private AuthService authService;

    @GetMapping({"/me"})
    public TokenClaims getCurrentUser() throws ParseException {
        return this.getAuthService().getClaims();
    }

    public AuthService getAuthService() {
        return this.authService;
    }
    public UserController(AuthService authService) {
        super();
        this.authService = authService;
    }
}
