package com.example.autenticacion.Modelo;

public class ModeloUsuario {

    private String env, name, lastname, email,password;
    private Integer dni, commission, group;


    public ModeloUsuario(String env, String name, String lastname, String email, String password, Integer dni, Integer commission, Integer group) {
        this.env = env;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.dni = dni;
        this.commission = commission;
        this.group = group;
    }

    public ModeloUsuario(String email, String password){
        this.env = "TEST";
        this.email = email;
        this.password = password;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public Integer getDni() {
        return dni;
    }

    public void setDni(Integer dni) {
        this.dni = dni;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getGroup() {
        return group;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public String datosCorrectos (/*String mensaje*/){
        if(this.name.isEmpty() || this.lastname.isEmpty() || this.dni.equals("") || this.group.equals("")){
            return "Complete los campos solicitados";
            //return;
        }
        if(!this.env.equals("TEST")  && !this.env.equals("PROD")){
           return  "El ambiente debe ser TEST o PROD";
            //return;
        }
       if(this.password.length()<8){
           return  "La contraseña debe tener al menos 8 caracteres";
           //return;
       }
       if(this.commission != 2900 && this.commission != 3900) {
           return  "La comision debe ser 2900(Martes) o 3900(Miercoles)";
           //return;
       }
       return "Datos Correctos";
       //return;
    }
}
