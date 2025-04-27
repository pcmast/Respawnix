package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.AdministradorDAO;
import com.proyecto3evaluacion.respawnix.DAO.UsuarioDAO;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.baseDatos.XMLManager;
import com.proyecto3evaluacion.respawnix.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RegistrarseController {


    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private DatePicker fechaNacimiento;
    @FXML
    private Label contrasennaNoCoincide;
    @FXML
    private Label correoExistente;
    @FXML
    private TextField emailAccount;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField repeatPassword;
    @FXML
    private TextField claveAdministrador;


    public void iniciarSesion(MouseEvent mouseEvent) {
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaIniciarSesion.fxml"));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 390, 340);
            stage.setTitle("Iniciar Sesion");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void createAccount(ActionEvent actionEvent) {
        boolean crearCuenta = true;
        String email = emailAccount.getText();
        String contrasenna = password.getText();
        String repetirContrasenna = repeatPassword.getText();
        String nombreUsuario = nombre.getText();
        String apellidosUsuario = apellidos.getText();
        LocalDate fecha = fechaNacimiento.getValue();
        String clave = claveAdministrador.getText();

        if (!contrasenna.equals(repetirContrasenna)){
            contrasennaNoCoincide.setText("Las Contraseñas no Coinciden");
        }

        ArrayList<Usuario> list = (ArrayList<Usuario>) UsuarioDAO.todosUsuarios();

        for (Usuario usuario: list) {
            if (usuario.getEmail().equals(email)){
                crearCuenta = false;
            }
        }
        UsuarioDAO.insertarUsuarios(nombreUsuario,apellidosUsuario,fecha,email,contrasenna);
        String claveXML = XMLManager.readXML(clave,"clave.xml");
        if (clave.equals(claveXML)){
            AdministradorDAO.insertarAdministrador(email);
        }

        if (!crearCuenta){
            correoExistente.setText("Correo ya existe en el sistema");
        }else {
            correoExistente.setText("");
        }
        if (crearCuenta){
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.close();
            FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaIniciarSesion.fxml"));
            Stage stage = new Stage();
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load(), 390, 340);
                stage.setTitle("Iniciar Sesion");
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
