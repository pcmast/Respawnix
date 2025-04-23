package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class IniciarSesionController {
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;

    public void iniciarSesion(ActionEvent event){




    }

    public void Registrarse(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaRegistrarse.fxml"));
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            currentStage.close();
            Scene scene = null;
            scene = new Scene(loader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}