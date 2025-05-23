package com.proyecto3evaluacion.respawnix.model;

import com.proyecto3evaluacion.respawnix.controller.UsuarioActualController;

public class VideoJuegoComprado {

    private Usuario usuario;
    private VideoJuego videoJuego = new VideoJuego();
    private double precio;
    private double precioTotal;
    private int cantidad;


    public VideoJuegoComprado() {
        usuario = UsuarioActualController.getInstance().getUsuario();
    }

    public String getEmail() {
        return usuario.getEmail();
    }

    public void setEmail(String email) {
        this.usuario.setEmail(email);
    }

    public VideoJuego getVideojuego() {
        return videoJuego;
    }

    public void setJuego(VideoJuego juego) {
        videoJuego = juego;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String toString() {
        return this.videoJuego.getNombre() + " " + this.precio+ " cantidad "+this.cantidad;
    }
}
