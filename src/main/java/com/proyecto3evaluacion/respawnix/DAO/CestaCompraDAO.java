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
    private static final String SQL_UPDATE_NAME ="UPDATE cestaCompra SET nombreVideoJuego = ? WHERE nombreVideoJuego = ?";


    public static void actualizarNombre(String nombre,String nuevoNombre){
        Connection con = ConnectionDB.getConnection();
        try {
            PreparedStatement psUpdate = con.prepareStatement(SQL_UPDATE_NAME);
            psUpdate.setString(1, nuevoNombre);
            psUpdate.setString(2, nombre);
            psUpdate.executeUpdate();

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



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

    public static void insertarCesta(String nombreUsuario, String nombreVideoJuego, int cantidad){
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, nombreVideoJuego);
            stmt.setInt(3, cantidad);

            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
