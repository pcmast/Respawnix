package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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





}