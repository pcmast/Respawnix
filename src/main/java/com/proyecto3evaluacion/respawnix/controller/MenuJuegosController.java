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

    /**
     * Metodo que al iniciar el controllador se ejecuta añadiendo una imagen al lado del buscador
     * y carga la lista de los juegos y si no esta vacia la lista carga el primer juego
     */
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

    /**
     * Metodo que coge de la base de datos todos los videojuegos y carga la lista
     * @return devuelve la lista
     */
    public ObservableList<VideoJuego> cargarLista() {
        ArrayList<VideoJuego> list = (ArrayList<VideoJuego>) VideoJuegoDAO.todosLosJuegos();
        ObservableList<VideoJuego> lista = FXCollections.observableArrayList();
        lista.addAll(list);
        return lista;
    }

    /**
     * Metodo que busca por todos los videojuegos de la base de datos por su nombre introducido por el usuario si no se encuentra
     * muestra un mensaje de no se encontraron coincidencias
     */
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

    /**
     * Metodo que muestra en la barra el juego seleccionado por el usuario
     */
    public void mostrarEnLaBarraLateral() {
        VideoJuego videoJuego = mostrarJuegosAnadidos.getSelectionModel().getSelectedItem();
        barraLateralInfo(videoJuego);
    }

    /**
     * Metodo que abre una ventana para poner la informacion de un nuevo juego y luego añade la lista del controlador
     * de menuAnnadirJuegoController
     * @param actionEvent Cuando el usuario hace click
     */
    public void botonAnadir(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaAnadirJuego.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage = new Stage();
            File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
            Image imageIcono = new Image(imagenURLIcono.toURI().toString());
            stage.getIcons().add(imageIcono);

            stage.setScene(scene);
            stage.setTitle("Respawnix");

            stage.show();

            MenuAnnadirJuegoController controller = fxmlLoader.getController();
            controller.setListaVideoJuegos(videoJuegos);
            controller.setStage(stage);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que cuando el usuario le de a eliminar coge el juego seleccionado y si es distinto a null lo elimina
     * de la base de datos
     * @param actionEvent cuando el usuario le da click al boton
     */
    public void botonEliminar(ActionEvent actionEvent) {
        VideoJuego videoJuegoSeleccionado = mostrarJuegosAnadidos.getSelectionModel().getSelectedItem();
        if (videoJuegoSeleccionado != null) {
            videoJuegos.remove(videoJuegoSeleccionado);
            barraLateralInfo(null);
            VideoJuegoDAO.eliminarJuego(videoJuegoSeleccionado);
        }


    }


    /**
     * Metodo que muestra una alerta con informacion del programa (metodo en todas las pantallas)
     * @param actionEvent el usuario clickea el boton
     */
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.show();
    }

    /**
     * metodo que cierra la sesion poniendo el usuario actual a null y abriendo de nuevo la ventana de iniciar sesion para
     * cambiar el usuario
     * @param actionEvent cuando el usuario le da click a cerrar sesion
     */
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

    /**
     * Metodo que muestra la ventana del perfil del usuario actual en este caso siempre sera administrador
     * @param actionEvent cuando el usuario le da click al boton
     */
    public void verPerfil(ActionEvent actionEvent) {
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
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }

    /**
     * Metodo que cuando se le da click abre una ventana nueva para actualizar un jeugo de la base de datos
     * @param actionEvent cuando el usuario le da click al boton
     */
    public void botonActualizar(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaActualizarJuego.fxml"));
        try {
            Scene scene = null;
            scene = new Scene(fxmlLoader.load());
            stage = new Stage();
            File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
            Image imageIcono = new Image(imagenURLIcono.toURI().toString());
            stage.getIcons().add(imageIcono);
            stage.setScene(scene);
            stage.setTitle("respawnix");
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Cuando se actualiza la un juego hay un boton para actualizar la lista y que salga actualizado
     */
    public void botonActualizarLista(){
        List<VideoJuego> list = VideoJuegoDAO.juegosPorNombre(buscar.getText());
        ObservableList<VideoJuego> juegos = FXCollections.observableArrayList();
        juegos.addAll(list);
        mostrarJuegosAnadidos.setItems(juegos);

    }
}
