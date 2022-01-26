import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application{

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    public void start(Stage arg0) throws Exception {
        try{
            FXMLLoader f = new FXMLLoader(getClass().getResource("/GradingSystemView.fxml"));
            Parent p = f.load();
            Scene s = new Scene(p);
            arg0.setScene(s);
            arg0.show();
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

}
