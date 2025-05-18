package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.CestaCompraDAO;
import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class ActualizarJuegoController {


    @FXML
    private Label nombreJuegoVacio;
    @FXML
    private TextField campoNombreAntiguo;
    @FXML
    private TextField campoNombre;
    @FXML
    private TextField campoDescripcion;
    @FXML
    private TextField campoGenero;
    @FXML
    private TextField campoPlataforma;
    @FXML
    private TextField campoPrecio;


    /**
     * Metodo que actualiza un objeto de la base de datos y tambien lo actualiza en la cesta de la compra si no se introduce
     * ningun dato aparte del nombre antiguo se dejan los datos tal cual estan
     * @param actionEvent recibe como parametro el click que ha dado el usuario para luego conseguir la ventana
     * anterior y cerrarla
     */
    public void actualizarObjeto(ActionEvent actionEvent) {
        boolean nombreVacio = false;
        VideoJuego videoJuegoAntiguo = new VideoJuego();
        List<VideoJuego> list = VideoJuegoDAO.todosLosJuegos();
        for (VideoJuego videoJuego: list){
            if (videoJuego.getNombre().equals(campoNombreAntiguo.getText())){
            videoJuegoAntiguo = videoJuego;
            }
        }
        if (campoNombreAntiguo.getText().isEmpty()){
            nombreJuegoVacio.setText("Introduce el nombre del juego");
            nombreVacio = true;
        }

        if (!nombreVacio){
            if (campoNombre.getText().isEmpty()) {
                campoNombre.setText(videoJuegoAntiguo.getNombre());
            }
            if (campoDescripcion.getText().isEmpty()) {
                campoDescripcion.setText(videoJuegoAntiguo.getDescripcion());
            }
            if (campoGenero.getText().isEmpty()) {
                campoGenero.setText(videoJuegoAntiguo.getGenero());
            }
            if (campoPlataforma.getText().isEmpty()) {
                campoPlataforma.setText(videoJuegoAntiguo.getPlataforma());
            }
            if (campoPrecio.getText().isEmpty()) {
                campoPrecio.setText(String.valueOf(videoJuegoAntiguo.getPrecio()));
            }

            double precio = Double.parseDouble(campoPrecio.getText());

            CestaCompraDAO.actualizarNombre(campoNombreAntiguo.getText(),campoNombre.getText());

            VideoJuegoDAO.juegosActualizar(campoDescripcion.getText(),campoGenero.getText(),campoPlataforma.getText(),precio,campoNombre.getText());

            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        }

    }



}