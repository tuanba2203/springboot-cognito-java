package com.example.demo.service;

import com.example.demo.model.CognitoJWT;
import com.example.demo.model.TokenClaims;
import com.example.demo.Functions;
import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.util.Date;

/**
 * Service for authentication
 */
@Component
public class AuthService {
    @Value("${endpoints.token}")
    private String tokenUrl;

    @Value("${cognito.client}")
    private String clientId;

    @Value("${cognito.secret}")
    private String clientSecret;

    @Value("${cognito.callback}")
    private String callbackUrl;

    /**
     * Get token claims from security context
     */
    public TokenClaims getClaims() throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTClaimsSet details = (JWTClaimsSet)authentication.getDetails();

        return new TokenClaims(details.getStringClaim("sub"),
                (Long)details.getClaim("auth_time"),
                (Date)details.getClaim("iat"),
                (Date)details.getClaim("exp"),
                details.getStringClaim("name"),
                details.getStringClaim("cognito:username"),
                details.getStringClaim("email")
        );
    }

    /**
     * Get token with authorization code
     */
    public CognitoJWT getToken(String code){
        RestTemplate client = new RestTemplate();

        LinkedMultiValueMap headers = new LinkedMultiValueMap<String, String>();
        String auth = Functions.toBase64("" + this.clientId + ':' + this.clientSecret);
        headers.add("HeaderName", "value");
        headers.add("Authorization", "Basic " + auth);
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        HttpEntity req = new HttpEntity(null, headers);

        String url = "" + this.tokenUrl + "?grant_type=authorization_code&client_id=" +
                this.clientId + "&code=" + code + "&redirect_uri=" + this.callbackUrl;

        return client.postForObject(url, req, CognitoJWT.class);
//        return client.postForObject(url, req, CognitoJWT.class, new Object[0]);
    }
}
