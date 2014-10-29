/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sisrae;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Rick
 * Esta clase generará la pantalla principal del sistema desde el cual se tendrá acceso 
 * a las diferentes pantallas segun su uso. Asi como de brindar la opcion de cargar un evento
 * nuevo o en curso
 */
public class Home {
    JFrame home=new JFrame("Sistema de Registro de Asistencia a Eventos (SISRAE)");
    JDesktopPane dp=new JDesktopPane();
    JInternalFrame inicio = new JInternalFrame();
    JPanel p1=new JPanel(new FlowLayout()), 
            p2=new JPanel(new FlowLayout(FlowLayout.LEFT));
    Image icono=new ImageIcon("img/IconoAdec.jpg").getImage();
    JLabel etiqueta=new JLabel("Area de Trabajo");
    
    JMenuBar menu=new JMenuBar();
    JMenu archivo=new JMenu("Archivo"),
            sesion=new JMenu("Sesion");
    JMenuItem cargar=new JMenuItem("Cargar nuevo"),
            guardar=new JMenuItem("Guardar"),
            salir=new JMenuItem("Salir"),
            
            iniciar_S=new JMenuItem("Iniciar sesion"),
            cerrar_S=new JMenuItem("Cerrar sesion"),
            cambiar_S=new JMenuItem("Cambiar sesion");
    
    Home(){
        start();
    }
    
    void start(){
        
        
        //home.setLayout(null);
        //home.setLayout(new BoxLayout(home.getContentPane(), BoxLayout.Y_AXIS));
        //home.setLayout(new GridLayout(2, 1));
        home.setLayout(new BorderLayout());
        home.setIconImage(icono); //Cambio el icono de java por el de ADEC
        home.getContentPane().add(dp);//Agrego el desktopPane al Contenedor principal
        
        
        
        archivo.addSeparator();
        
        //agregar submenus a MENUS
        archivo.add(cargar);
        archivo.add(guardar);
        archivo.add(salir);
        
        sesion.add(iniciar_S);
        sesion.add(cambiar_S);
        sesion.add(cerrar_S);
        //Agregar Menus a la BARRA de MENUS
        menu.add(archivo);
        menu.add(sesion);

        
        inicio.add(p1); //Se agrega el panel de trabajo al InternalFrame
        inicio.setVisible(true);//Se hace visible el internalFrame

        p2.add(menu);
        p1.add(etiqueta); //agrego Etiqueta al Panel    

        
        home.add(p2,BorderLayout.NORTH);
        home.add(inicio,BorderLayout.CENTER); //Agrego el internal frame al Frame principal
        
        home.setDefaultCloseOperation(home.EXIT_ON_CLOSE); //Poder cerrarse 
        home.setSize(800, 600);
        home.setVisible(true); //Ser visible
        home.setResizable(false);//
    }
    
    public static void main(String[] args) {
        new Home();
    }
    
}
