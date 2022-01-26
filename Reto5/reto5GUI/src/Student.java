import java.util.Objects;
import java.util.List;

public class Student {

    private String nombre;
    private String genero;
    private List<String> asignaturas;
    private List<String> notas;

    public Student() {
    }

    public Student(String nombre, String genero, List<String> asignaturas, List<String> notas) {
        this.nombre = nombre;
        this.genero = genero;
        this.asignaturas = asignaturas;
        this.notas = notas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return this.genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public List<String> getAsignaturas() {
        return this.asignaturas;
    }

    public void setAsignaturas(List<String> asignaturas) {
        this.asignaturas = asignaturas;
    }

    public List<String> getNotas() {
        return this.notas;
    }

    public void setNotas(List<String> notas) {
        this.notas = notas;
    }

    public Student nombre(String nombre) {
        setNombre(nombre);
        return this;
    }

    public Student genero(String genero) {
        setGenero(genero);
        return this;
    }

    public Student asignaturas(List<String> asignaturas) {
        setAsignaturas(asignaturas);
        return this;
    }

    public Student notas(List<String> notas) {
        setNotas(notas);
        return this;
    }

    @Override
    public String toString() {
        return "{" + " nombre='" + getNombre() + "'" + ", genero='" + getGenero() + "'" + ", asignaturas='"
                + getAsignaturas() + "'" + ", notas='" + getNotas() + "'" + "}\n";
    }

}