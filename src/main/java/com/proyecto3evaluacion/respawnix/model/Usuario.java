package com.proyecto3evaluacion.respawnix.model;

import com.proyecto3evaluacion.respawnix.DAO.CestaCompraDAO;
import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoCompradoDAO;
import com.proyecto3evaluacion.respawnix.controller.UsuarioActualController;

import java.beans.FeatureDescriptor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Usuario {

    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String email;
    private String password;
    private ArrayList<VideoJuegoComprado> videojuegosComprados;
    private CestaCompra cesta;
    private Tarjeta tarjeta;


    public Usuario() {
        videojuegosComprados = (ArrayList<VideoJuegoComprado>) VideoJuegoCompradoDAO.todosLosJuegosPorEmail(email);

    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getNombre() {
        return nombre;

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CestaCompra getCesta() {
        cesta = CestaCompra.getInstance();
        return cesta;
    }

    public void setCesta() {
        cesta = CestaCompra.getInstance();
    }

    /**
     * Metodo que añade un juego a los juegos comprados del usuario
     * @param videoJuegoComprado videojuego que voy a añadir
     */
    public void annadir(VideoJuegoComprado videoJuegoComprado) {
        videojuegosComprados.add(videoJuegoComprado);
        VideoJuegoCompradoDAO.insertarJuegoComprado(UsuarioActualController.getInstance().getUsuario().email, videoJuegoComprado.getVideojuego().getNombre(), videoJuegoComprado.getPrecio(), videoJuegoComprado.getPrecioTotal(), videoJuegoComprado.getCantidad());

    }

    /**
     * Metodo que consigue todos los juegos de la base de datos y los devuelve
     * @return
     */
    public ArrayList<VideoJuegoComprado> todosJuegos() {
        videojuegosComprados = (ArrayList<VideoJuegoComprado>) VideoJuegoCompradoDAO.todosLosJuegosPorEmail(this.email);
        return videojuegosComprados;
    }

}
