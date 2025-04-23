package com.proyecto3evaluacion.respawnix.model;

import java.util.ArrayList;

public class CestaCompra {

    private ArrayList<VideoJuego> list = new ArrayList<>();
    private static CestaCompra instancia;

    private CestaCompra() {};

    public static CestaCompra getInstance(){
        if (instancia == null){
         instancia = new CestaCompra();
        }
        return instancia;
    }

    public boolean agregarALaCesta(VideoJuego videoJuego){
        boolean annadido = false;
        if (!list.contains(videoJuego)){
            list.add(videoJuego);
            annadido = true;
        }
        return annadido;
    }
    public boolean eliminarDeLaCesta(VideoJuego videoJuego){
        boolean eliminado = false;
        if (list.contains(videoJuego)){
            list.remove(videoJuego);
            eliminado = true;
        }
        return eliminado;
    }


}
