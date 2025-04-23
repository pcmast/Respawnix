package com.proyecto3evaluacion.respawnix.model;

import javafx.scene.image.Image;

public class VideoJuego {
    private String nombre;
    private String descripcion;
    private String genero;
    private String plataforma;
    private double precio;
    private Image image;

    public VideoJuego(String nombre, String descripcion, String genero, String plataforma, double precio, Image image) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.genero = genero;
        this.plataforma = plataforma;
        this.precio = precio;
        this.image = image;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }


}
