package com.proyecto3evaluacion.respawnix.model;

public class Tarjeta {

    private int id;
    private String nombre;
    private String email;


    public Tarjeta(int id, String nombre, String email) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;

    }
    public Tarjeta(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
