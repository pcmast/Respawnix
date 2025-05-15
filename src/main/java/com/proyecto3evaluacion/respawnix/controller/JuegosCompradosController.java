package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoCompradoDAO;
import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import com.proyecto3evaluacion.respawnix.model.VideoJuegoComprado;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

public class JuegosCompradosController {

    private ObservableList<VideoJuegoComprado> list = cargarLista();
    @FXML
    private ListView<VideoJuegoComprado> juegosComprados;

    public void initialize(){
        juegosComprados.setItems(list);

    }

    public ObservableList<VideoJuegoComprado> cargarLista() {
        List<VideoJuegoComprado> list = VideoJuegoCompradoDAO.todosLosJuegosComprados();
        ObservableList<VideoJuegoComprado> lista = FXCollections.observableArrayList();
        for (VideoJuegoComprado videoJuegoComprado : list){
            if (UsuarioActualController.getInstance().getUsuario().getEmail().equals(videoJuegoComprado.getEmail())){
                lista.add(videoJuegoComprado);
            }
        }
        if (juegosComprados != null) {
            juegosComprados.setItems(lista);
        }

        return lista;
    }


    public void mostrarAcercaDe(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Respawnix");
        alert.setContentText("Respawnix tienda de videojuegos desarrollada por Pedro Castaño Marín");
        alert.showAndWait();
    }


}
