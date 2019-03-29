package com.example.demo.model;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * AWS identity token for security context
 *
 * @property token raw token
 */
public class CognitoAuthenticationToken extends AbstractAuthenticationToken {
    private String token;
    List<GrantedAuthority> authorities;

    public Object getCredentials() {
        return this.token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getPrincipal() {
        Object var10000 = this.getDetails();
        return var10000;
    }

    public CognitoAuthenticationToken(String token, JWTClaimsSet details, List authorities) {
        super(authorities);
        this.token = token;
        this.setDetails(details);
        this.setAuthenticated(true);
    }

}