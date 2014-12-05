package SISRAE;

import Conexion.Conexion;
import java.io.*; 
import java.sql.*;
import jxl.*;

//variables que se utilizan

public class ReadExcel { 
    Conexion con,c;
    int id_participante;
    int id_evento;
    int id_registro;
    String entrada;
    String salida;
    String calificacion;
    
//esta clase es para abrir la ventana que dejara seleccionar el archivo .csv
//y lo lee para almacenar los datos en la BD
    
    public void algo(String a) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        
        javax.swing.JFileChooser j= new javax.swing.JFileChooser();

        j.showOpenDialog(j);
        
        String path= j.getSelectedFile().getAbsolutePath();

	String csvFile = path;
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
 
	try {
 
		br = new BufferedReader(new FileReader(csvFile));
		while ((line = br.readLine()) != null) {
//Usa coma como separador, arreglo country para seleccionar las "localidades"
//donde se encuentran los datos dentro del archivo
			String[] country = line.split(cvsSplitBy);
                        System.out.println("\n"); 
                    con = new Conexion();
//Se hace la insercion en la BD con ayuda del arreglo
                    String sentencia="INSERT INTO registro(`id_participante`, `id_evento`, `id_registro`, `entrada`, `salida`, `calificacion`) VALUES('"+country[0]+"','"+country[1]+"','"+country[2]+"','"+country[3]+"','"+country[4]+"','"+country[5]+"')";
                    con.ejecutar(sentencia);
 
		}
//Errores
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
                }
	}
    }
    
//Se hace la conexion con la ayuda de la variable antes declarada
//la linea algo("Hola") es para que se ejecute lo de la lectura del archivo
    ReadExcel() { 
        try { 
            c = new Conexion();
            String sql;
            algo("Hola");
            
        }
        catch (Exception ioe) { 
            ioe.printStackTrace();
        } 
    } 
    
//Se manda llamar a la clase ReadExel la cual hace la conexion y manda llamar a
// la clase algo que hace la lectura del archivo
    public static void main(String arg[]) { 
        ReadExcel excel = new ReadExcel();
    } 
}