/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sisrae;

import com.mysql.jdbc.Connection;
import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rick
 * En esta clase se trabajará la parte de la conexion del sistema con la base de
 * datos con acceso único desde la clase Maneger.
 */
public class ConexionABD {
    
    
    ConexionABD(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection conexion= (Connection) DriverManager.getConnection("JDBC:MySql://localhost/prueba1","root","Ercr.1123");
            Statement stmt=conexion.createStatement();
            //stmt.execute("INSERT INTO gente VALUES ('','')");
            
        } catch (SQLException ex) {
            System.out.println("Error en la conexion");
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Conection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public static void main(String[] args) {
        new Conection();
    }
    
}
