package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.CestaCompraDAO;
import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoCompradoDAO;
import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class MenuPagoController {



    public void comprar(ActionEvent actionEvent) {
        Map<String, Integer> list = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
        List<VideoJuego> juegos = VideoJuegoDAO.todosLosJuegos();
        double precio = 0;
        double precioTotal = 0;
        for (Map.Entry<String, Integer> entry : list.entrySet()) {
            String nombreJuego = entry.getKey();
            int cantidad = entry.getValue();
            for (VideoJuego videoJuego: juegos){
                if (videoJuego.getNombre().equals(nombreJuego)){
                    precio = videoJuego.getPrecio();
                    precioTotal = videoJuego.getPrecio() * cantidad;
                    CestaCompraDAO.eliminarCesta(UsuarioActualController.getInstance().getUsuario().getEmail(),nombreJuego);
                }
            }

            VideoJuegoCompradoDAO.insertarJuegoComprado(UsuarioActualController.getInstance().getUsuario().getEmail(),nombreJuego,precio,precioTotal,cantidad);
        }

        ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
    }

    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }

}
