package com.example.demo.model;

import java.util.Date;

/**
 * Data model for token claims
 */
public class TokenClaims {
    private String uuid;
    private Long auth_time;
    private Date issued;
    private Date expire;
    private String name;
    private String cognitoUserName;
    private String email;

    public TokenClaims() {
    }

    public TokenClaims(String uuid, Long auth_time, Date issued, Date expire, String name, String cognitoUserName, String email) {
        this.uuid = uuid;
        this.auth_time = auth_time;
        this.issued = issued;
        this.expire = expire;
        this.name = name;
        this.cognitoUserName = cognitoUserName;
        this.email = email;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getAuth_time() {
        return auth_time;
    }

    public void setAuth_time(Long auth_time) {
        this.auth_time = auth_time;
    }

    public Date getIssued() {
        return issued;
    }

    public void setIssued(Date issued) {
        this.issued = issued;
    }

    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCognitoUserName() {
        return cognitoUserName;
    }

    public void setCognitoUserName(String cognitoUserName) {
        this.cognitoUserName = cognitoUserName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}