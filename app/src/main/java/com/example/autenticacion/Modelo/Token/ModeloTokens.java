package com.example.autenticacion.Modelo.Token;

import java.io.Serializable;

public class ModeloTokens implements Serializable {
    private String token, token_Refresh;

    public ModeloTokens(String token, String token_Refresh) {
        this.token = token;
        this.token_Refresh = token_Refresh;
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
