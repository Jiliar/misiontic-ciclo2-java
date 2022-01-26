package models;

import java.util.Objects;

public class Estudiante {

    private String nombre;
    private Genero genero;
    private Materia materia;
    private double nota;
    

    public Estudiante() {
    }

    public Estudiante(String nombre, Genero genero, Materia materia, double nota) {
        this.nombre = nombre;
        this.genero = genero;
        this.materia = materia;
        this.nota = nota;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Materia getMateria() {
        return this.materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public double getNota() {
        return this.nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public Estudiante nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Estudiante genero(Genero genero) {
        setGenero(genero);
        return this;
    }

    public Estudiante materia(Materia materia) {
        setMateria(materia);
        return this;
    }

    public Estudiante nota(double nota) {
        setNota(nota);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Estudiante)) {
            return false;
        }
        Estudiante estudiante = (Estudiante) o;
        return Objects.equals(nombre, estudiante.nombre) && Objects.equals(genero, estudiante.genero) && Objects.equals(materia, estudiante.materia) && nota == estudiante.nota;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, genero, materia, nota);
    }

    @Override
    public String toString() {
        return "{" +
            " nombre='" + getNombre() + "'" +
            ", genero='" + getGenero() + "'" +
            ", materia='" + getMateria() + "'" +
            ", nota='" + getNota() + "'" +
            "}";
    }

}
