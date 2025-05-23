package com.proyecto3evaluacion.respawnix.model;

public class TarjetaPremium extends Tarjeta{


    private int porcentaje = 5;

    public TarjetaPremium(int id, String nombre) {
        super(id, nombre);

    }

    public TarjetaPremium() {
    }


    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
