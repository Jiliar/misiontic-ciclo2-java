import java.util.List;
import java.util.LinkedList;

public class App {

    public static void main(String[] args) throws Exception {

        SchoolGradingSystem r = new SchoolGradingSystem();

        r.readData();
        String[] data = r.getData();
        // String[] data = r.getDataVector1();
        List<List<Double>> data_cast = new LinkedList<List<Double>>();
        int cant = r.getCant();
        // int cant = 18;
        if (data.length == cant) {
            for (var i = 0; i < cant; i++) {
                String[] aux = data[i].trim().split(" ");
                List<Double> double_cast = new LinkedList<Double>();
                for (String cadena : aux) {
                    double_cast.add(Double.parseDouble(cadena));
                }
                data_cast.add(double_cast);
            }

            var genres = r.getCleanDataByCriteria(data_cast, "genres");
            var courses = r.getCleanDataByCriteria(data_cast, "courses");
            var scores = r.getRawData(data_cast, "scores");

            r.setData_cast(data_cast);
            r.setUniverse(scores);
            System.out.println(r.question1());
            System.out.println(r.question2());
            r.setUniverse(genres);
            System.out.println(r.question3());
            r.setUniverse(courses);
            System.out.println(r.question4());

        } else {
            System.out.println("Number of records in vector are not equal to what was inserted by the user.");
        }

    }

}
