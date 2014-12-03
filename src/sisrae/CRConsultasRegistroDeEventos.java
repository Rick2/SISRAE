/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sisrae;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chess
 */
public class CRConsultasRegistroDeEventos {

    int cont;
    String Modifica = "";
    ArrayList<Integer> elimina;
    ArrayList<String> Datos;
    /**
     * *
     * CONFIGURACION DE LA BASE DE DATOS
     *
     ************************************************
     */
    String BaseDatos = "SISRAE";
    String Usuario = "root";
    String Contraseña = "";
    /**
     * **********************************************
     */
    String url = "JDBC:MySql://localhost/" + BaseDatos;
    Connection con;
    Statement stmt;
    ResultSet rs;

    //METODO PARA LLENAR COMBOBOX "TIPO DE EVENTO" DE INTERFAZ CREAR EVENTO 
    public ArrayList<String> GetTipoEvento() throws SQLException, InstantiationException {
        try {
            Datos = new ArrayList<>();
            Datos.clear();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, Usuario, Contraseña);
            stmt = con.createStatement();
            String query = "select nombre from tipo";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Datos.add(rs.getString("nombre"));
            }
            stmt.execute("COMMIT");
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CRConsultasRegistroDeEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Datos;
    }

    //METODO PARA OBTENER LOS DATOS QUE SE ESCRIBIRAN EN EL "ARCHIVO REPORTE"
    public ArrayList<String> GetDatosReporte(int Dato) throws SQLException, InstantiationException {
        try {
            Datos = new ArrayList<>();
            Datos.clear();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, Usuario, Contraseña);
            stmt = con.createStatement();
            String query = "select t.nombre,e.id_evento,e.TipoEvento,e.Descripcion,e.fecha_inicio,e.fecha_termino,e.comienza_hora,e.termina_hora,"
                    + "en.nombre,l.ciudad,l.domicilio,l.capacidad"
                    + " from `tipo` AS t inner join `evento` AS e on t.id_tipo = e.id_tipo inner join `atiende` AS a"
                    + "	on e.id_evento = a.id_evento inner join `encargada` AS en on en.id_encargada = a.id_encargada inner join `sede` AS s"
                    + "	on s.id_evento = e.id_evento inner join `lugar` AS l on l.id_lugar = s.id_lugar where e.id_evento= " + Dato;
            rs = stmt.executeQuery(query);
            String Cadena = null;
            while (rs.next()) {
                //tabla tipo
                Cadena = System.getProperty("line.separator") + "EVENTO " + rs.getString("t.nombre");
                //tabla evento
                Cadena += "                                                                                                            Clave: " + rs.getString("e.id_evento");
                Cadena += "        " + rs.getString("e.TipoEvento");
                Cadena += "             								                                                               " + rs.getString("e.Descripcion");
                Cadena += "								                                                                                       Inicia: " + rs.getString("e.fecha_inicio");
                Cadena += " Termina: " + rs.getString("e.fecha_termino");
                Cadena += " de las " + rs.getString("e.comienza_hora");
                Cadena += " a las " + rs.getString("e.termina_hora");
                // tabla encargada
                Cadena += "                                                                                                    Organizador General: " + rs.getString("en.nombre");
                // tabla lugar
                Cadena += "                                                                                               Institucion SEDE: " + rs.getString("l.ciudad");
                Cadena += ", " + rs.getString("l.domicilio");
                Cadena += ". Capacidad: " + rs.getString("l.capacidad") + ".";
                Datos.add(Cadena);
            }
            System.out.println(query);
            stmt.execute("COMMIT");
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CRConsultasRegistroDeEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Datos;
    }

//METODO PARA INICIALIZAR O ACTUALIZAR "CONTADOR = ID_EVENTO"
    public int GetContador() throws SQLException, InstantiationException {
        String contador = null;
        int c = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, Usuario, Contraseña);
            stmt = con.createStatement();
            String query = "select id_evento from evento ORDER BY id_evento DESC LIMIT 1";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                contador = String.valueOf(rs.getString("id_evento"));
            }
            if (c == 0 && contador == null) {
                c = -1;
            } else {
                c = Integer.parseInt(contador);
            }
            c++;
            stmt.execute("COMMIT");
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CRConsultasRegistroDeEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

