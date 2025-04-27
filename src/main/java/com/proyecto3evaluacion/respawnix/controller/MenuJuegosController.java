package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuJuegosController {

    public ListView<VideoJuego> mostrarJuegosAnadidos;
    public Label nombreLabel;
    public Label precioLabel;
    public Label plataformaLabel;
    public Label generoLabel;
    public Label descripcionLabel;
    private ObservableList<VideoJuego> videoJuegos = FXCollections.observableArrayList();


    public void initialize() {
        mostrarJuegosAnadidos.setItems(videoJuegos);
    }

    public void barraLateralInfo(VideoJuego videoJuego){
        if (videoJuego != null){
            nombreLabel.setText(videoJuego.getNombre());
            precioLabel.setText(videoJuego.getPrecio());
            plataformaLabel.setText(videoJuego.getPlataforma());
            generoLabel.setText(videoJuego.getGenero());
            descripcionLabel.setText(videoJuego.getDescripcion());
        }

    }

    public void mostrarEnLaBarraLateral(MouseEvent mouseEvent) {
        VideoJuego videoJuego = mostrarJuegosAnadidos.getSelectionModel().getSelectedItem();
        barraLateralInfo(videoJuego);
    }

    public void botonAnadir(ActionEvent actionEvent) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaAnadirJuego.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setTitle("Añadir Juego");

            newStage.show();
            MenuAnnadirJuegoController controller = fxmlLoader.getController();
            controller.setListaVideoJuegos(videoJuegos);
            controller.setStage(newStage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void botonEliminar(ActionEvent actionEvent) {

    }

    public void botonEditar(ActionEvent actionEvent) {
    }


}
