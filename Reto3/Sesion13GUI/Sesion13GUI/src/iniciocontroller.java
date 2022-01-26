import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class iniciocontroller {

    @FXML
    private Label mensajelbl;

    @FXML
    public void setmensajelbl(String texto){
        mensajelbl.setText(texto);
    }
}
