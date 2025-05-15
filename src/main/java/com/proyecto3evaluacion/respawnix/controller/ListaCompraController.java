package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.CestaCompraDAO;
import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.model.CestaCompra;
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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ListaCompraController {


    @FXML
    private Label cestaVacia;
    @FXML
    private Label precioCesta;
    @FXML
    private TextField cantidadAEliminar;
    @FXML
    private Label eliminado;
    @FXML
    private ListView<String> listaCompra;


    public void initialize() {
        mostrarLista();
        List<VideoJuego> juegos = VideoJuegoDAO.todosLosJuegos();
        Map<String, Integer> juegosLista = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
        String nombreJuego;
        int cantidad =0;
        double precio = 0;

        for (Map.Entry<String, Integer> entry : juegosLista.entrySet()) {
            nombreJuego = entry.getKey();
            cantidad = entry.getValue();
            for (VideoJuego videoJuego: juegos){
                if (videoJuego.getNombre().equals(nombreJuego) && cantidad == 1){
                    precio = videoJuego.getPrecio() + precio;
                }
                if (videoJuego.getNombre().equals(nombreJuego) && cantidad != 1){
                    precio += (cantidad * videoJuego.getPrecio());
                }

            }

        }
        precioCesta.setText(String.format("Precio: %.2f€", precio));


    }

    public void mostrarLista() {
        List<String> nombres = CestaCompra.getVideoJuego();
        Map<String, Integer> contador = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
        List<VideoJuego> todosLosJuegos = VideoJuegoDAO.todosLosJuegos();
        ObservableList<String> listaVisual = FXCollections.observableArrayList();

        for (VideoJuego juego : todosLosJuegos) {
            if (contador.containsKey(juego.getNombre())) {
                int cantidad = contador.get(juego.getNombre());
                String item = juego.getNombre() + " | Precio: " + juego.getPrecio() + "€ | Cantidad: " + cantidad;
                listaVisual.add(item);
            }
        }

        listaCompra.setItems(listaVisual);
    }


    public void comprar(ActionEvent actionEvent) {
        Map<String, Integer> cesta = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
        if (!cesta.isEmpty()){
        FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaCompra.fxml"));
        Scene scene = null;
        Stage stage = new Stage();
        try {
            File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
            Image imageIcono = new Image(imagenURLIcono.toURI().toString());
            stage.getIcons().add(imageIcono);
            
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }else {
            cestaVacia.setText("La cesta esta vacia");
        }
    }


    public void eliminar(ActionEvent actionEvent) {
        String seleccion = listaCompra.getSelectionModel().getSelectedItem();
        if (seleccion == null){
            eliminado.setText("Seleccione Un Juego.");
            return;
        }
        String nombreJuego = seleccion.split(" \\| ")[0].trim();
        String email = UsuarioActualController.getInstance().getUsuario().getEmail();
        Map<String, Integer> list = CestaCompraDAO.cesta(email);
        int resta = 0;
        int cantEliminar;
        int cantidad = 0;
        try {
            cantEliminar = Integer.parseInt(cantidadAEliminar.getText());
            if (list.containsKey(nombreJuego)) {
                cantidad = list.get(nombreJuego);
                resta = cantidad - cantEliminar;

            }

        } catch (NumberFormatException e) {
            eliminado.setText("Cantidad inválida.");
            return;
        }
        if (cantEliminar < 0 || cantEliminar > cantidad) {
            eliminado.setText("Cantidad inválida.");
        } else {
            CestaCompraDAO.eliminarCestaOActualizar(email, nombreJuego, resta);
        }


        mostrarLista();
        if (cantEliminar < 0 || cantEliminar > cantidad || cantEliminar == 0) {
            eliminado.setText("Cantidad inválida.");
        } else {
            eliminado.setText("Eliminado.");
        }
        precioCestaSet();

    }
    public void precioCestaSet(){
        List<VideoJuego> juegos = VideoJuegoDAO.todosLosJuegos();
        Map<String, Integer> juegosLista = CestaCompraDAO.cesta(UsuarioActualController.getInstance().getUsuario().getEmail());
        String nombreJuego;
        int cantidad = 0;
        double precio = 0;


        for (Map.Entry<String, Integer> entry : juegosLista.entrySet()) {
            nombreJuego = entry.getKey();
            cantidad = entry.getValue();
            for (VideoJuego videoJuego : juegos) {
                if (videoJuego.getNombre().equals(nombreJuego)) {
                    precio += cantidad * videoJuego.getPrecio();
                    break;
                }
            }
            }


        precioCesta.setText(String.format("Precio: %.2f€", precio));

    }



    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }
}