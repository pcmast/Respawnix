package com.proyecto3evaluacion.respawnix.model;

public class TarjetaPremium extends Tarjeta{

    private String email;
    private int porcentaje = 5;

    public TarjetaPremium(int id, String nombre, String email) {
        super(id, nombre, email);
    }

    public TarjetaPremium() {
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
