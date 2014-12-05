/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sisrae;

import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;


/**
 *
 * @author Oz44
 */
public class CriteriosEvaluacion extends JPanel implements ItemListener{
 JPanel comboBoxPane = new JPanel();
 JPanel Cards = new JPanel(new CardLayout());
 
 JPanel individual = new JPanel();
 
 JPanel global = new JPanel();
 JPanel evento = new JPanel();
 
 ConexionBD C = new ConexionBD ("sisrae","root","root");
 Vector<String> comboBoxItems = new Vector<String> ();
 ResultSet RS;
 ResultSet AUX;
 
    public CriteriosEvaluacion() {
        
        //Obetener eventos individuales
        RS=C.consulta("SELECT id_evento,Descripcion,id_tipo FROM `evento`;");
     try {
         while(RS.next()){
             comboBoxItems.add(RS.getString(2));
         }
     } catch (SQLException ex) {
         Logger.getLogger(CriteriosEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
     }
       
        
        JComboBox eventocb = new JComboBox(comboBoxItems);
        eventocb.setEditable(false);
        eventocb.addItemListener(this);
       
        evento.add(eventocb);
        
        
       /* individual.setLayout(new BoxLayout(individual,BoxLayout.PAGE_AXIS));
        global.setLayout(new BoxLayout(global,BoxLayout.PAGE_AXIS));*/
       
      
        global.add(new Checkbox("Asistencia a Todo"));
        global.add(new Checkbox("Asistencia C.Obligatorias"));
        global.add(new Checkbox("Asistencia T.Obligatorios"));
        global.add(new Checkbox("Calificacion Promedio"));
        
        individual.add(new Checkbox("Hora de Entrada"));
        individual.add(new Checkbox("Hora de Salida"));
        individual.add(new Checkbox("Calificacion"));
        
        
        Cards = new JPanel(new CardLayout());
        
//     try {
//         while(RS.next()){
//             if(RS.getInt(3)==2){
//                  Cards.add(individual,RS.getString(2));
//             }else{
//                 if(RS.getInt(3)==1){
//                     Cards.add(global,RS.getString(2));
//                 }
//             }
//            
//         }
       
        RS=C.consulta("SELECT id_tipo,descripcion FROM `evento` where descripcion='"+
                        eventocb.getItemAt(0).toString()+  "';");
        try {
            String n="";
         while(RS.next()){
             if(RS.getInt(1)==1){
                  //System.out.println(eventocb.getItemAt(0).toString());
                n=RS.getString(2);
                 Cards.add(global,n);
             }else{
                 n=RS.getString(2);
                   Cards.add(individual, n);
             }
         }
         
         RS=C.consulta("SELECT id_evento,Descripcion,id_tipo FROM `evento`;");
         
         while(RS.next()){
             if(RS.getInt(3)==1){
                 Cards.add(global,RS.getString(2));
             }else{
                 Cards.add(individual,RS.getString(2)); 
             }
         }
         
         } catch (SQLException ex) {
         Logger.getLogger(CriteriosEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
     }
         
      
       
        
//     } catch (SQLException ex) {
//         Logger.getLogger(CriteriosEvaluacion.class.getName()).log(Level.SEVERE, null, ex);
//     }
        
        this.add(evento);
        this.add(Cards);
        
        
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        System.out.println((String)evt.getItem());
        CardLayout cl = (CardLayout)(Cards.getLayout());
        cl.show(Cards,(String)evt.getItem());
    }
    
    
   
    
}
