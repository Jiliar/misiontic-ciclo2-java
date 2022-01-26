import java.util.List;
import java.util.LinkedList;

public class Test {
    
    public List<List<Double>> loadDataTest() {

        List<List<Double>> data_cast = new LinkedList<List<Double>>();

        Test t=new Test();
        String[] data = t.getDataVector1();

        for (var i = 0; i < data.length; i++) {
            String[] aux = data[i].trim().split(" ");
            List<Double> double_cast = new LinkedList<Double>();
            for (String cadena : aux) {
                double_cast.add(Double.parseDouble(cadena));
            }
            data_cast.add(double_cast);
        }

        return data_cast;
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