//METODO PARA CREAR UN NUEVO EVENTO 
    public void InsertarEvento(
            String Estatus,
            String Comienza_Hora,
            String Fecha_Inicio,
            String Descripcion,
            String Duracion,
            String Termina_Hora,
            String Fecha_Termino,
            String TipoEvento,
            int id_evento,
            int Condicion,
            int id_tipo) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, Usuario, Contraseña);
            stmt = con.createStatement();
            String query = "Insert into evento (id_evento,id_tipo,TipoEvento,Descripcion,estatus,Duracion,comienza_hora,termina_hora,fecha_inicio,fecha_termino,obligatorio)"
                    + "values (" + id_evento + "," + id_tipo + ",'" + TipoEvento + "','" + Descripcion + "','" + Estatus + "','" + Duracion + "','" + Comienza_Hora + "','" + Termina_Hora + "','"
                    + Fecha_Inicio + "','" + Fecha_Termino + "'," + Condicion + ");";
            System.out.println(query);
            stmt.executeUpdate(query);
            stmt.execute("COMMIT");
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//METODO PARA ACTUALIZAR UN EVENTO
    public void ActualizaEvento(String Estatus, String Comienza_Hora, String Fecha_Inicio, String Descripcion, String Duracion,
            String Termina_Hora, String Fecha_Termino, String TipoEvento, int id_evento, int Condicion) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, Usuario, Contraseña);
            stmt = con.createStatement();
            String query1 = "update evento set TipoEvento='" + TipoEvento + "' where id_evento=" + id_evento;
            String query2 = "update evento set Descripcion='" + Descripcion + "' where id_evento=" + id_evento;
            String query3 = "update evento set estatus='" + Estatus + "' where id_evento=" + id_evento;
            String query4 = "update evento set Duracion='" + Duracion + "' where id_evento=" + id_evento;
            String query5 = "update evento set comienza_hora='" + Comienza_Hora + "' where id_evento=" + id_evento;
            String query6 = "update evento set termina_hora='" + Fecha_Inicio + "' where id_evento=" + id_evento;
            String query7 = "update evento set fecha_inicio='" + Termina_Hora + "' where id_evento=" + id_evento;
            String query8 = "update evento set fecha_termino='" + Fecha_Termino + "' where id_evento=" + id_evento;
            String query9 = "update evento set obligatorio=" + Condicion + " where id_evento=" + id_evento;
            stmt.executeUpdate(query1);
            stmt.executeUpdate(query2);
            stmt.executeUpdate(query3);
            stmt.executeUpdate(query4);
            stmt.executeUpdate(query5);
            stmt.executeUpdate(query6);
            stmt.executeUpdate(query7);
            stmt.executeUpdate(query8);
            stmt.executeUpdate(query9);
            System.out.println("" + query9);
            stmt.execute("COMMIT");
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//METODO PARA ELIMINAR UN EVENTO
    public void EliminarEvento(int id_evento) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, Usuario, Contraseña);
            stmt = con.createStatement();
            String query = "DELETE FROM evento WHERE id_evento = " + id_evento;
            System.out.println("\n" + query);
            stmt.executeUpdate(query);
            stmt.execute("COMMIT");
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

//METODO PARA LLENAR EL COMBOBOX EN LA INTERFAZ MODIFICAR EVENTO
    public ArrayList<String> GetSeleccionaEvento() throws SQLException, InstantiationException {
        try {
            Datos = new ArrayList<>();
            Datos.clear();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, Usuario, Contraseña);
            stmt = con.createStatement();
            String query = "select id_evento, TipoEvento, Descripcion from evento";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                Modifica = rs.getString("id_evento");
                Modifica += "  " + rs.getString("TipoEvento");
                Modifica += "  " + rs.getString("Descripcion");
                Datos.add(Modifica);
            }
            stmt.execute("COMMIT");
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CRConsultasRegistroDeEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Datos;
    }

//METODO PARA RECUPERAR ARRAYLIST QUE CONTIENE LOS ID DE LOS EVENTOS QUE EXISTEN
    public ArrayList<Integer> GetIndiceEnArray() throws SQLException, InstantiationException {
        try {
            elimina = new ArrayList<>();
            elimina.clear();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection(url, Usuario, Contraseña);
            stmt = con.createStatement();
            String query = "select id_evento from evento";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                this.elimina.add(Integer.parseInt(rs.getString("id_evento")));
            }
            stmt.execute("COMMIT");
            stmt.close();
            con.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CRConsultasRegistroDeEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return elimina;
    }

    public void SetContador(int control) {
        this.cont = control;
    }

    public int getCont() {
        return cont;
    }

    void InsertarEvento(String estatus, String comienzahora, String fechainicio, String descripcion, String duracion, String terminahora, String termino, int id_evento, int condicional, int id_tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
