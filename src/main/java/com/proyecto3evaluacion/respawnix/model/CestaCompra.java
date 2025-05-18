package com.proyecto3evaluacion.respawnix.model;

import com.proyecto3evaluacion.respawnix.Interfaces.CestaCompraInterfaz;

import java.util.ArrayList;
import java.util.List;

public class CestaCompra implements CestaCompraInterfaz<String> {


    private static CestaCompra instancia;
    private static String emailUsuario;
    private static List<String> videoJuego = new ArrayList<>();


    private CestaCompra() {
    }


    public static CestaCompra getInstance() {
        if (instancia == null) {
            instancia = new CestaCompra();
        }
        return instancia;
    }

    public static String getEmailUsuario() {
        return emailUsuario;
    }

    public static void setEmailUsuario(String emailUsuario) {
        CestaCompra.emailUsuario = emailUsuario;
    }

    public static List<String> getVideoJuego() {
        return videoJuego;
    }

    public static void setVideoJuego(List<String> videoJuego) {
        CestaCompra.videoJuego = videoJuego;
    }

    public void annadir(String videoJuegoName) {
        videoJuego.add(videoJuegoName);
    }

    public void eliminar(String videoJuegoName){
            videoJuego.remove(videoJuegoName);

    }

}
