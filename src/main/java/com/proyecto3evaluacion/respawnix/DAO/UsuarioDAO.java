package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UsuarioDAO {
    private final static String SQL_ALL = "select * from usuario";
    private final static String SQL_ANNADIR = "INSERT INTO usuario (Nombre, apellidos, fechaNacimiento, email, password) VALUES (?, ?, ?, ?, ?)";;


    /**
     * Metodo que coge de la base de datos todos los usuarios
     * @return devuelve la lista de los usuarios
     */
    public static List<Usuario> todosUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setPassword(rs.getString("password"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return usuarios;
    }

    /**
     * Metodo que inserta un usuario en la base de datos mediante su informacion
     * @param nombre nombre del usuario introducido
     * @param apellidos apellidos del usuario introducido
     * @param fechaNacimiento fecha de nacimiento del usuario introducida
     * @param email email del usuario introducido
     * @param password la contraseña del usuario introducida
     */
    public static void insertarUsuarios(String nombre, String apellidos, LocalDate fechaNacimiento, String email, String password){
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_ANNADIR);
            stmt.setString(1, nombre);
            stmt.setString(2, apellidos);
            stmt.setDate(3, Date.valueOf(fechaNacimiento));
            stmt.setString(4, email);
            stmt.setString(5, password);
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}
