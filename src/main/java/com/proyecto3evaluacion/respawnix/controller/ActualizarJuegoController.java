package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.CestaCompraDAO;
import com.proyecto3evaluacion.respawnix.DAO.VideoJuegoDAO;
import com.proyecto3evaluacion.respawnix.model.CestaCompra;
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
     * Al iniciar esta pantalla desde menuJuegosController se pasa el videojuego que a seleccionado el usuario para actualizarlo
     * y que salgan los datos para que el los modifique
     * @param videoJuego videojuego seleccionado
     */
    public void setVideoJuego(VideoJuego videoJuego) {
        campoNombre.setText(videoJuego.getNombre());
        campoDescripcion.setText(videoJuego.getDescripcion());
        campoGenero.setText(videoJuego.getGenero());
        campoPlataforma.setText(videoJuego.getPlataforma());
        campoPrecio.setText(String.valueOf(videoJuego.getPrecio()));
    }

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
            if (videoJuego.getNombre().equals(campoNombre.getText())){
            videoJuegoAntiguo = videoJuego;
            }
        }
        if (campoNombre.getText().isEmpty()) {
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

            VideoJuego videoJuego = new VideoJuego();
            videoJuego.setNombre(campoNombre.getText());
            videoJuego.setDescripcion(campoDescripcion.getText());
            videoJuego.setPlataforma(campoPlataforma.getText());
            videoJuego.setPrecio(Double.valueOf(campoPrecio.getText()));
            videoJuego.setGenero(campoGenero.getText());

           UsuarioActualController.getInstance().getUsuario().getCesta().actualizar(videoJuego);

            VideoJuegoDAO.juegosActualizar(campoDescripcion.getText(),campoGenero.getText(),campoPlataforma.getText(),precio,campoNombre.getText());

            ((Stage) ((Node) actionEvent.getSource()).getScene().getWindow()).close();
        }

    }



}