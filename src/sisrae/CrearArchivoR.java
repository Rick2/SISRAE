/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisrae;

/**
 *
 * @author monsemali
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List; 

import com.csvreader.CsvWriter;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
public class CrearArchivoR {
     
    CrearArchivoR(String path) throws SQLException {
        try {
         
            CsvWriter csvOutput = new CsvWriter(new FileWriter(path), ',');
           
            csvOutput.write("Codigo");
            csvOutput.write("Nombre");
            csvOutput.write("hora entrada");
            csvOutput.write("hora salida");                       
            csvOutput.write("Calificacion");
            csvOutput.endRecord();
            ConexionBD c=new ConexionBD();
            String cons="select * from registro;", resul=" ";
            ResultSet r=c.consulta(cons);
           
        
               
            try {
                 
                String a[]=new String[6];                
                int j;
                j = r.getRow();
                for (int i = 0; i < j; i++) {
                if( r.next()){
                a[0]= a[0]+r.getInt("id_participante");
                csvOutput.write( a[0]);
                a[1]= a[1]+r.getInt("id_evento");
                csvOutput.write( a[1]);
                a[2]= a[1]+r.getInt("id_registro")+"";
                csvOutput.write( a[2]);
                a[3]= a[1]+r.getString("entrada");
                csvOutput.write( a[3]);
                a[4]= a[1]+r.getString("salida");
                csvOutput.write(a[4]);
                a[5]= a[1]+r.getString("calificacion");
                csvOutput.write(a[5]);
                csvOutput.write("\n");

                  } 
                }
                csvOutput.close();
            } catch (SQLException ex) {
                    System.out.println("error en la consulta");
                Logger.getLogger(CrearArchivoR.class.getName()).log(Level.SEVERE, null, ex);
            
            }    
//             StringTokenizer st = new StringTokenizer(cons); 
//             while(st.hasMoreTokens()){
//                 csvOutput.write(st.nextToken());
//             }
  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
