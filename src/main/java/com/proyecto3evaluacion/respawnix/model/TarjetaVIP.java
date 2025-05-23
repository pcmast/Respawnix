package com.proyecto3evaluacion.respawnix.model;

public class TarjetaVIP extends Tarjeta{



    private int porcentaje = 10;

    public TarjetaVIP(int id, String nombre) {
        super(id, nombre);
    }

    public TarjetaVIP() {
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}