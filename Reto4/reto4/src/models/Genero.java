package models;

import java.util.Objects;

public class Genero {
    
    private int identificador;
    private String genero;


    public Genero() {
    }

    public Genero(int identificador, String genero) {
        this.identificador = identificador;
        this.genero = genero;
    }

    public int getIdentificador() {
        return this.identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Genero dentificador(int identificador) {
        setIdentificador(identificador);
        return this;
    }

    public Genero genero(String genero) {
        setGenero(genero);
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identificador, genero);
    }

    @Override
    public String toString() {
        return getIdentificador() + " - " + getGenero();
    }

}
