package com.example.autenticacion.Modelo.Evento;

public class ModeloRespuestaEvento {

    private String env;
    private ModeloEvento event;
    private Boolean success;

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public ModeloEvento getEvent() {
        return event;
    }

    public void setEvent(ModeloEvento event) {
        this.event = event;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
