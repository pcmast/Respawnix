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

    public static List<Administrador> todosAdministradores(){
        List<Administrador> list = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                Administrador usuario = new Administrador();
                usuario.setEmailUsuario(rs.getString("emailUsuario"));
                list.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return list;
    }
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