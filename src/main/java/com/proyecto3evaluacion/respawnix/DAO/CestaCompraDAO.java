package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class CestaCompraDAO {
    private static final String SQL_INSERT = "INSERT INTO cestaCompra (emailUsuario, nombreVideojuego, cantidad) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_BY_EMAILUSER = "SELECT nombreVideojuego, cantidad FROM cestaCompra WHERE emailUsuario = ?";
    private static final String SQL_UPDATE = "UPDATE cestaCompra SET cantidad = cantidad + ? WHERE emailUsuario = ? AND nombreVideojuego = ?";
    private static final String SQL_UPDATE_DELETE = "UPDATE cestaCompra SET cantidad = ? WHERE emailUsuario = ? AND nombreVideojuego = ?";
    private static final String SQL_DELETE = "DELETE FROM cestaCompra WHERE emailUsuario = ? AND nombreVideojuego = ?";
    private static final String SQL_UPDATE_NAME ="UPDATE cestaCompra SET nombreVideoJuego = ?";

    /**
     * Metodo que actualiza en la base de datos un nombre por uno nuevo usando una consulta UPDATE
     * @param nuevoNombre nombre nuevo
     */
    public static void actualizarNombre(String nuevoNombre){
        Connection con = ConnectionDB.getConnection();
        try {
            PreparedStatement psUpdate = con.prepareStatement(SQL_UPDATE_NAME);
            psUpdate.setString(1, nuevoNombre);
            psUpdate.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Metodo que actualiza la lista de la compra con el email del usuario el nombre del videojuego y la cantidad a compar
     * @param emailUsuario email usuario
     * @param nombreVideojuego nombre del videojuego a comprar
     * @param cantidad cantidad de videojuegos a comprar
     */
    public static void actualizarEnLaLista(String emailUsuario, String nombreVideojuego, int cantidad) {
        Connection con = ConnectionDB.getConnection();
        try {
            PreparedStatement psUpdate = con.prepareStatement(SQL_UPDATE);
            psUpdate.setInt(1, cantidad);
            psUpdate.setString(2, emailUsuario);
            psUpdate.setString(3, nombreVideojuego);

            int filasAfectadas = psUpdate.executeUpdate();

            if (filasAfectadas == 0) {
                insertarCesta(emailUsuario,nombreVideojuego,cantidad);
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que elimina de la cesta de la compra un videojuego  o actualiza la cesta
     * @param emailUsuario email del usuario
     * @param nombreJuego nombre del videojuego 
     * @param cantidad cantidad de videojuego a comprar
     */
    public static void eliminarCestaOActualizar(String emailUsuario, String nombreJuego, int cantidad){
        Connection con = ConnectionDB.getConnection();
        if (cantidad != 0){

        try {
        PreparedStatement ps = con.prepareStatement(SQL_UPDATE_DELETE);
        ps.setInt(1,cantidad);
        ps.setString(2,emailUsuario);
        ps.setString(3,nombreJuego);
        ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        }else {
            eliminarCesta(emailUsuario,nombreJuego);
        }
    }

    /**
     * Metodo que elimina dela cesta de la compra un videojuego con el email del usuario y el nombre del videojuego
     * @param emailUsuario email del usuario
     * @param nombreJuego nombre del videojuego a eliminar
     */
    public static void eliminarCesta(String emailUsuario, String nombreJuego){
        Connection con = ConnectionDB.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQL_DELETE);
            ps.setString(1,emailUsuario);
            ps.setString(2,nombreJuego);
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }


    /**
     * Metodo que muestra toda la cesta de un usuario dependiendo del email
     * @param emailUsuario email del usuario
     * @return devuelve la lista de los videojuegos con la cantidad que hay en la cesta
     */
    public static Map<String, Integer> cesta(String emailUsuario) {
        HashMap<String, Integer> cesta = new HashMap<>();

        Connection con = ConnectionDB.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQL_SELECT_BY_EMAILUSER);
            ps.setString(1, emailUsuario);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombreVideojuego = rs.getString("nombreVideojuego");
                int cantidad = rs.getInt("cantidad");
                cesta.put(nombreVideojuego, cantidad);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cesta;
    }


    /**
     * Metodo que inserta en la base de datos un videojuego y la cantidad de videojuegos a comprar dependiendo del nombre
     * del usuario actual
     * @param emailUsuario email del usuario actual
     * @param nombreVideoJuego nombre videojuego
     * @param cantidad cantidad de juegos a comprar
     */
    public static void insertarCesta(String emailUsuario, String nombreVideoJuego, int cantidad){
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, emailUsuario);
            stmt.setString(2, nombreVideoJuego);
            stmt.setInt(3, cantidad);

            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
