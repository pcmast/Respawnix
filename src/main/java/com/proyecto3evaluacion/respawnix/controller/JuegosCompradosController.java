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

    /**
     * Metodo que al iniciar el controlador añade la lista ya cargada de los juegos que a comprado el usuario actual del
     * sistema
     */
    public void initialize(){
        juegosComprados.setItems(list);

    }

    /**
     * Metodo que carga en una lista todos los juegos que un usuario haya comprado todos en la base de datos
     * @return devuelve un ObservableList para que se pueda mostrar en el ListView
     */
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
