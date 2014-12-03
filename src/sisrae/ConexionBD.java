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
public class ConexionBD {
    Statement stmt;
    Connection conexion;
    ResultSet rs;
    /**
     * No muevan nada del constructor 
     */
    ConexionBD(String BD,String user,String password){
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion= (Connection) DriverManager.getConnection("JDBC:MySql://localhost/"+BD,user,password);
            stmt=conexion.createStatement();
            //stmt.execute("INSERT INTO gente VALUES ('','')");
            
           // ResultSet m= stmt.executeQuery("select * from gente;");
            //ResultSetMetaData rs=m.getMetaData();
            //VER METADATOS DE LA BASE DE DATOS
//            for (int i = 1; i <= rs.getColumnCount(); i++) {
//                System.out.println("Columna: " + rs.getColumnName(i));
//                System.out.println("Tipo: " + rs.getColumnTypeName(i));
//                System.out.println("Tabla: " + rs.getTableName(i));
//            }
//            
            //CON ESTO SE REALIZA UNA CONSULTA
            
//            while (m.next()) {
//                System.out.println("nombre: "+m.getString("nombre"));
//                System.out.println("Fecha: "+m.getString("fecha"));
//            }
        } catch (SQLException ex) {
            System.out.println("Error en la conexion");
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Clase no encontrada");
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void insertar(String sql){
        try {
            stmt.execute(sql);
        } catch (SQLException ex) {
            System.out.println("Ha habido un error en tu insercion, revisa la sintaxis");
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Este metodo funciona de la siguiente forma:
     * Creas una variable de tipo ResultSet a la que le vas a asignar el resultado de esta consulta. 
     * Luego metes dentro de un while (si asi lo deseas) para leer cada resultado de una tabla. El while lee por renglones la informacion
     * de una tabla. Por ejemplo:
     * 
     * ResultSet m= consulta("select * from algo");
     * while(m.next()){
     *      System.out.println("nombre: "+m.getString("nombre"));
     *      System.out.println("Fecha: "+m.getString("fecha"));
     * }
     * 
     * Donde nombre y fecha en las comillas son el nombre de las columnas.
     * Hay mas informacion en la documentacion.
     * 
     * @param consulta Debe ir la consulta en SQL bien redactada y con terminacion en ;. Ejemplo: select * from eventos;
     * @return El resultado de esta consulta en un tipo ResultSet
     */
    ResultSet consulta(String consulta){
        
        try {
            rs=stmt.executeQuery("select * from gente;");
        } catch (SQLException ex) {
            System.out.println("Ha habido un error en tu consulta. Revisa la sintaxis");
            Logger.getLogger(ConexionBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    public static void main(String[] args) {
        new ConexionBD("SISRAE","root","Ercr.1123");
    }
    
}
