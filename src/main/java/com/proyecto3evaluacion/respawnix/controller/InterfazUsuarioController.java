package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class InterfazUsuarioController {

    @FXML
    private ImageView imagen;
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

    private Stage stage;

    public void initialize() {
        File imagenURL = new File("images/lupa.jpg");
        Image image = new Image(imagenURL.toURI().toString());
        imagen.setImage(image);

        listaJuegosAComprar.setItems(videoJuegos);
    }

    /**
     * Metodo que muestra en la barra de la informacion de los juegos todos los datos del juego
     *
     * @param videoJuego recibe como parametro el videojuego que va a mostrar en la interfaz grafica
     */
    public void barraLateralInfo(VideoJuego videoJuego) {
        if (videoJuego != null) {
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

    public ObservableList<VideoJuego> cargarLista() {
        ArrayList<VideoJuego> list = (ArrayList<VideoJuego>) VideoJuegoDAO.todosLosJuegos();
        ObservableList<VideoJuego> lista = FXCollections.observableArrayList();
        lista.addAll(list);
        return lista;
    }

    public void comprar(ActionEvent actionEvent) {
        if (stage == null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaCesta.fxml"));
                Scene scene = null;
                scene = new Scene(fxmlLoader.load());
                stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("respawnix");
                stage.setOnCloseRequest(event -> stage = null);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void verPerfil(ActionEvent actionEvent) {
        if (stage == null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaPerfilUsuario.fxml"));
                Scene scene = null;
                scene = new Scene(fxmlLoader.load());
                stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("respawnix");
                stage.setOnCloseRequest(event -> stage = null);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void annadirALaCesta(ActionEvent actionEvent) {





    }
}
