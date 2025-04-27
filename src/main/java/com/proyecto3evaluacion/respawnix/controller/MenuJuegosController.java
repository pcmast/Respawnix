package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MenuJuegosController {

    public ListView<VideoJuego> mostrarJuegosAnadidos;
    public Label nombreLabel;
    public Label precioLabel;
    public Label plataformaLabel;
    public Label generoLabel;
    public Label descripcionLabel;
    public ImageView imagenJuego;
    private ObservableList<VideoJuego> videoJuegos = cargarLista();


    public void initialize() {
        mostrarJuegosAnadidos.setItems(videoJuegos);
    }
    public ObservableList<VideoJuego> cargarLista(){
        ArrayList<VideoJuego> list = (ArrayList<VideoJuego>) VideoJuegoDAO.todosLosJuegos();
        ObservableList<VideoJuego> lista = FXCollections.observableArrayList();
        lista.addAll(list);
        return lista;
    }


    /**
     * Metodo que muestra en la barra de la informacion de los juegos todos los datos del juego
     * @param videoJuego recibe como parametro el videojuego que va a mostrar en la interfaz grafica
     */
    public void barraLateralInfo(VideoJuego videoJuego){
        if (videoJuego != null){
            nombreLabel.setText(videoJuego.getNombre());
            precioLabel.setText(String.valueOf(videoJuego.getPrecio()));
            plataformaLabel.setText(videoJuego.getPlataforma());
            generoLabel.setText(videoJuego.getGenero());
            descripcionLabel.setText(videoJuego.getDescripcion());
            File imagen = new File(videoJuego.getImage());
            Image image = new Image(imagen.toURI().toString());
            imagenJuego.setImage(image);
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
