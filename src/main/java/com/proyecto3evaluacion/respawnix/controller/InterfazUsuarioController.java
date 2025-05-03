package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.CestaCompraDAO;
import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.model.CestaCompra;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import com.proyecto3evaluacion.respawnix.utils.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class InterfazUsuarioController {

    public Label videoJuegoAnnadido;
    public TextField cantidadAComprar;


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
        if (!listaJuegosAComprar.getItems().isEmpty()) {
            listaJuegosAComprar.getSelectionModel().selectFirst();
            barraLateralInfo(listaJuegosAComprar.getSelectionModel().getSelectedItem());
        }

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
                stage.showAndWait();
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
                stage.showAndWait();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void annadirALaCesta(ActionEvent actionEvent) {
        if (Utilidades.validarNumero(cantidadAComprar.getText())){
        VideoJuego seleccionado = listaJuegosAComprar.getSelectionModel().getSelectedItem();
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
        CestaCompra.setEmailUsuario(usuarioActualController.getUsuario().getEmail());
        CestaCompra.annadir(seleccionado.getNombre());

        CestaCompraDAO.actualizarEnLaLista(usuarioActualController.getUsuario().getEmail(), seleccionado.getNombre(), Integer.parseInt(cantidadAComprar.getText()));
        videoJuegoAnnadido.setText("Añadido a la cesta");
        }else {
            videoJuegoAnnadido.setText("Cantidad no válida");
        }
    }




    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }

    public void cerrarSesionUsuario(ActionEvent actionEvent) {
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
        usuarioActualController.setUsuario(null);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaIniciarSesion.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            stage = (Stage) listaJuegosAComprar.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarJuegosComprados(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaJuegosComprados.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.centerOnScreen();
            newStage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
