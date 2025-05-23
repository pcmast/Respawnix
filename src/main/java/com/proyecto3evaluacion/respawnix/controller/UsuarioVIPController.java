package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.TarjetaDAO;
import com.proyecto3evaluacion.respawnix.DAO.TarjetaPremiumDAO;
import com.proyecto3evaluacion.respawnix.DAO.TarjetaVIPDAO;
import com.proyecto3evaluacion.respawnix.DAO.UsuarioDAO;
import com.proyecto3evaluacion.respawnix.model.TarjetaPremium;
import com.proyecto3evaluacion.respawnix.model.TarjetaVIP;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UsuarioVIPController {


    @FXML
    private ImageView fotoVIP;
    @FXML
    private ImageView fotoPREMIUM;
    @FXML
    private Label yacomprado;

    /**
     * Metodo que al iniciar el controlador añade dos fotos a dos imagenes de la pantalla de interfaz grafica
     */
    public void initialize(){
        File imagenURL = new File("images/VIP.jpg");
        Image imageVIP = new Image(imagenURL.toURI().toString());
        fotoVIP.setImage(imageVIP);
        File imageURL = new File("images/PREMIUM.jpg");
        Image imagePREMIUM = new Image(imageURL.toURI().toString());
        fotoPREMIUM.setImage(imagePREMIUM);

    }

    /**
     * Si el usuario decide comprar la tarjeta PREMIUM se comprobara que ese usuario no tenga una tarjeta comprada ya
     * y si no la tiene registra en la base de datos que a comprado la tarjeta PREMIUM si ya tiene una tarjeta el
     * sistema lo notificara
     * @param actionEvent cuando el usuario le da click al boton
     */
    public void comprarPremium(ActionEvent actionEvent) {
        boolean comprado = false;
        int porcentaje = 5;
        List<TarjetaPremium> listaPremium = TarjetaPremiumDAO.todasTarjetas();
        List<TarjetaVIP> listaVIP = TarjetaVIPDAO.todasTarjetas();
        for (TarjetaPremium tarjetaPremium : listaPremium){
            if (tarjetaPremium.getUsuario().getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())){
                comprado = true;
            }
        }
        for (TarjetaVIP tarjetaVIP : listaVIP){
            if (tarjetaVIP.getUsuario().getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())){
                comprado = true;
            }
        }

        if (!comprado){
        TarjetaDAO.insertarTarjeta("PREMIUM",UsuarioActualController.getInstance().getUsuario().getEmail());
        TarjetaPremiumDAO.insertarTarjeta(UsuarioActualController.getInstance().getUsuario().getEmail(), porcentaje);
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        }else {
            yacomprado.setText("YA TIENES UNA TAJETA COMPRADA");
        }

    }

    /**
     * Cuando el usuario decide comprar la tarjeta VIP se comprueba si ese mismo usuario tiene ya una tarjeta comprada si la
     * tiene notificara que no puede comprar mas y si no tiene ninguna tarjeta registra en la base de datos que a comprado la tarjeta
     * @param actionEvent cuando el usuario le da a comprar
     */
    public void compraVIP(ActionEvent actionEvent) {
        boolean comprado = false;
        int porcentaje = 10;
        List<TarjetaPremium> listaPremium = TarjetaPremiumDAO.todasTarjetas();
        List<TarjetaVIP> listaVIP = TarjetaVIPDAO.todasTarjetas();
        for (TarjetaPremium tarjetaPremium : listaPremium){
            if (tarjetaPremium.getUsuario().getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())){
                comprado = true;
            }
        }
        for (TarjetaVIP tarjetaVIP : listaVIP){
            if (tarjetaVIP.getUsuario().getEmail().equals(UsuarioActualController.getInstance().getUsuario().getEmail())){
                comprado = true;
            }
        }
        if (!comprado) {
            TarjetaDAO.insertarTarjeta("VIP", UsuarioActualController.getInstance().getUsuario().getEmail());
            TarjetaVIPDAO.insertarTarjeta(UsuarioActualController.getInstance().getUsuario().getEmail(), porcentaje);
            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        }else {
            yacomprado.setText("YA TIENES UNA TAJETA COMPRADA");
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
        alert.showAndWait();
    }
}