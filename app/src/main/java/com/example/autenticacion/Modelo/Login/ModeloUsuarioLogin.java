package com.example.autenticacion.Modelo.Login;

public class ModeloUsuarioLogin {
    private String email, password;

    public ModeloUsuarioLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String datosLoginCorrectos() {
        if(this.email.isEmpty() || this.password.isEmpty())
            return "Complete los datos solicitados";
        if(this.password.length()<8)
            return "La contraseña es incorrecta";
        return "Datos Correctos";
    }
}
