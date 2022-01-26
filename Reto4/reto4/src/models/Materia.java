package models;

import java.util.Objects;

public class Materia {
    
    private int identificador;
    private String nombre;


    public Materia() {
    }

    public Materia(int identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Materia identificador(int identificador) {
        setIdentificador(identificador);
        return this;
    }

    public Materia nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Materia)) {
            return false;
        }
        Materia materia = (Materia) o;
        return identificador == materia.identificador && nombre == materia.nombre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, nombre);
    }

    @Override
    public String toString() {
        return getIdentificador() + " - " + getNombre();
    }

}
