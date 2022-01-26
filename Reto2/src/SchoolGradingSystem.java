
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

public class SchoolGradingSystem {

    private String[] data;
    private List<List<Double>> data_cast;
    private int cant;
    private List<Double> universe;

    public String[] getData() {
        return data;
    }

    public List<List<Double>> getData_cast() {
        return data_cast;
    }

    public int getCant() {
        return cant;
    }

    public List<Double> getUniverse() {
        return universe;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public void setData_cast(List<List<Double>> data_cast) {
        this.data_cast = data_cast;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public void setUniverse(List<Double> universe) {
        this.universe = universe;
    }

    public void readData() {
        Scanner read = new Scanner(System.in);
        this.setCant(Integer.parseInt(read.nextLine()));
        data_cast = new LinkedList<List<Double>>();
        data = new String[this.getCant()];
        for (var i = 0; i < this.getCant(); i++) {
            data[i] = read.nextLine();
        }
        read.close();
    }

    public String question1() {
        var avg = this.getAverage(this.getUniverse());
        var acum = 0.0;
        for (var aux : universe) {
            acum += Math.pow((aux - avg), 2);
        }
        BigDecimal bd = new BigDecimal(acum / universe.size()).setScale(2, RoundingMode.HALF_UP);
        String val2 = "";
        if (bd.doubleValue() % 1 != 0)
            val2 = String.format("%.2f", bd.doubleValue()).replace(",", ".");
        else
            val2 = String.format("%.0f", bd.doubleValue()).replace(",", ".");
        return val2;
    }

    public String question2() {
        String output = "";
        output += this.getClasificationScore(data_cast).get("Sobresaliente").size();
        return output;
    }

    public String question3() {
        List<Double> data_genre_course = this.getScoreByCriteria("genres", this.getUniverse(), data_cast, "courses")
                .get(1.0);
        List<Double> data_genre_scores = this.getScoreByCriteria("genres", this.getUniverse(), data_cast, "scores")
                .get(0.0);
        return this.getWorstCourseAverageByGenre(data_genre_course, data_genre_scores);
    }

    public String question4() {
        List<Double> data_course_student = this.getScoreByCriteria("courses", this.getUniverse(), data_cast, "students")
                .get(1.0);
        List<Double> data_course_score = this.getScoreByCriteria("courses", this.getUniverse(), data_cast, "scores")
                .get(1.0);
        return this.getBestPerformanceByCourse(data_course_student, data_course_score);
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

    public String getBestPerformanceByCourse(List<Double> students, List<Double> scores) {
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

    public String getWorstCourseAverageByGenre(List<Double> courses, List<Double> scores) {
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

    public HashMap<String, List<Double>> getClasificationScore(List<List<Double>> universe) {

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

        return scores;
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

    public String[] getDataVector1() {
        String[] test = { "1.0 0.0 1.0 2.1", "1.0 0.0 2.0 2.7", "1.0 0.0 3.0 2.2", "2.0 0.0 1.0 0.6", "2.0 0.0 2.0 3.1",
                "2.0 0.0 3.0 4.9", "3.0 0.0 1.0 5.0", "3.0 0.0 2.0 1.2", "3.0 0.0 3.0 3.9", "4.0 1.0 1.0 3.7",
                "4.0 1.0 2.0 4.6", "4.0 1.0 3.0 0.4", "5.0 1.0 1.0 2.7", "5.0 1.0 2.0 3.6", "5.0 1.0 3.0 4.4",
                "6.0 1.0 1.0 0.1", "6.0 1.0 2.0 0.9", "6.0 1.0 3.0 3.6" };
        return test;
    }

    public String[] getDataVector2() {
        String[] test = { "1.0 0.0 1.0 2.8", "1.0 0.0 2.0 3.9", "1.0 0.0 3.0 0.1", "2.0 0.0 1.0 3.7", "2.0 0.0 2.0 3.7",
                "2.0 0.0 3.0 4.7", "3.0 0.0 1.0 4.0", "3.0 0.0 2.0 4.1", "3.0 0.0 3.0 4.4", "4.0 1.0 1.0 3.0",
                "4.0 1.0 2.0 0.2", "4.0 1.0 3.0 1.9", "5.0 1.0 1.0 0.6", "5.0 1.0 2.0 4.2", "5.0 1.0 3.0 4.0",
                "6.0 1.0 1.0 0.3", "6.0 1.0 2.0 1.9", "6.0 1.0 3.0 3.3" };
        return test;
    }

    public String[] getDataVector3() {
        String[] test = { "1.0 0.0 1.0 2.8", "1.0 0.0 2.0 0.6", "1.0 0.0 3.0 3.3", "2.0 0.0 1.0 1.0", "2.0 0.0 2.0 3.0",
                "2.0 0.0 3.0 0.3", "3.0 0.0 1.0 3.1", "3.0 0.0 2.0 0.7", "3.0 0.0 3.0 3.5", "4.0 1.0 1.0 3.4",
                "4.0 1.0 2.0 0.4", "4.0 1.0 3.0 2.4", "5.0 1.0 1.0 2.6", "5.0 1.0 2.0 1.5", "5.0 1.0 3.0 1.1",
                "6.0 1.0 1.0 2.0", "6.0 1.0 2.0 1.5", "6.0 1.0 3.0 1.3" };
        return test;
    }

    public String[] getDataVector4() {
        String[] test = { "1.0 0.0 1.0 1.5", "1.0 0.0 2.0 1.0", "1.0 0.0 3.0 0.1", "2.0 0.0 1.0 1.0", "2.0 0.0 2.0 1.5",
                "2.0 0.0 3.0 3.7", "3.0 0.0 1.0 3.4", "3.0 0.0 2.0 3.4", "3.0 0.0 3.0 0.9", "4.0 1.0 1.0 0.6",
                "4.0 1.0 2.0 1.4", "4.0 1.0 3.0 0.2", "5.0 1.0 1.0 4.7", "5.0 1.0 2.0 3.2", "5.0 1.0 3.0 3.4",
                "6.0 1.0 1.0 1.8", "6.0 1.0 2.0 0.7", "6.0 1.0 3.0 4.4" };
        return test;
    }

}
