package com.example.autenticacion.Modelo.Token;

import java.io.Serializable;

public class ModeloDatosSesion implements Serializable {
    private String token, token_Refresh, email;

    public ModeloDatosSesion(String token, String token_Refresh, String email) {
        this.token = token;
        this.token_Refresh = token_Refresh;
        this.email = email;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken_Refresh() {
        return token_Refresh;
    }

    public void setToken_Refresh(String token_Refresh) {
        this.token_Refresh = token_Refresh;
    }
}
