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
import java.util.List;
import java.util.stream.Collectors;

public class InterfazUsuarioController {

    public Label videoJuegoAnnadido;
    public TextField cantidadAComprar;
    public TextField buscar;
    public Label vacio;


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

    /**
     * Metodo que al iniciar el controlador pone una imagen en un imagenView de la ventana y establece en la lista de los
     * juegos. Si la lista no esta vacia elije el primer juego para que no se quede la lista sin tener ningun juego
     * seleccionado
     */
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
     * Metodo que con un textField busca en la base de datos si el nombre dle juego introducido coincide con alguno de la lista
     * si no lo hace muestra en la lista un mensaje no se encontraron coincidencias y si si lo hace añade a la lista los juegos
     * que tengan un nombre parecido
     */
    public void buscarJuegos() {
        List<VideoJuego> juegosFiltrados = VideoJuegoDAO.juegosPorNombre(buscar.getText()).stream()
                .filter(juego -> juego != null)
                .collect(Collectors.toList());

        ObservableList<VideoJuego> juegos = FXCollections.observableArrayList(juegosFiltrados);

        vacio.setText(juegos.isEmpty() ? "No se encontraron coincidencias" : "");
        listaJuegosAComprar.setItems(juegos);
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

    /**
     * Metodo que dependiendo del juego seleccionado por el usuario muestra la informacion de ese mismo juego en la barra lateral
     *
     * @param mouseEvent si el usuario da click al videojuego
     */
    public void mostrarEnLaBarra(MouseEvent mouseEvent) {
        VideoJuego videoJuego = listaJuegosAComprar.getSelectionModel().getSelectedItem();
        barraLateralInfo(videoJuego);
    }

    /**
     * Metodo que carga la lista de todos los videojuegos y los añade a una observableList para que el listView
     * la pueda mostrar bien
     *
     * @return devuelve la observableList con los juegos
     */
    public ObservableList<VideoJuego> cargarLista() {
        ArrayList<VideoJuego> list = (ArrayList<VideoJuego>) VideoJuegoDAO.todosLosJuegos();
        ObservableList<VideoJuego> lista = FXCollections.observableArrayList();
        lista.addAll(list);
        return lista;
    }

    /**
     * Metodo que abre la ventada de la cesta de la compra
     *
     * @param actionEvent cuando el usuario le da click a comprar
     */
    public void comprar(ActionEvent actionEvent) {
        if (stage == null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaCesta.fxml"));
                Scene scene = null;
                scene = new Scene(fxmlLoader.load());
                stage = new Stage();
                File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
                Image imageIcono = new Image(imagenURLIcono.toURI().toString());
                stage.getIcons().add(imageIcono);

                stage.setScene(scene);
                stage.setTitle("respawnix");
                stage.setOnCloseRequest(event -> stage = null); //dejo la variable stage en null para que luego se pueda abrir de nuevo la ventana
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }


    /**
     * Metodo que abre la ventana del perfil del usuario mostrando los datos del usuario
     *
     * @param actionEvent
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
            stage.setOnCloseRequest(event -> stage = null); //dejo la variable stage en null para que luego se pueda abrir de nuevo la ventana
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Metodo que valida si lo introducido es un numero si lo es coge el juego seleccionado y lo añade a la cesta de la compra
     * actualizando la base de datos con el juego y el email del usuario
     *
     * @param actionEvent
     */
    public void annadirALaCesta(ActionEvent actionEvent) {
        if (Utilidades.validarNumero(cantidadAComprar.getText())) {
            VideoJuego seleccionado = listaJuegosAComprar.getSelectionModel().getSelectedItem();
            UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
            CestaCompra cestaCompra = CestaCompra.getInstance();
            cestaCompra.setEmailUsuario(usuarioActualController.getUsuario().getEmail());
            cestaCompra.annadir(seleccionado.getNombre(), Integer.parseInt(cantidadAComprar.getText()));

            videoJuegoAnnadido.setText("Añadido a la cesta");
        } else {
            videoJuegoAnnadido.setText("Cantidad no válida");
        }
    }


    /**
     * Metodo que muestra una alerta con informacion del programa (metodo en todas las pantallas)
     *
     * @param actionEvent el usuario clickea el boton
     */
    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }

    /**
     * Metodo que cierra sesion del usuario actual del sistema y te devuelve a la ventana de iniciar sesion para cambiar de
     * usuario
     *
     * @param actionEvent
     */
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

    /**
     * Metodo que abre una ventana de los videojuegos comprados del usuario
     *
     * @param actionEvent cuando el usuario hace click al boton
     */
    public void mostrarJuegosComprados(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaJuegosComprados.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            Stage newStage = new Stage();
            File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
            Image imageIcono = new Image(imagenURLIcono.toURI().toString());
            newStage.getIcons().add(imageIcono);

            newStage.setScene(scene);
            newStage.centerOnScreen();
            newStage.setTitle("Respawnix");
            newStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que abre una ventada de comprar una tarjeta y el usuario volverse VIP
     *
     * @param actionEvent cuando el usuario hace click al boton
     */
    public void hazteVIP(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaVIP.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
            Stage newStage = new Stage();
            File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
            Image imageIcono = new Image(imagenURLIcono.toURI().toString());
            newStage.getIcons().add(imageIcono);

            newStage.setScene(scene);
            newStage.setTitle("Respawnix");
            newStage.centerOnScreen();
            newStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
