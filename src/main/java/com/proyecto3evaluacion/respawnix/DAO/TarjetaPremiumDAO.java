package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.TarjetaPremium;
import com.proyecto3evaluacion.respawnix.model.TarjetaVIP;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarjetaPremiumDAO {


    private final static String SQL_ALL = "select * from TarjetaPremium";
    private final static String SQL_INSERT = "insert into TarjetaPremium values(?,?)";
    private final static String SQL_DELETE = "DELETE FROM tarjetaPremium WHERE email = ?";


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

    public static List<TarjetaPremium> todasTarjetas() {

        List<TarjetaPremium> tarjetas = new ArrayList<>();
        Connection con = ConnectionDB.getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(SQL_ALL);
            while (rs.next()) {
                TarjetaPremium tarjeta = new TarjetaPremium();
                tarjeta.setEmail(rs.getString("email"));
                tarjetas.add(tarjeta);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tarjetas;

    }

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