package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.Usuario;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.scene.image.Image;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VideoJuegoDAO {

    private final static String SQL_ALL = "select * from videojuego";
    private final static String SQL_INSERT = "INSERT INTO videojuego (nombre, descripcion, genero, plataforma, precio, Imagen) VALUES (?, ?, ?, ?, ?, ?)";


    public static List<VideoJuego> todosLosJuegos() {
        List<VideoJuego> juegos = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                VideoJuego videoJuego = new VideoJuego();
                videoJuego.setNombre(rs.getString("nombre"));
                videoJuego.setDescripcion(rs.getString("descripcion"));
                videoJuego.setGenero(rs.getString("genero"));
                videoJuego.setPlataforma(rs.getString("plataforma"));
                videoJuego.setPrecio(rs.getDouble("precio"));
                videoJuego.setImagen(rs.getString("Imagen"));
                juegos.add(videoJuego);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return juegos;
    }

    public static void insertarJuego(String nombre, String descripcion, String genero, String plataforma, Double precio, String imagen){
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setString(3, genero);
            stmt.setString(4, plataforma);
            stmt.setDouble(5, precio);
            stmt.setString(6,imagen);
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }




}