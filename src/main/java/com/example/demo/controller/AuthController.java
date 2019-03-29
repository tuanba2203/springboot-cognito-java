package com.example.demo.controller;

import com.example.demo.model.CognitoJWT;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Auth endpoints
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Value("${endpoints.authorize}")
    private String authorizeUrl;
    private AuthService authService;

    public AuthService getAuthService() {
        return this.authService;
    }

    public AuthController(AuthService authService) {
        super();
        this.authService = authService;
        this.authorizeUrl = "";
    }

    @GetMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .header(HttpHeaders.LOCATION, authorizeUrl)
                .build();
    }

    @GetMapping("/token")
    public CognitoJWT token(@RequestParam("code") String code){
        return authService.getToken(code);
    }

}
