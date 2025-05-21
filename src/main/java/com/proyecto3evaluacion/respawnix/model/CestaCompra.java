package com.proyecto3evaluacion.respawnix.model;

import com.proyecto3evaluacion.respawnix.DAO.CestaCompraDAO;
import com.proyecto3evaluacion.respawnix.Interfaces.CestaCompraInterfaz;
import com.proyecto3evaluacion.respawnix.controller.UsuarioActualController;

import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CestaCompra implements CestaCompraInterfaz<String> {


    private static CestaCompra instancia;
    private  String emailUsuario;
    private  Map<String, Integer> videoJuego = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());

    private CestaCompra() {
    }


    public static CestaCompra getInstance() {
        if (instancia == null) {
            instancia = new CestaCompra();
        }
        return instancia;
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public Map<String, Integer> getVideoJuego() {
        videoJuego = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
        return videoJuego;
    }



    public void annadir(String videoJuegoName, int cantidad) {
        videoJuego.put(videoJuegoName,cantidad);
        CestaCompraDAO.actualizarEnLaLista(UsuarioActualController.getInstance().getUsuario().getEmail(), videoJuegoName, cantidad);

    }

    public void eliminar(String videoJuegoName, int resta){
            videoJuego.remove(videoJuegoName);
            CestaCompraDAO.eliminarCestaOActualizar(UsuarioActualController.getInstance().getUsuario().getEmail(), videoJuegoName, resta);

    }

    public void eliminarDescuento(String email, String nombreJuego){
            CestaCompraDAO.eliminarCesta(email,nombreJuego);
    }

    public Map<String, Integer> todaCesta(){
        videoJuego = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
        return videoJuego;
    }
    public void actualizar(String nombreAntiguo, String nombre){
        CestaCompraDAO.actualizarNombre(nombreAntiguo, nombre);

    }



}
