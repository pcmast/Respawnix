package com.proyecto3evaluacion.respawnix.model;

import javafx.scene.control.TextField;

public class VideoJuego {
    private String nombre;
    private String descripcion;
    private String genero;
    private String plataforma;
    private Double precio;
    private String imagen;

    public VideoJuego(String nombre, String descripcion, String genero, String plataforma, Double precio, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
        this.plataforma = plataforma;
        this.precio = precio;
        this.imagen = imagen;
    }

    public VideoJuego() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImage() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    @Override
    public String toString() {
        return this.nombre + " " + this.precio;
    }
}
