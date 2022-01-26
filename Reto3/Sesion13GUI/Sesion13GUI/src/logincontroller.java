import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class logincontroller {

    @FXML
    private TextField usuariotxt;

    @FXML
    private PasswordField clavetxt;

    @FXML
    private Button IniciarSesionBtn;

    @FXML
    private Label mensajeError;

    @FXML
    void iniciar(MouseEvent event) {
        try{
            String usu = usuariotxt.getText();
        String clave = clavetxt.getText();
        if(usu.equalsIgnoreCase("prueba")){
            mensajeError.setText("Usuario no valido!");
        }
        else if(clave.length() < 6){
            mensajeError.setText("Contraseña debe contar con más de 6 carácteres. Intente de nuevo.");
        }
        else if(usu.equals(clave)){
            mensajeError.setText("Contraseña no puede ser igual al usuario");
        }
        else{
            FXMLLoader f = new FXMLLoader(getClass().getResource("/productos.fxml"));
     
            Parent p = f.load();
            Scene s = new Scene(p);
            Stage s1 = new Stage();
            s1.setScene(s);
            //iniciocontroller i1 = (iniciocontroller)f.getController();
            //i1.setmensajelbl("Bienvenido al almacen "+usu);
            s1.show();
        }
        }catch(Exception e){
            System.out.println("Error");
        }
        
    }

}
