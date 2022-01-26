package controllers;

import models.Estudiante;
import dao.Conexion;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EstudianteController {

    public int create(Estudiante e) {
        int flag = 0;
        var sql = "INSERT INTO students VALUES('" + e.getNombre() + "'," + e.getGenero().getIdentificador() + ","
                + e.getMateria().getIdentificador() + "," + e.getNota() + ");";
        Conexion conexion = new Conexion();
        try {
            Connection con = conexion.conectar();
            Statement st = con.createStatement();
            flag = st.executeUpdate(sql);
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return flag;
    }

    public Estudiante search(String nombre) {
        Estudiante estudiante = new Estudiante();
        Conexion conexion = new Conexion();
        String sql = "SELECT nombre, genero, materia, nota FROM students WHERE nombre = ?";
        try {
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, nombre);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setMateria(new MateriaController().search(rs.getInt("materia")));
                estudiante.setGenero(new GeneroController().search(rs.getInt("genero")));
                estudiante.setNota(rs.getDouble("nota"));
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return estudiante;
    }

    public int update(Estudiante e) {
        int flag = 0;
        var sql = "UPDATE students SET" + "genero = " + e.getGenero().getIdentificador() + "," + "materia = "
                + e.getMateria().getIdentificador() + "," + "nota = " + e.getNota() + " " + "WHERE nombre = '"
                + e.getNombre() + "';";
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

    public int delete(Estudiante e, String type) {
        int flag = 0;
        var sql = "";
        if (type == "nombre")
            sql = "DELETE FROM students WHERE nombre = '" + e.getNombre() + "';";
        else if (type == "materia")
            sql = "DELETE FROM students WHERE materia = " + e.getMateria() + ";";
        else if (type == "ambos")
            sql = "DELETE FROM students WHERE nombre = '" + e.getNombre() + "' OR materia = " + e.getMateria() + ";";

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

    public List<Estudiante> list() {
        Estudiante estudiante;
        List<Estudiante> estudiantes = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "SELECT * FROM students";
        try {
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                estudiante = new Estudiante();
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setMateria(new MateriaController().search(rs.getInt("materia")));
                estudiante.setGenero(new GeneroController().search(rs.getInt("genero")));
                estudiante.setNota(rs.getDouble("nota"));
                estudiantes.add(estudiante);
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return estudiantes;
    }

    public List<Estudiante> listByEstudianteAndMateria(String nombre_estudiante, String id_materia) {
        Estudiante estudiante;
        List<Estudiante> estudiantes = new ArrayList<>();
        Conexion conexion = new Conexion();
        String sql = "";
        if (nombre_estudiante == null && id_materia.trim() == "") {
            sql = "SELECT * FROM students";
        } else if (nombre_estudiante != null && id_materia.trim() == "") {
            sql = "SELECT * FROM students WHERE nombre = '" + nombre_estudiante + "';";
        } else if (nombre_estudiante == null && id_materia.trim() != "") {
            sql = "SELECT * FROM students WHERE materia = " + id_materia + ";";
        } else {
            sql = "SELECT * FROM students WHERE materia = '" + id_materia + "' OR nombre = '" + nombre_estudiante
                    + "';";
        }

        try {
            Connection con = conexion.conectar();
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                estudiante = new Estudiante();
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setMateria(new MateriaController().search(rs.getInt("materia")));
                estudiante.setGenero(new GeneroController().search(rs.getInt("genero")));
                estudiante.setNota(rs.getDouble("nota"));
                estudiantes.add(estudiante);
            }
            st.close();
            rs.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return estudiantes;
    }
}
