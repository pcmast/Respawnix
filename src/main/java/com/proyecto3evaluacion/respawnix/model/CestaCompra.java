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

    /**
     * Metodo que añade un videojuego a la lista de la compra
     * @param juego el juego a insertar
     * @param cantidad la cantidad de juegos
     */
    public void annadir(VideoJuego juego, int cantidad) {
        videoJuego.put(juego.getNombre(),cantidad);
        CestaCompraDAO.actualizarEnLaLista(UsuarioActualController.getInstance().getUsuario().getEmail(),juego.getNombre(), cantidad);

    }

    /**
     * Metodo que elimina un videojuego de la lista de la compra
     * @param juego juego que va a eliminar
     * @param cantidad es la cantidad
     */
    public void eliminar(VideoJuego juego, int cantidad){
            videoJuego.remove(juego.getNombre());
            CestaCompraDAO.eliminarCestaOActualizar(UsuarioActualController.getInstance().getUsuario().getEmail(), juego.getNombre(), cantidad);

    }

    /**
     * Metodo que elimina un videojuego de la cesta con su descuento
     * @param videoJuego videojuego a eliminar
     * @param email email del usuario del pedido
     */
    public void eliminarDescuento(VideoJuego videoJuego, String email){
            CestaCompraDAO.eliminarCesta(email,videoJuego.getNombre());
    }

    /**
     * Metodo que devuelve la cesta de la compra por el usuario actual
     * @return devuelve un map con el nombre del juego y la cantidad
     */
    public Map<String, Integer> todaCesta(){
        //videoJuego = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
        return videoJuego;
    }

    /**
     * Metodo que actualiza un videojuego en la base de datos
     * @param videoJuego videojuego a actualizar
     */
    public void actualizar(VideoJuego videoJuego){
        CestaCompraDAO.actualizarNombre(videoJuego.getNombre());

    }



}
