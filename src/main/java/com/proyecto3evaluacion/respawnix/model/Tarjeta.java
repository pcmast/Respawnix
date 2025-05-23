package com.proyecto3evaluacion.respawnix.model;

import com.proyecto3evaluacion.respawnix.controller.UsuarioActualController;

public class Tarjeta {

    private int id;
    private String nombre;
    private Usuario usuario;


    public Tarjeta(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        usuario = UsuarioActualController.getInstance().getUsuario();
    }
    public Tarjeta(){
        usuario = UsuarioActualController.getInstance().getUsuario();
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
