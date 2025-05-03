package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class PerfilController {



    @FXML
    private Label nombre;
    @FXML
    private Label apellidos;
    @FXML
    private Label fechaNacimiento;
    @FXML
    private Label email;


    public void initialize(){
    UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
        Usuario usuario = usuarioActualController.getUsuario();
        nombre.setText(usuario.getNombre());
        apellidos.setText(usuario.getApellidos());
        fechaNacimiento.setText(String.valueOf(usuario.getFechaNacimiento()));
        email.setText(usuario.getEmail());

    }
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }





}