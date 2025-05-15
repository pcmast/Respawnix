package com.proyecto3evaluacion.respawnix.model;

public class VideoJuegoComprado {

    private String email;
    private String nombreJuego;
    private double precio;
    private int cantidad;


    public VideoJuegoComprado(String email, String nombreJuego) {
        this.email = email;
        this.nombreJuego = nombreJuego;
    }

    public VideoJuegoComprado() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreJuego() {
        return nombreJuego;
    }

    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String toString() {
        return this.nombreJuego + " " + this.precio+ " cantidad "+this.cantidad;
    }
}
