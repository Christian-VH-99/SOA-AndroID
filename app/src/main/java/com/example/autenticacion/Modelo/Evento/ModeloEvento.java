package com.example.autenticacion.Modelo.Evento;

public class ModeloEvento {

    private String env, type_events, description;

    public ModeloEvento(String env, String type_events, String description) {
        this.env = env;
        this.type_events = type_events;
        this.description = description;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getType_events() {
        return type_events;
    }

    public void setType_events(String type_events) {
        this.type_events = type_events;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
