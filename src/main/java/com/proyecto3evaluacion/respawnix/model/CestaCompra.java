package com.proyecto3evaluacion.respawnix.model;

import com.proyecto3evaluacion.respawnix.DAO.CestaCompraDAO;
import com.proyecto3evaluacion.respawnix.Interfaces.CestaCompraInterfaz;
import com.proyecto3evaluacion.respawnix.controller.UsuarioActualController;

import javax.xml.bind.Marshaller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CestaCompra implements CestaCompraInterfaz<VideoJuego> {


    private static CestaCompra instancia;
    private  Usuario usuario;
    private  Map<String, Integer> videoJuego;

    private CestaCompra() {
        usuario = UsuarioActualController.getInstance().getUsuario();
        videoJuego = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
    }


    public static CestaCompra getInstance() {
        if (instancia == null) {
            instancia = new CestaCompra();
        }
        return instancia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void annadir(VideoJuego juego, int cantidad) {
        videoJuego.put(juego.getNombre(),cantidad);
        CestaCompraDAO.actualizarEnLaLista(UsuarioActualController.getInstance().getUsuario().getEmail(),juego.getNombre(), cantidad);

    }

    public void eliminar(VideoJuego juego, int resta){
            videoJuego.remove(juego.getNombre());
            CestaCompraDAO.eliminarCestaOActualizar(UsuarioActualController.getInstance().getUsuario().getEmail(), juego.getNombre(), resta);

    }

    public void eliminarDescuento(VideoJuego videoJuego, String email){
            CestaCompraDAO.eliminarCesta(email,videoJuego.getNombre());
    }

    public Map<String, Integer> todaCesta(){
        videoJuego = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
        return videoJuego;
    }
    public void actualizar(VideoJuego videoJuego){
        CestaCompraDAO.actualizarNombre(videoJuego.getNombre());

    }



}
