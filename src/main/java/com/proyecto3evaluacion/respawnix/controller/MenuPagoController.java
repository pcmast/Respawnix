package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.*;
import com.proyecto3evaluacion.respawnix.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class MenuPagoController {

    @FXML
    private TextField numeroTarjeta;
    @FXML
    private TextField fechaCaducidad;
    @FXML
    private TextField codigoSeguridad;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField direccion;
    @FXML
    private TextField localidad;
    @FXML
    private TextField codigoPostal;
    @FXML
    private TextField pais;
    @FXML
    private TextField telefono;
    private boolean vipBoolean = false;
    private boolean premium = false;
    private boolean bono = false;





    /**
     * Metodo que coge toda la cesta de la compra del usuario actual y calcula el precio del descuento si se a querido aplicar
     * y añade cuando se compre los juegos comprados a la base de datos
     * @param actionEvent cuando el usuario le de a comprar
     */
    public void comprar(ActionEvent actionEvent) {
        Map<String, Integer> list = UsuarioActualController.getInstance().getUsuario().getCesta().todaCesta();
        List<VideoJuego> juegos = VideoJuegoDAO.todosLosJuegos();
        double precio = 0;
        double precioTotal = 0;
        double porcentaje = 0;

        if (premium) {
            porcentaje = 5;
        }
        if (vipBoolean) {
            porcentaje = 10;
        }

        for (Map.Entry<String, Integer> entry : list.entrySet()) {
            String nombreJuego = entry.getKey();
            int cantidad = entry.getValue();
            for (VideoJuego videoJuego : juegos) {
                if (videoJuego.getNombre().equals(nombreJuego)) {
                    if (porcentaje != 0) {
                        precio = videoJuego.getPrecio() - (videoJuego.getPrecio() * porcentaje / 100);
                    } else {
                        precio = videoJuego.getPrecio();
                    }
                    precioTotal = precio * cantidad;
                    UsuarioActualController.getInstance().getUsuario().getCesta().eliminarDescuento(videoJuego,UsuarioActualController.getInstance().getUsuario().getEmail());
                }
            }

            VideoJuegoComprado videoJuegoComprado = new VideoJuegoComprado();
            videoJuegoComprado.setEmail(UsuarioActualController.getInstance().getUsuario().getEmail());
            videoJuegoComprado.setNombreJuego(nombreJuego);
            videoJuegoComprado.setCantidad(cantidad);
            videoJuegoComprado.setPrecioTotal(precioTotal);
            videoJuegoComprado.setPrecio(precio);
            UsuarioActualController.getInstance().getUsuario().annadir(videoJuegoComprado);
        }

        List<Tarjeta> listaTarjetas = TarjetaDAO.todas();

        if (premium) {
            premiumUsado();
        }
        if (vipBoolean) {
            vipUsado();
        }

        if (bono) {
            for (Tarjeta tarjeta : listaTarjetas) {
                if (tarjeta.getUsuario().getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())) {
                    TarjetaDAO.tarjetaUsada(UsuarioActualController.getInstance().getUsuario().getEmail());
                }
            }

        }

        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    /**
     * La tarjeta si es usada se elimina de la base de datos el email del usuario indicando que ya a usado la tarjeta
     */
    public void premiumUsado() {
        List<TarjetaPremium> list = TarjetaPremiumDAO.todasTarjetas();
        for (TarjetaPremium tarjetaPremium : list) {
            if (tarjetaPremium.getUsuario().getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())) {
                TarjetaPremiumDAO.tarjetaUsada(UsuarioActualController.getInstance().getUsuario().getEmail());
            }
        }
    }

    /**
     * La tarjeta si es usada se elimina de la base de datos el email del usuario indicando que ya a usado la tarjeta
     */
    public void vipUsado() {
        List<TarjetaVIP> list = TarjetaVIPDAO.todasTarjetas();
        for (TarjetaVIP tarjetaVIP : list) {
            if (tarjetaVIP.getUsuario().getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())) {
                TarjetaVIPDAO.tarjetaUsada(UsuarioActualController.getInstance().getUsuario().getEmail());
            }
        }
    }

    /**
     * Metodo que muestra una alerta con informacion del programa (metodo en todas las pantallas)
     * @param actionEvent el usuario clickea el boton
     */
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }

    /**
     * Si el usuario decide usar el bono pone a true el booleano dependiendo de que tarjeta compro o VIP o PREMIUM
     * @param actionEvent
     */
    public void usarBONO(ActionEvent actionEvent) {
        bono = true;
        List<TarjetaPremium> premiums = TarjetaPremiumDAO.todasTarjetas();
        List<TarjetaVIP> vip = TarjetaVIPDAO.todasTarjetas();

        for (TarjetaPremium tarjetaPremium : premiums) {
            if (UsuarioActualController.getInstance().getUsuario().getEmail().equals(tarjetaPremium.getUsuario().getEmail())) {
                premium = true;
            }
        }

        for (TarjetaVIP tarjetaVIP : vip) {
            if (UsuarioActualController.getInstance().getUsuario().getEmail().equals(tarjetaVIP.getUsuario().getEmail())) {
                vipBoolean = true;
            }

        }

    }
}
