/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sisrae;

import com.sun.jmx.snmp.BerDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Rick
 */
public class Clock extends JLabel implements Runnable{
    
    
    private String dia,
                    mes,
                    año,
                    minutos,
                    segundos,
                    hora;
    private Calendar calendario=new GregorianCalendar();
    Thread hilo;

    public int getDia() {
        return Integer.getInteger(dia);
    }
    public int getMes() {
        return Integer.getInteger(mes);
    }
    public int getAño() {
        return Integer.getInteger(año);
    }

    public int getMinutos() {
        return Integer.getInteger(minutos);
    }
    public int getHora() {
        return Integer.getInteger(hora);
    }

    
    
    //Constructores
    Clock(int x,int y,int weight,int height){
        setBounds(x, y, weight, height);
        hilo=new Thread(this);
        hilo.start();
    }
    Clock(){
        hilo=new Thread(this);
        hilo.start();
        //this.run();
    }
    //Fin de Constructores
    
    
    
    
    

    @Override
    public void run() {
        Thread ct=new Thread();
        while (true) {
            try {
            actualiza();
            String mesEnero=(Integer.valueOf(mes)+1)+"";
            setText(dia+"/"+mesEnero+"/"+año+"  "+hora+":"+minutos+":"+segundos);
            Thread.sleep(1000);
            //repaint();
            } catch (InterruptedException ex) {
                System.out.println("El hilo no se durmio bien, que paso?");
            }
            
        }
        
        
    }
    
    
    void actualiza(){
        Date fechaHoraActual = new Date(); 
        calendario.setTime(fechaHoraActual); 
        
        
        segundos=calendario.get(Calendar.SECOND)>9?calendario.get(Calendar.SECOND)+"":"0"+calendario.get(Calendar.SECOND);
        minutos=calendario.get(Calendar.MINUTE)>9?calendario.get(Calendar.MINUTE)+"":"0"+calendario.get(Calendar.MINUTE);
        hora=calendario.get(Calendar.HOUR_OF_DAY)>9?calendario.get(Calendar.HOUR_OF_DAY)+"":"0"+calendario.get(Calendar.HOUR_OF_DAY);
        dia=calendario.get(Calendar.DATE)>9?calendario.get(Calendar.DATE)+"":"0"+calendario.get(Calendar.DATE);
        mes=calendario.get(Calendar.MONTH)>9?calendario.get(Calendar.MONTH)+"":"0"+calendario.get(Calendar.MONTH);
        año=calendario.get(Calendar.YEAR)>9?calendario.get(Calendar.YEAR)+"":"0"+calendario.get(Calendar.YEAR);
    }
    
    
    public static void main(String[] args) {
        
        JFrame f=new JFrame("Prueba del reloj");
        Clock reloj=new Clock();
        
        f.getContentPane().add(reloj);
        
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
        
    }
    
    
}
