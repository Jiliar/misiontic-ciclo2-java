
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class GradingSystem {

    // varianza de las calificaciones para todo el grupo
    public double stat1(List<Double> universe) {
        var avg = this.getAverage(universe);
        var acum = 0.0;
        for (var aux : universe) {
            acum += Math.pow((aux - avg), 2);
        }
        BigDecimal bd = new BigDecimal(acum / universe.size()).setScale(2, RoundingMode.HALF_UP);
        double val2 = bd.doubleValue();
        return val2;
    }

    // exámenes tienen una calificación Sobresaliente
    public int stat2(List<List<Double>> universe) {

        HashMap<String, List<Double>> scores = new HashMap<String, List<Double>>();

        List<Double> excelente = new LinkedList<Double>();
        List<Double> sobresaliente = new LinkedList<Double>();
        List<Double> regular = new LinkedList<Double>();
        List<Double> insuficiente = new LinkedList<Double>();
        List<Double> deficiente = new LinkedList<Double>();

        for (List<Double> aux1 : universe) {
            var score = aux1.get(aux1.size() - 1);
            if (score >= 0 && score <= 1) {
                deficiente.add(score);
            } else if (score > 1 && score <= 2.5) {
                insuficiente.add(score);
            } else if (score > 2.5 && score <= 3.5) {
                regular.add(score);
            } else if (score > 3.5 && score <= 4.5) {
                sobresaliente.add(score);
            } else {
                excelente.add(score);
            }
        }

        scores.put("Excelente", excelente);
        scores.put("Sobresaliente", sobresaliente);
        scores.put("Regular", regular);
        scores.put("Insuficiente", insuficiente);
        scores.put("Deficiente", deficiente);

        return scores.get("Sobresaliente").size();
    }

    // materia con el peor desempeño promedio para el género masculino
    public String stat3(List<Double> courses, List<Double> scores) {
        String ans = "";
        Set<Double> set = new HashSet<>(courses);
        HashMap<Double, List<Double>> scores_course = new HashMap<Double, List<Double>>();
        for (Double aux1 : set) {
            var pos = 0;
            List<Double> data = new LinkedList<Double>();
            for (Double aux2 : courses) {
                if (Double.compare(aux1, aux2) == 0) {
                    data.add(scores.get(pos));
                }
                pos++;
            }
            scores_course.put(aux1, data);
        }

        HashMap<Double, Double> avg_course = new HashMap<Double, Double>();
        for (Double aux1 : set) {
            var avg = 0.0;
            var cantidad = 0;
            for (Double aux2 : scores_course.get(aux1)) {
                avg += aux2;
                cantidad++;
            }
            avg = avg / cantidad;
            avg_course.put(aux1, avg);
        }

        var course = 0.0;
        var min = 999999999999.9;
        for (Map.Entry<Double, Double> entry : avg_course.entrySet()) {
            Double key = entry.getKey();
            Double value = entry.getValue();
            if (value < min) {
                course = key;
                min = value;
            }
        }

        ans = this.getCourseName(course);

        return ans;
    }

    // estudiante con el mejor desempeño para la materia biología
    public String stat4(List<Double> students, List<Double> scores) {
        String ans = "";
        var max = 0.0;
        var pos = 0;
        var cuenta = 0;
        for (var aux : scores) {
            if (aux > max) {
                max = aux;
                pos = cuenta;
            }
            cuenta++;
        }
        var student = students.get(pos);

        return getStudentName(student);
    }

    public double getAverage(List<Double> universe) {
        var avg = 0.0;
        var acum = 0.0;
        for (var aux : universe) {
            acum += aux;
        }
        avg = acum / universe.size();
        return avg;
    }

    public String getCourseName(double course) {
        String name = "";
        switch (String.valueOf(course)) {
            case "1.0":
                name = "biologia";
                break;
            case "2.0":
                name = "geografia";
                break;
            case "3.0":
                name = "matematicas";
                break;
        }
        return name;
    }

    public String getStudentName(double course) {
        String name = "";
        switch (String.valueOf(course)) {
            case "1.0":
                name = "armando";
                break;
            case "2.0":
                name = "nicolas";
                break;
            case "3.0":
                name = "daniel";
                break;
            case "4.0":
                name = "maria";
                break;
            case "5.0":
                name = "marcela";
                break;
            case "6.0":
                name = "alexandra";
                break;
        }
        return name;
    }

    public HashMap<Double, List<Double>> getScoreByCriteria(String what, List<Double> criteria,
            List<List<Double>> universe, String objetive) {
        HashMap<Double, List<Double>> scores = new HashMap<Double, List<Double>>();
        int index = getComparison(what);
        int obj = getComparison(objetive);
        for (Double aux1 : criteria) {
            List<Double> data = new LinkedList<Double>();
            for (List<Double> aux2 : universe) {
                if (Double.compare(aux1, aux2.get(index)) == 0) {
                    data.add(aux2.get(obj));
                }
            }
            scores.put(aux1, data);
        }
        return scores;
    }

    public List<Double> getRawData(List<List<Double>> entry, String what) {
        int compare = getComparison(what);
        List<Double> data = new LinkedList<Double>();
        for (List<Double> value1 : entry) {
            var pos = 0;
            for (Double value2 : value1) {
                if (pos == compare)
                    data.add(value2);
                pos++;
            }
        }

        return data;
    }

    public List<Double> getCleanDataByCriteria(List<List<Double>> entry, String what) {
        int compare = getComparison(what);
        List<Double> data = new LinkedList<Double>();
        for (List<Double> value1 : entry) {
            var pos = 0;
            for (Double value2 : value1) {
                if (pos == compare)
                    data.add(value2);
                pos++;
            }
        }

        Set<Double> set = new HashSet<>(data);
        data.clear();
        data.addAll(set);
        Collections.sort(data);
        return data;
    }

    public int getComparison(String what) {
        var comparison = 0;
        switch (what) {
            case "students":
                comparison = 0;
                break;
            case "genres":
                comparison = 1;
                break;
            case "courses":
                comparison = 2;
                break;
            case "scores":
                comparison = 3;
                break;
        }
        return comparison;
    }

    public String[] getDataVector(int cant) {
        String[] test = new String[cant];
        try {
            Scanner read = new Scanner(System.in);
            for (var i = 0; i < cant; i++) {
                test[i] = read.nextLine();
            }
            read.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return test;
    }
}