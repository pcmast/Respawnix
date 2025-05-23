package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.Administrador;
import com.proyecto3evaluacion.respawnix.model.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {

    private final static String SQL_ALL = "select * from administrador";
    private final static String SQL_ANNADIR = "INSERT INTO administrador (emailUsuario) VALUES (?)";

    /**
     * Metodo que coge de la base de datos todos los emails de los administradores
     * @return devuelve la lista de los administradores
     */
    public static List<Administrador> todosAdministradores(){
        List<Administrador> list = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                Administrador usuario = new Administrador();
                Usuario usuario1 = new Usuario();
                usuario1.setEmail(rs.getString("emailUsuario"));
                usuario.setUsuario(usuario1);
                list.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return list;
    }

    /**
     * Inserta en la base de datos el email de un administrador
     * @param email el email a añadir
     */
    public static void insertarAdministrador(String email){
        Connection con = ConnectionDB.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(SQL_ANNADIR);
            stmt.setString(1, email);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



}