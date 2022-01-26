import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class connet{  
    public Connection connect(){
        String url = "jdbc:sqlite:D:/Storage/Libraries/Documents/SQLiteStudio/AlmacenV2";
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url);
            System.out.println("Me conecte");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
