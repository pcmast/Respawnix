package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import com.proyecto3evaluacion.respawnix.model.VideoJuegoComprado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VideoJuegoCompradoDAO {

    private final static String SQL_ALL = "select * from videojuegoscomprados";
    private final static String SQL_INSERT = "INSERT INTO videojuegoscomprados (emailUsuario, NombreJuego, precioJuego, precioTotal, cantidad) VALUES (?, ?, ?, ?, ?)";

    /**
     * Metodo que coge de la base de datos todos los videojuegos comprados
     * @return devuelve la lista de los videojuegos comprados
     */
    public static List<VideoJuegoComprado> todosLosJuegosComprados() {
        List<VideoJuegoComprado> juegos = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                VideoJuegoComprado videoJuegoComprado = new VideoJuegoComprado();
                videoJuegoComprado.setNombreJuego(rs.getString("NombreJuego"));
                videoJuegoComprado.setPrecio(rs.getDouble("precioTotal"));
                videoJuegoComprado.setCantidad(rs.getInt("cantidad"));
                videoJuegoComprado.setEmail(rs.getString("emailUsuario"));
                juegos.add(videoJuegoComprado);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return juegos;
    }

    /**
     * Inserta en la base de datos el videojuego que a sido comprado que usuario lo ha hecho y cuantos a comprado
     * @param emailUsuario email del usuario que lo a comprado
     * @param nombreJuego nombre del juego que a comprado
     * @param precioJuego precio del juego que a comprado
     * @param precioTotal precio total de los juego por si se a comprado mas de uno
     * @param cantidad cantidad de juegos que se an comprado
     */
    public static void insertarJuegoComprado(String emailUsuario, String nombreJuego, double precioJuego, double precioTotal, int cantidad){
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, emailUsuario);
            stmt.setString(2,nombreJuego);
            stmt.setDouble(3,precioJuego);
            stmt.setDouble(4,precioTotal);
            stmt.setInt(5,cantidad);
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
