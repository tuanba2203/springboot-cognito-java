package com.example.demo.filter;

import com.example.demo.model.CognitoAuthenticationToken;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * Filter that handles jwt authentication.
 *
 * @property processor Processor for AWS ID token
 */
public final class AuthFilter extends BasicAuthenticationFilter {
    private ConfigurableJWTProcessor processor;

    public AuthFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
    }

    public AuthFilter(ConfigurableJWTProcessor processor, AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.processor = processor;
    }

    public final ConfigurableJWTProcessor getProcessor() {
        return this.processor;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException {
        try {
            String token = extractToken(req.getHeader("Authorization"));
            Objects.requireNonNull(token);
            CognitoAuthenticationToken authentication = this.extractAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(req, res);
        } catch (AccessDeniedException e) {
            LoggerFactory.getLogger(this.getClass()).error("Access denied");
            res.setStatus(401);
            res.getWriter().write("Access denied");
        } catch (ServletException e) {
            LoggerFactory.getLogger(this.getClass()).error("ServletException");
            res.setStatus(500);
            res.getWriter().write("ServletException");
        }
    }

    /**
     * Extract token from header
     */
    private String extractToken(String header) {
        Objects.requireNonNull(header);
        String[] headers = header.split("Bearer ");

        if (headers == null || headers.length < 2) {
            return null;
        } else {
            return headers[1];
        }
    }

    /**
     * Extract authentication details from token
     */
    private final CognitoAuthenticationToken extractAuthentication(String token) throws AccessDeniedException {
        if (token == null) {
            return null;
        }

        try {
            JWTClaimsSet claims = processor.process(token, null);
            return new CognitoAuthenticationToken(token, claims, null);
        } catch (Exception ex) {
            throw new AccessDeniedException(ex.getMessage());

        }
    }
}
