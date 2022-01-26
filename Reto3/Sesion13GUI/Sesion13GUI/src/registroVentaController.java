import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class registroVentaController {

    @FXML
    private ChoiceBox<String> productocxc;

    @FXML
    private ChoiceBox<String> clientecxc;

    @FXML
    private TextField cantidadtxt;

    @FXML
    private Button registrarbtn;

    @FXML
    private TextField identificaciontxt;

    @FXML
    private TextField nombretxt;

    @FXML
    private TextField apellidotxt;

    @FXML
    private TextField generotxt;

    @FXML
    private Button addclientebtn;

    ObservableList<String> productos = FXCollections.observableArrayList("Seleccione el producto");
    ObservableList<String> clientes = FXCollections.observableArrayList("Seleccione el cliente");

    @FXML
    private void initialize(){
        obtenerProducto();
        obtenerCliente();
    }

    @FXML
    void addCliente(ActionEvent event) {
        connet conexion = new connet();
        Integer cedula = Integer.parseInt(identificaciontxt.getText());
        String nombre = nombretxt.getText();
        String apellido = apellidotxt.getText();
        String genero = generotxt.getText();
        String sql = "INSERT INTO Clientes(cedula, nombre, apellido, genero) VALUES (?,?,?,?)";
        try{
            Connection conex = conexion.connect();
            PreparedStatement p1 = conex.prepareStatement(sql);
            p1.setInt(1,cedula);
            p1.setString(2, nombre);
            p1.setString(3, apellido);
            p1.setString(4,genero);
            p1.executeUpdate();
            System.out.println("!!! Producto creado !!!"); 
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    

    public void obtenerProducto(){
        connet conexion = new connet();
        String sql = "SELECT * FROM Producto ORDER BY codigo";
        try{
            Connection con1 = conexion.connect();
            Statement p1 = con1.createStatement();
            ResultSet rs = p1.executeQuery(sql);

            while(rs.next()) {
                productos.add(rs.getInt("codigo")+"-"+(rs.getString("nombre")));

            }
            productocxc.setItems(productos);
            con1.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void obtenerCliente(){
        connet conexion = new connet();
        String sql = "SELECT * FROM Clientes ORDER BY id_cliente";
        try{
            Connection con1 = conexion.connect();
            Statement p1 = con1.createStatement();
            ResultSet rs = p1.executeQuery(sql);

            while(rs.next()) {
                clientes.add(rs.getInt("id_cliente")+"-"+(rs.getString("nombre")));

            }
            clientecxc.setItems(clientes);
            con1.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

        
    }
    
    String obtenerClave(String texto){
        String datos[] = texto.split("-");
        return datos[0];
    }

    @FXML
    void registrar(ActionEvent event) {
        connet conexion = new connet();
        Integer o1 = Integer.parseInt(obtenerClave(productocxc.getValue()));
        Integer o2 = Integer.parseInt(obtenerClave(clientecxc.getValue()));
        Integer o3 = Integer.parseInt(cantidadtxt.getText());

        String sql = "INSERT INTO ventas (id_producto, id_cliente, cantidad) VALUES (?, ?, ?)";
        try{
            Connection con1 = conexion.connect();
            PreparedStatement p1 = con1.prepareStatement(sql);
            p1.setInt(1, o1);
            p1.setInt(2, o2);
            p1.setInt(3, o3);
            p1.executeUpdate();
            System.out.println("!!! Venta Creada !!!!");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

}