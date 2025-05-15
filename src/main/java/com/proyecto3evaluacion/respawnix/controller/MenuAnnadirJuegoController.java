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
    public VideoJuego anadirJuego() {
        videoJuego = new VideoJuego();
        String nombre = nombreJuego.getText();
        String plataforma = plataformaDelJuego.getText();
        String genero = generoJuego.getText();
        Double precio = Double.valueOf(precioDelJuego.getText());
        String descripcion = descripcionJuego.getText();
        String imagen = rutaImagenJuego;

        videoJuego.setNombre(nombre);
        videoJuego.setPlataforma(plataforma);
        videoJuego.setGenero(genero);

        videoJuego.setPrecio(precio);
        videoJuego.setDescripcion(descripcion);
        videoJuego.setImagen(imagen);



        VideoJuegoDAO.insertarJuego(nombre,descripcion,genero,plataforma,precio,imagen);

        return videoJuego;
    }

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

    public void anadirJuegoSalir (ActionEvent actionEvent){
        videoJuego = anadirJuego();
        list.add(videoJuego);
        if (stage != null) {
            stage.close();
        }


    }

    public void cancelarAnnadir(ActionEvent actionEvent) {
        stage.close();
    }

    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }
}