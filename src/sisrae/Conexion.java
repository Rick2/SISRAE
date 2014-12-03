package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    private Statement st;
    
    public Conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/pruebasisrae", "root", "1234");
            st = conexion.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("error1");
        } catch (SQLException ex) {
            System.out.println("error2");
        }
    }
    
    public void executeUpdate(String query){
        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("error3");
        }
    }
    
    public ResultSet executeQuery(String query){
        try {
            return st.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("error4");
        }
        return null;
    }
    
}
