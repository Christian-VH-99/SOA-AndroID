package com.example.autenticacion.Modelo;

public class ModeloVacuna {

    private int id_vacuna;
    private String tipo_de_vacuna;
    private String fecha_de_vacuna;
    private String firma;
    private String mail;

    public ModeloVacuna(int id_vacuna, String tipo_de_vacuna, String fecha_de_vacuna, String firma, String mail) {
        this.id_vacuna = id_vacuna;
        this.tipo_de_vacuna = tipo_de_vacuna;
        this.fecha_de_vacuna = fecha_de_vacuna;
        this.firma = firma;
        this.mail = mail;
    }

    public ModeloVacuna() {
        this.id_vacuna = -1;
        this.tipo_de_vacuna = "";
        this.fecha_de_vacuna = "";
        this.firma = "";
        this.mail = "";
    }

    public int getId_vacuna() {
        return id_vacuna;
    }

    public void setId_vacuna(int id_vacuna) {
        this.id_vacuna = id_vacuna;
    }

    public String getTipo_de_vacuna() {
        return tipo_de_vacuna;
    }

    public void setTipo_de_vacuna(String tipo_de_vacuna) {
        this.tipo_de_vacuna = tipo_de_vacuna;
    }

    public String getFecha_de_vacuna() {
        return fecha_de_vacuna;
    }

    public void setFecha_de_vacuna(String fecha_de_vacuna) {
        this.fecha_de_vacuna = fecha_de_vacuna;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String datosCorrectos(/*String mensaje*/) {

        if(this.tipo_de_vacuna.isEmpty() || this.fecha_de_vacuna.isEmpty() || this.firma.isEmpty()){
            return "Complete los campos solicitados";
            //return;
        }

        return "Datos Correctos";
        //return;
    }
}
