package com.proyecto3evaluacion.respawnix;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

public class RespawnixApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaIniciarSesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        File imagenURL = new File("images/MANDOPEQUEÑO.png");
        Image image = new Image(imagenURL.toURI().toString());
        stage.getIcons().add(image);
        stage.setTitle("Respawnix");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}