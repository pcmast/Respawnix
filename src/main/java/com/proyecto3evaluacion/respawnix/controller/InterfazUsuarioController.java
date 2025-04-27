package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.util.ArrayList;

public class InterfazUsuarioController {
    @FXML
    private Label descripcionJuego;
    @FXML
    private Label generoJuego;
    @FXML
    private Label plataformaJuego;
    @FXML
    private Label nombreJuego;
    @FXML
    private ImageView imagenJuego;
    @FXML
    private Label precioJuego;
    @FXML
    private ListView<VideoJuego> listaJuegosAComprar;

    private ObservableList<VideoJuego> videoJuegos = cargarLista();

    public void initialize() {
        listaJuegosAComprar.setItems(videoJuegos);
    }
    /**
     * Metodo que muestra en la barra de la informacion de los juegos todos los datos del juego
     * @param videoJuego recibe como parametro el videojuego que va a mostrar en la interfaz grafica
     */
    public void barraLateralInfo(VideoJuego videoJuego){
        if (videoJuego != null){
            nombreJuego.setText(videoJuego.getNombre());
            precioJuego.setText(String.valueOf(videoJuego.getPrecio()));
            descripcionJuego.setText(videoJuego.getDescripcion());
            generoJuego.setText(videoJuego.getGenero());
            plataformaJuego.setText(videoJuego.getPlataforma());
            File imagen = new File(videoJuego.getImage());
            Image image = new Image(imagen.toURI().toString());
            imagenJuego.setImage(image);
        }

    }

    public void mostrarEnLaBarra(MouseEvent mouseEvent) {
        VideoJuego videoJuego = listaJuegosAComprar.getSelectionModel().getSelectedItem();
        barraLateralInfo(videoJuego);
    }
    public ObservableList<VideoJuego> cargarLista(){
        ArrayList<VideoJuego> list = (ArrayList<VideoJuego>) VideoJuegoDAO.todosLosJuegos();
        ObservableList<VideoJuego> lista = FXCollections.observableArrayList();
        lista.addAll(list);
        return lista;
    }

}
