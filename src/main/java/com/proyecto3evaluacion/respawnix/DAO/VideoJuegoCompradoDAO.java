package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import com.proyecto3evaluacion.respawnix.model.VideoJuegoComprado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoJuegoCompradoDAO {

    private final static String SQL_ALL = "select * from videojuegocomprados";
    private final static String SQL_INSERT = "INSERT INTO videojuego (emailUsuario, NombreJuego) VALUES (?, ?)";


    public static List<VideoJuegoComprado> todosLosJuegosComprados() {
        List<VideoJuegoComprado> juegos = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                VideoJuegoComprado videoJuegoComprado = new VideoJuegoComprado();
                videoJuegoComprado.setNombreJuego(rs.getString("NombreJuego"));
                videoJuegoComprado.setEmail("emailUsuario");
                juegos.add(videoJuegoComprado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return juegos;
    }

    public static void insertarJuegoComprado(String nombre, String email){
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, nombre);
            stmt.setString(2,email);
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
