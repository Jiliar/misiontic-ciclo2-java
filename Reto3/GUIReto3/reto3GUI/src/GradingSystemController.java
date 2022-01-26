import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import java.util.List;

public class GradingSystemController {

    @FXML
    private Label lblTitulo;

    @FXML
    private TextField txtCantidad;

    @FXML
    private Button btnCalcular;

    @FXML
    private TextArea txtInput;

    @FXML
    private TextArea txtOutput;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void calcular(ActionEvent event) {

        StringBuilder ouput = new StringBuilder("");
        SchoolGradingSystem r = new SchoolGradingSystem();
        try {
            var cant = Integer.parseInt(txtCantidad.getText());
            var inputs = txtInput.getText();
            String[] data = inputs.trim().split("\n");
            List<List<Double>> data_cast = r.loadData(data);

            if (data.length == cant) {
                var genres = r.getCleanDataByCriteria(data_cast, "genres");
                var courses = r.getCleanDataByCriteria(data_cast, "courses");
                var scores = r.getRawData(data_cast, "scores");

                System.out.println(String.format("%.2f", r.stat1(scores)).replace(",", "."));
                ouput.append(String.format("%.2f", r.stat1(scores)).replace(",", ".") + "\n");

                List<Double> data_genre_course = r.getScoreByCriteria("genres", genres, data_cast, "courses").get(1.0);
                List<Double> data_genre_scores = r.getScoreByCriteria("genres", genres, data_cast, "scores").get(0.0);

                if (data_genre_course != null && data_genre_scores != null) {
                    System.out.println(r.stat3(data_genre_course, data_genre_scores));
                    ouput.append(r.stat3(data_genre_course, data_genre_scores) + "\n");
                } else {
                    System.out.println("Worst Course By Genre No calculated");
                    ouput.append("Worst Course By Genre No calculated \n");
                }

                List<Double> data_course_student = r.getScoreByCriteria("courses", courses, data_cast, "students")
                        .get(1.0);
                List<Double> data_course_score = r.getScoreByCriteria("courses", courses, data_cast, "scores").get(1.0);

                if (data_genre_course != null && data_genre_scores != null) {
                    System.out.println(r.stat4(data_course_student, data_course_score) + "\n");
                    ouput.append(r.stat4(data_course_student, data_course_score) + "\n");
                } else {
                    System.out.println("Best Performance By Course No calculated");
                    ouput.append("Best Performance By Course No calculated \n");
                }

            } else {
                System.out.println(
                        "Number of records in vector are not equal to what was inserted by the usethis. \n Cantidad: "
                                + cant + " \n Inputs: \n " + data.length);
                ouput.append(
                        "Number of records in vector are not equal to what was inserted by the usethis. \n Cantidad: "
                                + cant + " \n Inputs: \n " + data.length + "\n");
            }

            txtOutput.setText(ouput.toString());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            ouput.append("Error: " + e.getMessage());
        }

    }

}
