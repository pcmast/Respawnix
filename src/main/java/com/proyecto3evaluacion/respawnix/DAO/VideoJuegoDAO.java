package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.Usuario;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VideoJuegoDAO {

    private final static String SQL_ALL = "select * from videojuego";
    private final static String SQL_INSERT = "INSERT INTO videojuego (nombre, descripcion, genero, plataforma, precio, Imagen) VALUES (?, ?, ?, ?, ?, ?)";
    private final static String SQL_DELETE = "DELETE FROM videojuego WHERE nombre = ? AND descripcion = ? AND genero = ? AND plataforma = ? AND precio = ? AND imagen = ?";
    private final static String SQL_NAME = "SELECT * FROM videojuego WHERE nombre LIKE ?";
    private final static String SQL_UPDATE = "UPDATE videoJuego SET nombre = ?, descripcion = ?, genero = ?, plataforma = ?, precio = ? WHERE nombre = ?";


    /**
     * Metodo que actualiza en la base de datos un videojuego con los nuevos datos
     * @param descripcion descripcion nueva del juego
     * @param genero genero actualizado del juego
     * @param plataforma plataforma del juego actualizada
     * @param precio precio del juego actualizado
     * @param nombre nombre del juego actualizado
     */
    public static void juegosActualizar(String descripcion, String genero, String plataforma, double precio, String nombre) {
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_UPDATE);
            stmt.setString(1,nombre);
            stmt.setString(2, descripcion);
            stmt.setString(3, genero);
            stmt.setString(4, plataforma);
            stmt.setDouble(5, precio);
            stmt.setString(6, nombre);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * Metodo que coge todos los juegos dependiendo del nombre que se le introduzca
     * @param nombre nombre del juego
     * @return devuelve la lista de los videojuegos con ese nombre o similar
     */
    public static List<VideoJuego> juegosPorNombre(String nombre) {
        List<VideoJuego> juegos = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        try {
            PreparedStatement stmt = con.prepareStatement(SQL_NAME);
            stmt.setString(1, "%" + nombre + "%");
            ResultSet rs = stmt.executeQuery();
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

    /**
     * Metodo que coge de la base de datos todos los videojuegos
     * @return devuelve la lista de todos los juegos
     */
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


    /**
     * Metodo que inserta un videojuego en la base de datos
     * @param nombre nombre del juego a introducir
     * @param descripcion descripcion del juego a introducir
     * @param genero genero del juego a introducir
     * @param plataforma plataforma del juego a introducir
     * @param precio precio del juego a introducir
     * @param imagen ruta de la imagen a introducir
     */
    public static void insertarJuego(String nombre, String descripcion, String genero, String plataforma, Double precio, String imagen) {
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setString(3, genero);
            stmt.setString(4, plataforma);
            stmt.setDouble(5, precio);
            stmt.setString(6, imagen);
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Metodo que eliminar de la base de datos un videojuego pasando todos los datos del mismo un objeto entero
     * @param videoJuego el objeto videojuego que se va a eliminar
     */
    public static void eliminarJuego(VideoJuego videoJuego) {
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setString(1, videoJuego.getNombre());
            stmt.setString(2, videoJuego.getDescripcion());
            stmt.setString(3, videoJuego.getGenero());
            stmt.setString(4, videoJuego.getPlataforma());
            stmt.setDouble(5, videoJuego.getPrecio());
            stmt.setString(6, videoJuego.getImage());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}