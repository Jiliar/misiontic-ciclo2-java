import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class SchoolGradingSystem extends GradingSystem{
    
    public List<Student> loadData(int cant, String[] data) {

        List<String> auxiliar1 = new LinkedList<>();
        List<String> auxiliar2 = new LinkedList<>();
        List<Student> answer = new LinkedList<>();

        for (var i = 0; i < data.length; i++) {
            String[] aux = data[i].trim().split(" ");
            auxiliar1.add(aux[0]);
            auxiliar2.add(aux[2]);
        }

        List<String> estudiantes = auxiliar1.stream().distinct().collect(Collectors.toList());
        List<String> asignaturas = auxiliar2.stream().distinct().collect(Collectors.toList());
        

        for(var estudiante: estudiantes){
            var genero = "";
            
            for (var i = 0; i < data.length; i++) {
                String[] aux = data[i].trim().split(" ");
                if(aux[0].equals(estudiante)){
                    genero = aux[1];
                    break;
                }
            }
            answer.add(new Student(estudiante, genero, asignaturas, null));
        }
 
        List<Student> students = new LinkedList<>();
        for(Student estudiante: answer){
            List<String> scores = new LinkedList<>();
            for (var i = 0; i < data.length; i++) {
                String[] aux = data[i].trim().split(" ");
                if(estudiante.getNombre().equals(aux[0])){
                    for (var asignatura: estudiante.getAsignaturas()) {
                        if(asignatura.equals(aux[2])){
                            scores.add(aux[3]);
                        }
                    }
                }
            }
            estudiante.setNotas(scores);
            students.add(estudiante);
        }
        return students;
    }


    
    
}
