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
    Connection conexion;
    Statement stmt;
    ResultSet rs;
    ResultSetMetaData rsmd;
    
    ConexionABD(){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
            Logger.getLogger(ConexionABD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionABD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    void conectar(String usuario, String contraseña){
        try {
            conexion= (Connection) DriverManager.getConnection("JDBC:MySql://localhost/prueba1",usuario,contraseña);
            stmt=conexion.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionABD.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    /**
     * 
     * @param insercion "INSERT INTO <tabla> VALUES (valor1,valor2);
     */
    void insertar(String insercion){
        try {
            stmt.execute(insercion);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionABD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * 
     * @param consulta "select * from tabla"
     * @return ResultSet que tiene el resultado de la consulta.
     */
    ResultSet consultar(String consulta){
        try {
            rs=stmt.executeQuery(consulta);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionABD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    
    
    public static void main(String[] args) {
        new ConexionABD();
    }
    
}
