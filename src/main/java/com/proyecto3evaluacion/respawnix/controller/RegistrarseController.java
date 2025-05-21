package com.proyecto3evaluacion.respawnix.controller;

import com.proyecto3evaluacion.respawnix.DAO.AdministradorDAO;
import com.proyecto3evaluacion.respawnix.DAO.UsuarioDAO;
import com.proyecto3evaluacion.respawnix.RespawnixApplication;
import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionProperties;
import com.proyecto3evaluacion.respawnix.baseDatos.XMLManager;
import com.proyecto3evaluacion.respawnix.model.ClaveWrapper;
import com.proyecto3evaluacion.respawnix.model.Usuario;
import com.proyecto3evaluacion.respawnix.utils.PasswordUtils;
import com.proyecto3evaluacion.respawnix.utils.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RegistrarseController {

    @FXML
    private ImageView logo;
    @FXML
    private Label claveErronea;
    @FXML
    private Label fechaVacia;
    @FXML
    private Label nombreVacio;
    @FXML
    private Label apellidosVacios;
    @FXML
    private Label emailVacio;
    @FXML
    private Label contrasennaVacia;
    @FXML
    private Label repetirContrasennaVacia;
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

    /**
     * Metodo que al iniciar el controlador añade una foto a una imagen en la pantalla
     */
    public void initialize(){
        File imagenURL = new File("images/MANDOLETRAS.png");
        Image image = new Image(imagenURL.toURI().toString());
        logo.setImage(image);

    }


    /**
     * Metodo que vuelve atras a la ventana de iniciar sesion
     * @param mouseEvent cuando el usuario le de a iniciar sesion
     */
    public void iniciarSesion(MouseEvent mouseEvent) {
        Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        currentStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(RespawnixApplication.class.getResource("pantallaIniciarSesion.fxml"));
        Stage stage = new Stage();
        Scene scene = null;
        try {
            File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
            Image imageIcono = new Image(imagenURLIcono.toURI().toString());
            stage.getIcons().add(imageIcono);

            scene = new Scene(fxmlLoader.load());
            stage.setTitle("Respawnix");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * cuando el usuario le de a createAccount tendra que haber introducido todos los datos si hay algun dato vacio
     * el sistema lo informara y no dejara proseguir.
     * Tambien el usuario puede elejir si ser administrador o usuario normal dependiendo si sabe la clave del administrador
     * si no la introduce bien el sistema lo notificara.
     * Si se a creado la cuenta con existo se abrira la ventana de iniciar sesion.
     * @param actionEvent si el usuario da click en crear cuenta
     */
    public void createAccount(ActionEvent actionEvent) {
        boolean crearCuenta = true;
        String email = emailAccount.getText();
        String contrasenna = password.getText();
        String repetirContrasenna = repeatPassword.getText();
        String nombreUsuario = nombre.getText();
        String apellidosUsuario = apellidos.getText();
        LocalDate fecha = fechaNacimiento.getValue();
        String clave = claveAdministrador.getText();
        int seguir = 0;
        ClaveWrapper wrapper = XMLManager.readXML(new ClaveWrapper(),"clave.xml");
        String claveXML = wrapper.getValue();

        if (email.isEmpty()){
            emailVacio.setText("Introduce un email");
        } else if (!Utilidades.validarCorreo(email)) {
            emailVacio.setText("Introduce un email valido");
        }else {
            emailVacio.setText("");
            seguir+=1;
        }

        if (contrasenna.isEmpty()){
            contrasennaVacia.setText("Introduce la contraseña");
        }else {
            contrasennaVacia.setText("");
            seguir+=1;
        }
        if (nombreUsuario.isEmpty()){
            nombreVacio.setText("Debes Introducir tu nombre");
        }else {
            nombreVacio.setText("");
            seguir+=1;
        }
        if (apellidosUsuario.isEmpty()){
            apellidosVacios.setText("Introduce tus apellidos");
        }else {
            apellidosVacios.setText("");
            seguir+=1;
        }

        if (fecha == null){
            fechaVacia.setText("Introduce tu fecha");
        }else {
            fechaVacia.setText("");
            seguir+=1;
        }
        if (repetirContrasenna.isEmpty()){
            repetirContrasennaVacia.setText("Debes repetir la contraseña");
        }
        if (clave.equals(claveXML)){
            claveErronea.setText("");
        }else if (!clave.isEmpty()){
            claveErronea.setText("Las claves no coinciden");
            seguir = 0;
        }



        if (!contrasenna.equals(repetirContrasenna)){
            contrasennaNoCoincide.setText("Las Contraseñas no Coinciden");
            seguir = 0;
        }
        if(seguir == 5){

        ArrayList<Usuario> list = (ArrayList<Usuario>) UsuarioDAO.todosUsuarios();

        for (Usuario usuario: list) {
            if (usuario.getEmail().equals(email)){
                crearCuenta = false;
            }
        }
            String contrasennaHash = PasswordUtils.hashPassword(contrasenna);

            UsuarioDAO.insertarUsuarios(nombreUsuario,apellidosUsuario,fecha,email,contrasennaHash);
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
                File imagenURLIcono = new File("images/MANDOPEQUEÑO.png");
                Image imageIcono = new Image(imagenURLIcono.toURI().toString());
                stage.getIcons().add(imageIcono);

                scene = new Scene(fxmlLoader.load());
                stage.setTitle("Respawnix");
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        }
    }
}
