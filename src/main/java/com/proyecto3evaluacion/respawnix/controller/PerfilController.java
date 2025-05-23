package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.TarjetaDAO;
import com.proyecto3evaluacion.respawnix.DAO.TarjetaPremiumDAO;
import com.proyecto3evaluacion.respawnix.model.Tarjeta;
import com.proyecto3evaluacion.respawnix.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.util.List;

public class PerfilController {


    @FXML
    private ImageView imagenVIP;
    @FXML
    private Label nombre;
    @FXML
    private Label apellidos;
    @FXML
    private Label fechaNacimiento;
    @FXML
    private Label email;

    /**
     * Metodo que al inciar el controlador muestra la informacion del usuario actual del sistema y añade una foto dependiendo
     * si a comprado una tarjeta pone una foto VIP o no
     */
    public void initialize() {
        boolean VIP = false;
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
        Usuario usuario = usuarioActualController.getUsuario();
        nombre.setText(usuario.getNombre());
        apellidos.setText(usuario.getApellidos());
        fechaNacimiento.setText(String.valueOf(usuario.getFechaNacimiento()));
        email.setText(usuario.getEmail());
        List<Tarjeta> list = TarjetaDAO.todas();
        for (Tarjeta tarjeta : list) {

            if (tarjeta.getUsuario().getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())) {
                File imagenURL = new File("images/COMPRAVIP.png");
                Image image = new Image(imagenURL.toURI().toString());
                imagenVIP.setImage(image);
                VIP = true;
            }
        }
        if (!VIP) {
            File imagenURL = new File("images/usuario.png");
            Image image = new Image(imagenURL.toURI().toString());
            imagenVIP.setImage(image);
        }

    }

    /**
     * Metodo que muestra una alerta con informacion del programa (metodo en todas las pantallas)
     *
     * @param actionEvent el usuario clickea el boton
     */
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }


}