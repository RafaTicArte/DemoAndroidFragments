package com.ticarte.rafa.demoandroidfragments;

public class EventoItem {
    private String nombre;
    private String id;

    public EventoItem(String id, String nombre){
        this.nombre = nombre;
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
