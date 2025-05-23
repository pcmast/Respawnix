package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.Tarjeta;
import com.proyecto3evaluacion.respawnix.model.TarjetaVIP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarjetaVIPDAO {

    private final static String SQL_ALL = "select * from TarjetaVIP";
    private final static String SQL_INSERT = "insert into TarjetaVIP values(?,?)";
    private final static String SQL_DELETE = "DELETE FROM tarjetaVIP WHERE email = ?";

    /**
     * Metodo que elimina de la base de datos una tarjeta del usuario que ya a canjeado
     * @param email email del usuario actual
     */
    public static void tarjetaUsada(String email){
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_DELETE);
            stmt.setString(1, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Metodo que coge todas las tarjetas de la base de datos
     * @return devuelve todas las tarjetas de la base de datos
     */
    public static List<TarjetaVIP> todasTarjetas(){

        List<TarjetaVIP> tarjetas = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                TarjetaVIP tarjeta = new TarjetaVIP();
                tarjeta.getUsuario().setEmail(rs.getString("email"));
                tarjetas.add(tarjeta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tarjetas;

    }

    /**
     * Metodo que inserta en la base de datos el email del usuario que a comprado una tarjeta y el porcentaje del descuento de la misma
     * @param email email del usuario actual
     */
    public static void insertarTarjeta(String email, int porcentaje) {
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, email);
            stmt.setInt(2, porcentaje);
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





}
