package controllers;

import models.Genero;
import dao.Conexion;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GeneroController {

    public int create(Genero e) {
        int flag = 0;
        var sql = "INSERT INTO genres VALUES('" + e.getIdentificador() + "'," + e.getGenero() + ");";
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.conectar();
            Statement st = con.createStatement();
            flag = st.executeUpdate(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return flag;
    }

    public Genero search(int identificador) {
        Genero genero = new Genero();
        Conexion conexion = new Conexion();
        String sql = "SELECT id, genero FROM genres WHERE id = ?";
        try {
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, identificador);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                genero.setIdentificador(rs.getInt("id"));
                genero.setGenero(rs.getString("genero"));
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return genero;
    }

    public int update(Genero e) {
        int flag = 0;
        var sql = "UPDATE genres SET genero = '" + e.getGenero() + "' WHERE " + e.getIdentificador() + ";";
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.conectar();
            Statement stmt = (Statement) con.createStatement();
            flag = stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return flag;
    }

    public int delete(Genero e) {
        int flag = 0;
        var sql = "DELETE genres WHERE nombre = " + e.getIdentificador() + ";";
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.conectar();
            Statement stmt = (Statement) con.createStatement();
            flag = stmt.executeUpdate(sql);
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return flag;
    }

    public List<Genero> list() {
        Genero genero;
        List<Genero> generos = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM genres";
        try {
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                genero = new Genero();
                genero.setIdentificador(rs.getInt("id"));
                genero.setGenero(rs.getString("genero"));
                generos.add(genero);
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return generos;
    }
}
