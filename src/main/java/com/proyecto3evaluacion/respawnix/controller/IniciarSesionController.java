package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.AdministradorDAO;
import com.proyecto3evaluacion.respawnix.DAO.UsuarioDAO;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.model.Administrador;
import com.proyecto3evaluacion.respawnix.model.Usuario;
import com.proyecto3evaluacion.respawnix.utils.PasswordUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class IniciarSesionController {
    public ImageView logo;
    @FXML
    private Label emailContraseIncorrecto;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordField;

    /**
     * Metodo que al inciar la clase coge una imagen de la carpeta images del programa y la pone en la pantalla
     */
    public void initialize(){
        File imagenURL = new File("images/LOGOSINFONDO.png");
        Image image = new Image(imagenURL.toURI().toString());
        logo.setImage(image);
    }

    /**
     * Metodo que busca en la base de datos el usuario introducido y si existe en la base de datos llama dependiendo
     * de que usuario seas usuario normal o administrador al metodo que inicia sesion como esos dos usuarios
     * @param event Recibe como parametro el evento hecho en la interfaz grafica que luego se le pasara a los metodos de
     * iniciar sesion como administrador o como usuario normal
     */
    public void iniciarSesion(ActionEvent event) {
        String email = emailTextField.getText();
        String password = passwordField.getText();
        boolean existe = false;

        ArrayList<Usuario> list = (ArrayList<Usuario>) UsuarioDAO.todosUsuarios();
        ArrayList<Administrador> administradors = (ArrayList<Administrador>) AdministradorDAO.todosAdministradores();

        for (Usuario usuario : list) {


            if (usuario.getEmail().equals(email) && PasswordUtils.verificarPassword(password,usuario.getPassword())) {
                UsuarioActualController usuarioActualController = UsuarioActualController.getInstance();
                usuarioActualController.setUsuario(usuario);
                existe = true;
                for (Administrador administrador : administradors) {
                    if (administrador.getEmailUsuario().equals(email)) {
                        iniciarSesionExitosoAdministrador(event);
                        return;
                    }
                }
            }
        }
        if (existe){
            iniciarSesionDeCliente(event);
        }else {
        emailContraseIncorrecto.setText("Contraseña o email incorrecto");
        }


    }

    /**
     * Metodo que carga la pantalla de la interfaz del usuario normal con los datos ya confirmados antes.
     * se coge el stage del evento anterior
     * @param event
     */
    private void iniciarSesionDeCliente(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaInterfazUsuario.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = null;
            scene = new Scene(loader.load());
            File imagenURL = new File("images/MANDOPEQUEÑO.png");
            Image image = new Image(imagenURL.toURI().toString());
            stage.getIcons().add(image);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metodo que carga la pantalla de un usuario administrador con los datos ya confirmados antes
     * coge el stage del evento anterior
     * @param event
     */
    private void iniciarSesionExitosoAdministrador(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaMenuVideoJuegos.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = null;
            scene = new Scene(loader.load());
            File imagenURL = new File("images/MANDOPEQUEÑO.png");
            Image image = new Image(imagenURL.toURI().toString());
            stage.getIcons().add(image);
            stage.setScene(scene);
            stage.show();
            stage.centerOnScreen();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que carga otra ventana para introducir los datos del usuario si no tienes una cuenta
     * @param mouseEvent si el usuario a hecho click en registrarse
     */
    public void Registrarse(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaRegistrarse.fxml"));
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            currentStage.close();
            Scene scene = null;
            scene = new Scene(loader.load());
            Stage stage = new Stage();
            File imagenURL = new File("images/MANDOPEQUEÑO.png");
            Image image = new Image(imagenURL.toURI().toString());
            stage.getIcons().add(image);
            stage.setTitle("Respawnix");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}