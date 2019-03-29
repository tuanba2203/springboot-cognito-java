package com.example.demo.model;

import javax.validation.constraints.NotNull;

/**
 * Data model for aws token response
 */
public class CognitoJWT {

    @NotNull
    private String id_token = "";
    @NotNull
    private String access_token = "";
    @NotNull
    private String refresh_token = "";
    private int expires_in = 0;
    @NotNull
    private String token_type = "";

    public CognitoJWT(@NotNull String id_token, @NotNull String access_token, @NotNull String refresh_token, int expires_in, @NotNull String token_type) {
        this.id_token = id_token;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.token_type = token_type;
    }

    public CognitoJWT(@NotNull String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

}