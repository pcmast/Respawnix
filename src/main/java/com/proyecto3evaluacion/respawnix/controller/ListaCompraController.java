package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.*;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.model.CestaCompra;
import com.proyecto3evaluacion.respawnix.model.TarjetaPremium;
import com.proyecto3evaluacion.respawnix.model.TarjetaVIP;
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


    public Label precioPorcentaje;
    public Label porcentaje;


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
    private double descuento = 0;

    /**
     * Metodo que al iniciar el controlador llama a un metodo llamado mostrarLista y luego carga todos los videojuegos
     * y todos los juegos que tenga el usuario actual en la lista de la compra los recorre y les asigna el precio
     * dependiendo de la cantidad de juegos que haya añadido y comprueba si el usuario tiene una tarjeta comprada
     * de la base de datos lo enseña el precio de la cesta con el descuento aplicado dependiendo de la tarjeta que
     * disponga
     */
    public void initialize() {
        mostrarLista();
        List<VideoJuego> juegos = VideoJuegoDAO.todosLosJuegos();
        Map<String, Integer> juegosLista = CestaCompra.getInstance().todaCesta();
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
        boolean comprado = false;
        double precioPor =0;
        List<TarjetaPremium> premiums = TarjetaPremiumDAO.todasTarjetas();
        List<TarjetaVIP> vips = TarjetaVIPDAO.todasTarjetas();

        for (TarjetaPremium tarjetaPremium:premiums){
            if (tarjetaPremium.getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())){
                precioPor =(precio * 5) / 100;
                comprado = true;

            }
        }
        for (TarjetaVIP vip: vips){
            if (vip.getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())){
                precioPor =(precio * 10) / 100;
                comprado = true;

            }
        }


        if (comprado){
            double descuento = precio - precioPor;
            precioPorcentaje.setText(String.format("Descuento: %.2f€", descuento)); //Añade el precio con un string antes
            this.descuento = descuento;
        }

        precioCesta.setText(String.format("Precio: %.2f€", precio)); //Añade el precio con un string antes

    }

    /**
     * Metodo que coge de la base de datos todos los juegos del usuario actual que tenga en la cesta y luego todos los juegos
     * y luego añade en la lista de la compra el nombre del juego su precio individual y la cantidad que hay añadida en la cesta
     */
    public void mostrarLista() {
        Map<String, Integer> contador = CestaCompra.getInstance().todaCesta();
        List<VideoJuego> todosLosJuegos = VideoJuegoDAO.todosLosJuegos();
        ObservableList<String> listaVisual = FXCollections.observableArrayList();

        for (VideoJuego juego : todosLosJuegos) {
            if (contador.containsKey(juego.getNombre())) {
                int cantidad = contador.get(juego.getNombre());
                String jue = juego.getNombre() + " | Precio: " + juego.getPrecio() + "€ | Cantidad: " + cantidad;
                listaVisual.add(jue);
            }
        }

        listaCompra.setItems(listaVisual);
    }

    /**
     * Metodo que coge de la base de datos todos los juegos de la cesta del usuario actual
     * y si la cesta no esta vacia abre una ventana para añadir los datos de facturacion para comprar los juegos
     * si la cesta esta vacia muestra un label con el texto La cesta esta vacia
     * @param actionEvent
     */
    public void comprar(ActionEvent actionEvent) {
        Map<String, Integer> cesta = CestaCompra.getInstance().todaCesta();
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
            stage.setTitle("Respawnix");
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        }else {
            cestaVacia.setText("La cesta esta vacia");
        }
    }

    /**
     * Metodo que elimina un juego de la cesta de la compra y comprueba si el usuario a introducido el numero de juegos que quiere
     * quitar es valida es decir si tengo 5 juegos no puedo quitar 10 de la cesta
     * @param actionEvent El usuario hace click en el boton
     */
    public void eliminar(ActionEvent actionEvent) {
        String seleccion = listaCompra.getSelectionModel().getSelectedItem();
        if (seleccion == null){
            eliminado.setText("Seleccione Un Juego.");
            return;
        }
        String nombreJuego = seleccion.split(" \\| ")[0].trim();
        String email = UsuarioActualController.getInstance().getUsuario().getEmail();
        Map<String, Integer> list = CestaCompra.getInstance().todaCesta();
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
            CestaCompra.getInstance().eliminar(nombreJuego,resta);
            //CestaCompraDAO.eliminarCestaOActualizar(email, nombreJuego, resta);
        }


        mostrarLista();
        if (cantEliminar < 0 || cantEliminar > cantidad || cantEliminar == 0) {
            eliminado.setText("Cantidad inválida.");
        } else {
            eliminado.setText("Eliminado.");
        }
        precioCestaSet();

    }

    /**
     * Metodo que al eliminar un juego de la cesta coge todos los juegos de la base de datos
     * y todos los juegos de la cesta y vuelve a asignar el precio correcto de toda la cesta por si a quedado algun juego
     */
    public void precioCestaSet(){
        List<VideoJuego> juegos = VideoJuegoDAO.todosLosJuegos();
        Map<String, Integer> juegosLista = CestaCompra.getInstance().todaCesta();
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
        boolean comprado = false;
        double precioPor =0;
        List<TarjetaPremium> premiums = TarjetaPremiumDAO.todasTarjetas();
        List<TarjetaVIP> vips = TarjetaVIPDAO.todasTarjetas();

        for (TarjetaPremium tarjetaPremium:premiums){
            if (tarjetaPremium.getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())){
                precioPor =(precio * 5) / 100;
                comprado = true;

            }
        }
        for (TarjetaVIP vip: vips){
            if (vip.getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())){
                precioPor =(precio * 10) / 100;
                comprado = true;

            }
        }


        if (comprado){
            double descuento = precio - precioPor;
            precioPorcentaje.setText(String.format("Descuento: %.2f€", descuento));
            this.descuento = descuento;
        }

        precioCesta.setText(String.format("Precio: %.2f€", precio));

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