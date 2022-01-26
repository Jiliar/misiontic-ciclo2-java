package controllers;

import models.Materia;
import dao.Conexion;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MateriaController {

    public int create(Materia e) {
        int flag = 0;
        var sql = "INSERT INTO subjects VALUES('" + e.getIdentificador() + "'," + e.getNombre() + ");";
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

    public Materia search(int identificador) {
        Materia materia = new Materia();
        Conexion conexion = new Conexion();
        String sql = "SELECT id, nombre FROM subjects WHERE id = ?";
        try {
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, identificador);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                materia.setIdentificador(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return materia;
    }

    public int update(Materia e) {
        int flag = 0;
        var sql = "UPDATE subjects SET nombre = '" + e.getNombre() + "' WHERE " + e.getIdentificador() + ";";
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

    public int delete(Materia e) {
        int flag = 0;
        var sql = "DELETE subjects WHERE nombre = " + e.getIdentificador() + ";";
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

    public List<Materia> list() {
        Materia materia;
        List<Materia> materias = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM subjects;";
        try {
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                materia = new Materia();
                materia.setIdentificador(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
                materias.add(materia);
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return materias;
    }
}
