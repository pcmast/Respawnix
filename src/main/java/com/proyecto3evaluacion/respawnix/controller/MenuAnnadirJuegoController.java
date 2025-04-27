package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class MenuAnnadirJuegoController {

    public TextField nombreJuego;
    public TextField plataformaDelJuego;
    public TextField generoJuego;
    public TextField precioDelJuego;
    public TextField descripcionJuego;
    public ImageView imageViewJuego;
    private ObservableList<VideoJuego> list = FXCollections.observableArrayList();
    private Stage stage;
    private VideoJuego videoJuego;

    public void botonSubirImagen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen del juego");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png")
        );
        File selectedFile = fileChooser.showOpenDialog(null);
        File destinationFolder = new File("IdeaProjects\\Respawnix\\images");
        File destinationFile = new File(destinationFolder, selectedFile.getName());

        try {

            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public VideoJuego anadirJuego() {
        videoJuego = new VideoJuego();

        videoJuego.setNombre(String.valueOf(nombreJuego));
        videoJuego.setPlataforma(String.valueOf(plataformaDelJuego));
        videoJuego.setGenero(String.valueOf(generoJuego));

        videoJuego.setPrecio(precioDelJuego);
        videoJuego.setDescripcion(String.valueOf(descripcionJuego));

        return videoJuego;
    }

    public void setListaVideoJuegos(ObservableList<VideoJuego> lista) {
        if (list != null) {
            this.list = lista;
        }

    }
    //Establece el stage de este controlador al de menuJuegosController para luego poder cerrar la ventana una vez añadido el juego
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void anadirJuegoSalir (ActionEvent actionEvent){
        videoJuego = anadirJuego();
        list.add(videoJuego);
        if (stage != null) {
            stage.close();
        }


    }
}