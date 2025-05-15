package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import java.util.List;

public class MenuJuegosController {

    public Label vacio;
    @FXML
    private ListView<VideoJuego> mostrarJuegosAnadidos;
    @FXML
    private Label nombreLabel;
    @FXML
    private Label precioLabel;
    @FXML
    private Label plataformaLabel;
    @FXML
    private Label generoLabel;
    @FXML
    private Label descripcionLabel;
    @FXML
    private ImageView imagenJuego;
    @FXML
    private TextField buscar;
    @FXML
    private ImageView imagen;
    private ObservableList<VideoJuego> videoJuegos = cargarLista();
    private Stage stage;

    public void initialize() {
        File imagenURL = new File("images/lupa.jpg");
        Image image = new Image(imagenURL.toURI().toString());
        imagen.setImage(image);
        mostrarJuegosAnadidos.setItems(videoJuegos);
        if (!mostrarJuegosAnadidos.getItems().isEmpty()) {
            mostrarJuegosAnadidos.getSelectionModel().selectFirst();
            barraLateralInfo(mostrarJuegosAnadidos.getSelectionModel().getSelectedItem());
        }

    }

    public ObservableList<VideoJuego> cargarLista() {
        ArrayList<VideoJuego> list = (ArrayList<VideoJuego>) VideoJuegoDAO.todosLosJuegos();
        ObservableList<VideoJuego> lista = FXCollections.observableArrayList();
        lista.addAll(list);
        return lista;
    }

    public void buscarJuegos(){
        List<VideoJuego> list = VideoJuegoDAO.juegosPorNombre(buscar.getText());
        ObservableList<VideoJuego> juegos = FXCollections.observableArrayList();
        juegos.addAll(list);
        if (juegos.isEmpty()){
            vacio.setText("No se encontraron coincidencias");
        }else {
            vacio.setText("");
        }
        mostrarJuegosAnadidos.setItems(juegos);

    }

    /**
     * Metodo que muestra en la barra de la informacion de los juegos todos los datos del juego
     *
     * @param videoJuego recibe como parametro el videojuego que va a mostrar en la interfaz grafica
     */
    public void barraLateralInfo(VideoJuego videoJuego) {
        if (videoJuego != null) {
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
            stage = new Stage();
            File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
            Image imageIcono = new Image(imagenURLIcono.toURI().toString());
            stage.getIcons().add(imageIcono);

            stage.setScene(scene);
            stage.setTitle("Añadir Juego");

            stage.show();

            MenuAnnadirJuegoController controller = fxmlLoader.getController();
            controller.setListaVideoJuegos(videoJuegos);
            controller.setStage(stage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void botonEliminar(ActionEvent actionEvent) {
        VideoJuego videoJuegoSeleccionado = mostrarJuegosAnadidos.getSelectionModel().getSelectedItem();
        if (videoJuegoSeleccionado != null) {
            videoJuegos.remove(videoJuegoSeleccionado);
            barraLateralInfo(null);
            VideoJuegoDAO.eliminarJuego(videoJuegoSeleccionado);
        }


    }



    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.show();
    }

    public void cerrarSesionUsuario(ActionEvent actionEvent) {
        UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
        usuarioActualController.setUsuario(null);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaIniciarSesion.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            stage = (Stage) mostrarJuegosAnadidos.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void verPerfil(ActionEvent actionEvent) {
        if (stage == null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaPerfilUsuario.fxml"));
                Scene scene = null;
                scene = new Scene(fxmlLoader.load());
                stage = new Stage();
                File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
                Image imageIcono = new Image(imagenURLIcono.toURI().toString());
                stage.getIcons().add(imageIcono);

                stage.setScene(scene);
                stage.setTitle("respawnix");
                stage.setOnCloseRequest(event -> stage = null);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
