package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.*;
import com.proyecto3evaluacion.respawnix.model.Tarjeta;
import com.proyecto3evaluacion.respawnix.model.TarjetaPremium;
import com.proyecto3evaluacion.respawnix.model.TarjetaVIP;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class MenuPagoController {

    private boolean vipBoolean = false;
    private boolean premium = false;
    private boolean bono = false;

    public void comprar(ActionEvent actionEvent) {
        Map<String, Integer> list = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
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
                    CestaCompraDAO.eliminarCesta(UsuarioActualController.getInstance().getUsuario().getEmail(), nombreJuego);
                }
            }

            VideoJuegoCompradoDAO.insertarJuegoComprado(UsuarioActualController.getInstance().getUsuario().getEmail(), nombreJuego, precio, precioTotal, cantidad);
        }

        List<Tarjeta> listaTarjetas = TarjetaDAO.todas();

        if (bono) {
            for (Tarjeta tarjeta : listaTarjetas) {
                if (tarjeta.getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())) {
                    TarjetaDAO.tarjetaUsada(UsuarioActualController.getInstance().getUsuario().getEmail());
                }
            }
            if (premium) {
                premiumUsado();
            }
            if (vipBoolean) {
                vipUsado();
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
            if (tarjetaPremium.getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())) {
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
            if (tarjetaVIP.getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())) {
                TarjetaVIPDAO.tarjetaUsada(UsuarioActualController.getInstance().getUsuario().getEmail());
            }
        }
    }

    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }


    public void usarBONO(ActionEvent actionEvent) {
        bono = true;
        List<TarjetaPremium> premiums = TarjetaPremiumDAO.todasTarjetas();
        List<TarjetaVIP> vip = TarjetaVIPDAO.todasTarjetas();

        for (TarjetaPremium tarjetaPremium : premiums) {
            if (UsuarioActualController.getInstance().getUsuario().getEmail().equals(tarjetaPremium.getEmail())) {
                premium = true;
            }
        }

        for (TarjetaVIP tarjetaVIP : vip) {
            if (UsuarioActualController.getInstance().getUsuario().getEmail().equals(tarjetaVIP.getEmail())) {
                vipBoolean = true;
            }

        }

    }
}
