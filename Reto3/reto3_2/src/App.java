import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage arg) throws Exception {
        FXMLLoader f = new FXMLLoader(getClass().getResource("/GradingSystemView.fxml"));
        Parent p = f.load();
        Scene s = new Scene(p);
        arg.setScene(s);
        arg.show();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Entro");
        /*
         * launch(args);
         * 
         * SchoolGradingSystem r = new SchoolGradingSystem();
         * 
         * //List<List<Double>> data_cast = r.loadData();
         * 
         * List<List<Double>> data_cast = new Test().loadDataTest();
         * 
         * if(data_cast != null){ var genres = r.getCleanDataByCriteria(data_cast,
         * "genres"); var courses = r.getCleanDataByCriteria(data_cast, "courses"); var
         * scores = r.getRawData(data_cast, "scores");
         * 
         * System.out.println(String.format("%.2f", r.stat1(scores)).replace(",", "."));
         * System.out.println(r.stat2(data_cast).get("Sobresaliente").size());
         * 
         * List<Double> data_genre_course = r.getScoreByCriteria("genres", genres,
         * data_cast, "courses").get(1.0); List<Double> data_genre_scores =
         * r.getScoreByCriteria("genres", genres, data_cast, "scores").get(0.0);
         * 
         * if (data_genre_course != null && data_genre_scores != null) {
         * System.out.println(r.stat3(data_genre_course, data_genre_scores)); } else {
         * System.out.println("Worst Course By Genre No calculated"); }
         * 
         * List<Double> data_course_student = r.getScoreByCriteria("courses", courses,
         * data_cast, "students").get(1.0); List<Double> data_course_score =
         * r.getScoreByCriteria("courses", courses, data_cast, "scores").get(1.0);
         * 
         * if (data_genre_course != null && data_genre_scores != null) {
         * System.out.println(r.stat4(data_course_student, data_course_score)); } else {
         * System.out.println("Best Performance By Course No calculated"); }
         * 
         * }else{ System.out.
         * println("Number of records in vector are not equal to what was inserted by the usethis."
         * ); }
         */
    }

}
