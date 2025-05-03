package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.AdministradorDAO;
import com.proyecto3evaluacion.respawnix.DAO.UsuarioDAO;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.Administrador;
import com.proyecto3evaluacion.respawnix.model.Cliente;
import com.proyecto3evaluacion.respawnix.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.util.ArrayList;

public class IniciarSesionController {
    @FXML
    private Label emailContraseIncorrecto;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;


    public void iniciarSesion(ActionEvent event) {
        String email = emailTextField.getText();
        String password = passwordField.getText();
        boolean existe = false;

        ArrayList<Usuario> list = (ArrayList<Usuario>) UsuarioDAO.todosUsuarios();
        ArrayList<Administrador> administradors = (ArrayList<Administrador>) AdministradorDAO.todosAdministradores();

        for (Usuario usuario : list) {
            if (usuario.getEmail().equals(email) && usuario.getPassword().equals(password)) {
                UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
                usuarioActualController.setUsuario(usuario);
                existe = true;
                for (Administrador administrador : administradors) {
                    if (administrador.getEmailUsuario().equals(email)) {
                        iniciarSesionExitosoAdministrador(event);
                        return;
                    }
                }
            }
        }
        if (existe){
            iniciarSesionDeCliente(event);
        }else {
        emailContraseIncorrecto.setText("Contraseña o email incorrecto");
        }


    }

    private void iniciarSesionDeCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaInterfazUsuario.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = null;
            scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void iniciarSesionExitosoAdministrador(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaMenuVideoJuegos.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = null;
            scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void Registrarse(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaRegistrarse.fxml"));
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            currentStage.close();
            Scene scene = null;
            scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setTitle("Registrate");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}