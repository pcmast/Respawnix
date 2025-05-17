package com.proyecto3evaluacion.respawnix.DAO;

import com.proyecto3evaluacion.respawnix.baseDatos.ConnectionDB;
import com.proyecto3evaluacion.respawnix.model.Tarjeta;
import com.proyecto3evaluacion.respawnix.model.VideoJuego;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TarjetaDAO {


    private final static String SQL_ALL = "select * from Tarjeta";
    private final static String SQL_INSERT = "insert into Tarjeta (nombre,email) values(?,?)";
    private final static String SQL_DELETE = "DELETE FROM tarjeta WHERE email = ?";


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


    public static List<Tarjeta> todas(){
            List<Tarjeta> tarjetas = new ArrayList<>();
            Connection con = ConnectionDB.getConnection();
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL_ALL);
                while (rs.next()) {
                    Tarjeta tarjeta = new Tarjeta();
                    tarjeta.setNombre(rs.getString("nombre"));
                    tarjeta.setEmail(rs.getString("email"));
                    tarjetas.add(tarjeta);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return tarjetas;

    }
    public static void insertarTarjeta(String nombre, String email) {
        Connection con = ConnectionDB.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(SQL_INSERT);
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}