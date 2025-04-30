package com.proyecto3evaluacion.respawnix.model;

public class VideoJuegoComprado {

    private String email;
    private String nombreJuego;

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
}
