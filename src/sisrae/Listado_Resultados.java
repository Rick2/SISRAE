
package sisrae;

import com.mysql.jdbc.ResultSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Listado_Resultados {
  
    JFrame fr_listado=new JFrame();
    JPanel pan_listado=new JPanel(),
           pan_listado2=new JPanel(),
           pan_descripcion=new JPanel(),
           pan_descripcion2=new JPanel(),
           pan_cont=new JPanel(),
           pan_opciones=new JPanel(),
           pan_boton=new JPanel();
    JButton bt_generar=new JButton("Generar Listado(s)");
    JLabel lb_evento=new JLabel("Seleccione el Evento:"), 
           lb_descripcion=new JLabel("Descripcion:"),
           lb_opcion=new JLabel("Seleccione los listados que desee:");
    JCheckBox che_opcion1=new JCheckBox(" Constancias de Participacion"),
              che_opcion2=new JCheckBox(" Constacias de Asistencia"),
              che_opcion3=new JCheckBox(" Inscripciones sin Asistencia");
    JComboBox lis_eventos=new JComboBox();
    JTextArea ar_decripcion=new JTextArea(5, 15);
    JScrollPane deslizamiento=new JScrollPane(ar_decripcion);
    //Image icono=new ImageIcon("img/IconoAdec.jpg").getImage();
    String evento_seleccionado="",encabezado;
    
    
    java.sql.ResultSet rs;
    
    public static void main(String[] args) {
		new Listado_Resultados();
	}
    
    public Listado_Resultados()
    {
        combo_eventos();
        generar_formulario();
    }

    void combo_eventos()
    {
        lis_eventos.addItem("");
        rs= new ConexionBD().consulta("SELECT TipoEvento FROM evento");
        
        try {
            while (rs.next())
            {
                lis_eventos.addItem(rs.getString("TipoEvento"));
            }
        } catch (SQLException ex) {
        }
        
    }
    void descripcion_eventos(String a)
    {
        evento_seleccionado=a;
        ar_decripcion.setText("");
        rs= new ConexionBD().consulta("SELECT Descripcion FROM evento WHERE TipoEvento ='"+a+"'");
        
        try {
            while (rs.next())
            {
                ar_decripcion.append(rs.getString("Descripcion"));
            }
        } catch (SQLException ex) {
        } 
    }
    
    void datos_evento()
    {
        String evento="Nombre del evento: ",
               anfitrion="Nombre del anfitrion: ",
               coordinador="Nombre del coordinador del evento: ",
               indicador="Tipo de indicacion: ",
               lugar="Lugar: ",
               cupo_maximo="Cupo maximo de asistencia: ",
               fecha="Fecha: ",
               hora="y Hora: ";
        //Datos evento
        rs= new ConexionBD().consulta("SELECT TipoEvento, estatus, comienza_hora, termina_hora, fecha_inicio, fecha_termino FROM evento WHERE TipoEvento='"+evento_seleccionado+"'");

             try
             {
                while(rs.next())
                {
                    evento+=rs.getString("TipoEvento");
                    indicador+=rs.getString("estatus");
                    fecha+=rs.getString("fecha_inicio");
                    fecha+=" a ";
                    fecha+=rs.getString("fecha_termino");
                    hora+=rs.getString("comienza_hora");
                    hora+=" a ";
                    hora+=rs.getString("termina_hora");
                }
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
             //Datos responsables
              rs= new ConexionBD().consulta("select en.nombre, en2.nombre from evento e join atiende a on e.id_evento=a.id_evento and a.rol='anfitrion' join encargada en on a.id_encargada=en.id_encargada join atiende a2 on e.id_evento=a2.id_evento and a2.rol='coordinador' join encargada en2 on a2.id_encargada=en2.id_encargada Where e.TipoEvento='"+evento_seleccionado+"'");

             try
             {
                while(rs.next())
                {
                    anfitrion+=rs.getString("en.nombre");
                    coordinador+=rs.getString("en2.nombre");
                }
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
             //Datos lugar
             rs= new ConexionBD().consulta("select l.descripcion, l.ciudad, l.estado, l.capacidad from sede s join lugar l on s.lugar_id_lugar=l.id_lugar join evento e on s.evento_id_evento=e.id_evento Where e.TipoEvento='"+evento_seleccionado+"'");

             try
             {
                while(rs.next())
                {
                    lugar+=rs.getString("descripcion");
                    lugar+=", ";
                    lugar+=rs.getString("ciudad");
                    lugar+=", ";
                    lugar+=rs.getString("estado");
                    cupo_maximo+=rs.getString("capacidad");
                }
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
             
            encabezado=evento+"\n"+anfitrion+"\n"+coordinador+"\n"+indicador+"\n"+lugar+"\n"+cupo_maximo+"\n"+fecha+" "+hora+"\n";
             
             System.out.println(encabezado);
    }
    void accion_boton() throws SQLException, FileNotFoundException, DocumentException, IOException
    {
      
        Document documento1 = new Document();
        Document documento2 = new Document();
        Document documento3 = new Document();
        
        String datos_participante="";
        String total_datos_participante="";
        int tipo_evento=99;
        
        rs= new ConexionBD().consulta("select tipo_id_tipo from evento where TipoEvento='"+evento_seleccionado+"'");
        try
        {
            while (rs.next())
            {
                tipo_evento=rs.getInt("tipo_id_tipo");
            }
 
        }
        catch(Exception e)
                {
                    System.out.println(e);
                }
        
        
       if(che_opcion1.isSelected() || che_opcion2.isSelected()||che_opcion3.isSelected())
       {
           datos_evento();
        
           if(che_opcion1.isSelected())
           {
           System.out.println(evento_seleccionado);
           FileOutputStream doc_Pdf = new FileOutputStream("Listados/"+evento_seleccionado+"_ConstanciasDeParticipacion.pdf");
           PdfWriter.getInstance(documento1,doc_Pdf);
           documento1.open();
           documento1.add(new Paragraph("Listado para constancias de participacion"));
           documento1.add(new Paragraph(encabezado));
           rs= new ConexionBD().consulta("select p.id_participante, p.nombre,p.apellido_pat, p.apellifo_mat,p.seccion from participante p join registro r on p.id_participante=r.id_participante join evento e on e.id_evento=r.id_evento where e.TipoEvento='"+evento_seleccionado+"' and (r.calificacion>70 or r.calificacion='A')");

             try
             {
                while(rs.next())
                {
                   datos_participante+=rs.getString("p.id_participante")+","+
                                       rs.getString("p.nombre")+","+
                                       rs.getString("p.apellido_pat")+","+
                                       rs.getString("p.apellifo_mat")+","+
                                       rs.getString("p.seccion")+("\n");
                   documento1.add(new Paragraph(datos_participante));
                   total_datos_participante+=datos_participante;
                   datos_participante="";
                   
                }
                documento1.close();
                 
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
             
             
             //documento csv
             try
            {
                FileWriter doc_csv=new FileWriter("Listados/"+evento_seleccionado+"_ConstanciasDeParticipacion.csv");
                doc_csv.write("Listado para constancias de participacion\n");
                doc_csv.write(encabezado);
                doc_csv.write(total_datos_participante);
                doc_csv.flush();
                doc_csv.close();
            }
             catch (IOException e){
                 System.out.println(e);
            }
             
              
               datos_participante="";
               total_datos_participante="";
               
           }
           
           if(che_opcion2.isSelected())
           {
           FileOutputStream doc_Pdf2 = new FileOutputStream("Listados/"+evento_seleccionado+"_ConstanciasDeAsistencia.pdf");
           PdfWriter.getInstance(documento2,doc_Pdf2);
           
           documento2.open();
           documento2.add(new Paragraph("Listado para constancias de inscripcion"));
           documento2.add(new Paragraph(encabezado));
               
              rs= new ConexionBD().consulta("select p.id_participante, p.nombre,p.apellido_pat, p.apellifo_mat,p.seccion,r.calificacion from participante p join registro r on p.id_participante=r.id_participante join evento e on e.id_evento=r.id_evento where e.TipoEvento='"+evento_seleccionado+"' and r.calificacion<70 and r.calificacion!='A'");

             try
             {
                while(rs.next())
                {
                   datos_participante+=rs.getString("p.id_participante")+","+
                                       rs.getString("p.nombre")+","+
                                       rs.getString("p.apellido_pat")+","+
                                       rs.getString("p.apellifo_mat")+","+
                                       rs.getString("p.seccion")+","+
                                       rs.getString("r.calificacion")+("\n");
                   documento2.add(new Paragraph(datos_participante));
                   total_datos_participante+=datos_participante;
                   datos_participante="";
                   
                }
                documento2.close();
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
             //documento csv
             try
            {
            FileWriter doc_csv2=new FileWriter("Listados/"+evento_seleccionado+"_ConstanciasDeAsistencia.csv");
            doc_csv2.write("Listado para constancias de inscripcion\n");
            doc_csv2.write(encabezado);
            doc_csv2.write(total_datos_participante);
            doc_csv2.flush();
            doc_csv2.close();
            }
             catch (IOException e){
                 System.out.println(e);
            }
                datos_participante="";
               total_datos_participante=""; 
             }
           
           if(che_opcion3.isSelected())
           {
           FileOutputStream doc_Pdf3 = new FileOutputStream("Listados/"+evento_seleccionado+"_InscripcionSinAsistencia.pdf");
           PdfWriter.getInstance(documento3,doc_Pdf3);
           
           documento3.open();
           documento3.add(new Paragraph("Listado para inscripciones sin asistencias"));
           documento3.add(new Paragraph(encabezado));

           rs= new ConexionBD().consulta("select p.id_participante, p.nombre,p.apellido_pat, p.apellifo_mat,p.seccion from participante p join registro r on p.id_participante=r.id_participante join evento e on e.id_evento=r.id_evento where e.TipoEvento='"+evento_seleccionado+"' and r.calificacion is null");

             try
             {
                while(rs.next())
                {
                   datos_participante+=rs.getString("p.id_participante")+","+
                                       rs.getString("p.nombre")+","+
                                       rs.getString("p.apellido_pat")+","+
                                       rs.getString("p.apellifo_mat")+","+
                                       rs.getString("p.seccion")+("\n");
                   documento3.add(new Paragraph(datos_participante));
                   total_datos_participante+=datos_participante;
                   datos_participante="";
                   
                }
                documento3.close();
             }
             catch(Exception e)
             {
                 System.out.println(e);
             }
             
             
             //documento csv
             try
            {
            FileWriter doc_csv3=new FileWriter("Listados/"+evento_seleccionado+"_InscripcionSinAsistencia.csv");
            doc_csv3.write("Listado para iscripciones sin asistencias\n");
            doc_csv3.write(encabezado);
            doc_csv3.write(total_datos_participante);
            doc_csv3.flush();
            doc_csv3.close();
            }
             catch (IOException e){
                 System.out.println(e);
            }
             datos_participante="";
             total_datos_participante="";
            }
       JOptionPane.showMessageDialog(pan_cont, "Documentos Generados\nLos puede localizar en la carpeta\nlistados dentro de la raiz");
       
           }
       else
           JOptionPane.showMessageDialog(pan_cont, "Seleccione al menos \n un tipo de listado","Error",0);
    }
    void generar_formulario()
    {
        fr_listado.getContentPane().setLayout(new GridLayout(4,1));
        fr_listado.setTitle("Generar Listado de Resultados");
        //fr_listado.setIconImage(icono);
        ar_decripcion.setEditable(false);
        
       lis_eventos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				                    descripcion_eventos((String)(lis_eventos.getSelectedItem()));
			}});
        
       bt_generar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
                            try {
                                accion_boton();
                            } catch (SQLException ex) {
                                Logger.getLogger(Listado_Resultados.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Listado_Resultados.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (DocumentException ex) {
                                Logger.getLogger(Listado_Resultados.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Listado_Resultados.class.getName()).log(Level.SEVERE, null, ex);
                            }
			}});
        //panel listado 1 y 2
        pan_listado.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_listado.add(lb_evento);
        pan_listado2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_listado2.add(lis_eventos);
        
        //panel descripcion 1 u 2 
        pan_descripcion.setLayout(new FlowLayout(FlowLayout.LEFT));
        pan_descripcion.add(lb_descripcion);
        pan_descripcion2.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_descripcion2.add(deslizamiento);
        
        //panel contenedor
        pan_cont.setLayout(new GridLayout(2,2));
        pan_cont.add(pan_listado);
        pan_cont.add(pan_listado2);
        pan_cont.add(pan_descripcion);
        pan_cont.add(pan_descripcion2);
        
        //panel opciones
        pan_opciones.setLayout(new GridLayout(4,0,0,3));
        pan_opciones.add(lb_opcion);
        pan_opciones.add(che_opcion1);
        pan_opciones.add(che_opcion2);
        pan_opciones.add(che_opcion3);
        //panel boton
        pan_boton.setLayout(new FlowLayout(FlowLayout.RIGHT));
        pan_boton.add(bt_generar);
        //agregar a contenedor
        fr_listado.add(pan_cont);
        fr_listado.add(pan_opciones);
        fr_listado.add(pan_boton);
        
        fr_listado.pack();
        fr_listado.setVisible(true);
        fr_listado.setSize(400,400);
        fr_listado.setResizable(false);
        fr_listado.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
