import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class productocontroller {

    @FXML
    private TextField codigotxt;

    @FXML
    private TextField nombretxt;

    @FXML
    private TextField preciocompratxt;

    @FXML
    private TextField cantidadbodegatxt;

    @FXML
    private TextField cantidadminreqtxt;

    @FXML
    private TextField cantidadmaxtxt;

    @FXML
    private TextField precioventatxt;

    @FXML
    private Button createButton;

    @FXML
    private Button viewButton;

    @FXML
    private TextArea productList;

    @FXML
    private Button createSaleButton;

    @FXML
    private Button actualizarbtn;

    @FXML
    private Button consultarbtn;

    @FXML
    private Button eliminarbtn;

    @FXML
    void actualizar(ActionEvent event) {
        connet conexion = new connet();
        String sql = "UPDATE Producto SET nombre = ?, precio_de_compra = ?, ... WHERE codigo = ?";

    }

    @FXML
    void consultar(ActionEvent event) {
        connet conexion = new connet();
        String sql = "SELECT * FROM Producto WHERE codigo = ?";
        try{
            Connection con = conexion.connect();
            PreparedStatement st = con.prepareStatement(sql);
            Integer o1 = Integer.parseInt(codigotxt.getText());
            st.setInt(1, o1);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                nombretxt.setText(rs.getString("nombre"));
                preciocompratxt.setText(rs.getString("precio_de_compra"));
                precioventatxt.setText(rs.getString("precio_de_venta"));
                cantidadbodegatxt.setText(rs.getString("cantidad_en_bodega"));
                cantidadminreqtxt.setText(rs.getString("cant_min_requerida"));
                cantidadmaxtxt.setText(rs.getString("cant_max_inv_permitida"));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void eliminar(ActionEvent event) {
        connet conexion = new connet();
        
    }
    
    @FXML
    void createProduct(MouseEvent event) {
        connet c = new connet();
        //Se recuperan los datos de las cajas de texto
        Integer code = Integer.parseInt(codigotxt.getText());
        String name = nombretxt.getText();
        Integer preCompra = Integer.parseInt(preciocompratxt.getText());
        Integer preVenta = Integer.parseInt(precioventatxt.getText());
        Integer cantBodega = Integer.parseInt(cantidadbodegatxt.getText());
        Integer cantMin = Integer.parseInt(cantidadminreqtxt.getText());
        Integer cantMax = Integer.parseInt(cantidadmaxtxt.getText());
        try{
            Connection con = c.connect();
            Statement st = con.createStatement();
            st.executeUpdate("insert into producto(codigo,nombre,preciodecompra,preciodeventa,cantidadenbodega,cantidadminrequerida,cantidadmaxpermitida)values("+code+","+"'"+name+"'"+","+preCompra+","+preVenta+","+cantBodega+","+cantMin+","+cantMax+")");
            System.out.println("Producto creado");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void viewProduct(MouseEvent event) {
        connet conexion = new connet();
        String sql = "SELECT * FROM producto";
        try{
            Connection conn = conexion.connect();
            Statement st1 = conn.createStatement();
            ResultSet registros = st1.executeQuery(sql);
            ResultSetMetaData nombre_columna = registros.getMetaData();
            int columnas = nombre_columna.getColumnCount(); 
            String producto = "";
            while(registros.next()){
                for(int i = 1; i<columnas ; i++){
                    String valor_columna = registros.getString(i);
                    producto = producto + nombre_columna.getColumnName(i) + ": " + valor_columna + "\n";

                }
                producto = producto + "\n";
            }
            productList.setText(producto);
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }  
    
    @FXML
    void createSale(ActionEvent event) {
        try{
            FXMLLoader f = new FXMLLoader(getClass().getResource("/registrarventa.fxml"));
            Parent p = f.load();
            Scene s = new Scene(p);
            Stage st = new Stage();
            st.setScene(s);
            st.show();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    

}
