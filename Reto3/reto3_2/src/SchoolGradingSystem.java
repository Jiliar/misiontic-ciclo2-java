
import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class SchoolGradingSystem extends GradingSystem{
    
    public List<List<Double>> loadData() {
        Scanner read = new Scanner(System.in);
        var cant = Integer.parseInt(read.nextLine());
        List<List<Double>> data_cast = new LinkedList<List<Double>>();

        String[] data = new String[cant];
        for (var i = 0; i < cant; i++) {
            data[i] = read.nextLine();
        }
        read.close();

        if (data.length == cant) {
            for (var i = 0; i < cant; i++) {
                String[] aux = data[i].trim().split(" ");
                List<Double> double_cast = new LinkedList<Double>();
                for (String cadena : aux) {
                    double_cast.add(Double.parseDouble(cadena));
                }
                data_cast.add(double_cast);
            }
        } else {
            data_cast = null;
        }
        return data_cast;
    }
    
}
