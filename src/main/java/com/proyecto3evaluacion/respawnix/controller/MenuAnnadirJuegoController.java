package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class MenuAnnadirJuegoController {

    @FXML
    private Label datosIntroducidos;
    @FXML
    private TextField nombreJuego;
    @FXML
    private TextField plataformaDelJuego;
    @FXML
    private TextField generoJuego;
    @FXML
    private TextField precioDelJuego;
    @FXML
    private TextField descripcionJuego;
    @FXML
    private ImageView imageViewJuego;
    private String rutaImagenJuego;

    private ObservableList<VideoJuego> list = FXCollections.observableArrayList();
    private Stage stage;
    private VideoJuego videoJuego;
    private boolean actualizado = false;

    /**
     * Metodo que selecciona una imagen del equipo para asignarsela a un videojuego
     * @param actionEvent cuando el usuario le da click al boton
     */
    public void botonSubirImagen(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen del juego");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.jpeg", "*.png")
        );

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {

            File destinationFolder = new File(System.getProperty("user.dir") + File.separator + "images");

            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }

            File destinationFile = new File(destinationFolder, selectedFile.getName());

            try {
                Files.copy(selectedFile.toPath(), Paths.get("images", selectedFile.getName()), StandardCopyOption.REPLACE_EXISTING);

                rutaImagenJuego = Paths.get("images", selectedFile.getName()).toString();

                imageViewJuego.setImage(new Image(new File(rutaImagenJuego).toURI().toString()));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Metodo que añade un videojuego a la base de datos con la informacion introducida por el usuario administrador
     * @return devuelve el videojuego añadido
     */
    public VideoJuego anadirJuego() {
        videoJuego = new VideoJuego();
        String nombre ="";
        String plataforma ="";
        String genero ="";
        double precio =0;
        String descripcion ="";
        String imagen ="";
        int nadaVacio =0;
        if(!nombreJuego.getText().isEmpty()){
            nombre = nombreJuego.getText();
            nadaVacio++;
        }else {
            datosIntroducidos.setText("Debes Introducir todos los campos");
        }
        if (!plataformaDelJuego.getText().isEmpty()){
            plataforma = plataformaDelJuego.getText();
            nadaVacio++;
        }else {
            datosIntroducidos.setText("Debes Introducir todos los campos");
        }

        if (!generoJuego.getText().isEmpty()){
            genero = generoJuego.getText();
            nadaVacio++;
        }else {
            datosIntroducidos.setText("Debes Introducir todos los campos");
        }
        if (!precioDelJuego.getText().isEmpty()){
            precio = Double.parseDouble((precioDelJuego.getText()));
            nadaVacio++;
        }else {
            datosIntroducidos.setText("Debes Introducir todos los campos");
        }
        if (!descripcionJuego.getText().isEmpty()){
            descripcion = descripcionJuego.getText();
            nadaVacio++;
        }else {
            datosIntroducidos.setText("Debes Introducir todos los campos");
        }

        if (rutaImagenJuego != null){
            imagen = rutaImagenJuego;
            nadaVacio++;
        }else {
            datosIntroducidos.setText("Debes Introducir todos los campos");
        }


        if (nadaVacio == 6){



        videoJuego.setNombre(nombre);
        videoJuego.setPlataforma(plataforma);
        videoJuego.setGenero(genero);

        videoJuego.setPrecio(precio);
        videoJuego.setDescripcion(descripcion);
        videoJuego.setImagen(imagen);


        actualizado = true;
        VideoJuegoDAO.insertarJuego(nombre,descripcion,genero,plataforma,precio,imagen);
        }

        return videoJuego;
    }

    /**
     * Metodo que si la lista no esta vacia asigna la lista de esta clase la lista recibida como parametro
     * @param lista la lista recibida de otra clase
     */
    public void setListaVideoJuegos(ObservableList<VideoJuego> lista) {
        if (list != null) {
            this.list = lista;
        }

    }

    //Establece el stage de este controlador al de menuJuegosController para luego poder cerrar la ventana una vez añadido el juego
    public void setStage(Stage stage) {
        File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
        Image imageIcono = new Image(imagenURLIcono.toURI().toString());
        stage.getIcons().add(imageIcono);

        this.stage = stage;
    }

    /**
     * Metodo que coge el videojuego introducido por el administrador y lo añade a la lista y luego cierra la ventana
     * @param actionEvent
     */
    public void anadirJuegoSalir (ActionEvent actionEvent){
        videoJuego = anadirJuego();
        if (actualizado){
        list.add(videoJuego);
        if (stage != null) {
            stage.close();
        }
        }

    }

    /**
     * Boton para cancelar operacion de añadir un juego
     * @param actionEvent cuando el usuario clickea el boton
     */
    public void cancelarAnnadir(ActionEvent actionEvent) {
        stage.close();
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
        alert.showAndWait();
    }
}