import java.util.List;
import java.util.LinkedList;

public class SchoolGradingSystem extends GradingSystem{
    
    public List<List<Double>> loadData(String[] data) {

        List<List<Double>> data_cast = new LinkedList<List<Double>>();

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
    
}
