/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sisrae;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Rick
 * Esta clase generará la pantalla principal del sistema desde el cual se tendrá acceso 
 * a las diferentes pantallas segun su uso. Asi como de brindar la opcion de cargar un evento
 * nuevo o en curso
 */
public class Home extends JFrame{
    JFrame jf=this;
    
    //JFrame home=new JFrame("Sistema de Registro de Asistencia a Eventos (SISRAE)");
    JDesktopPane dp=new JDesktopPane();
    JInternalFrame inicio = new JInternalFrame();
    
    JPanel p1_if=new JPanel(new FlowLayout()), //panel 1 del Internal Frame
            p2=new JPanel(new FlowLayout(FlowLayout.LEADING));
    
    JPanel p2_1=new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel p2_2=new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    Image icono=new ImageIcon("img/IconoAdec.jpg").getImage();
    JLabel etiqueta=new JLabel("Area de Trabajo");
    
    JMenuBar menu=new JMenuBar();
    JMenu archivo=new JMenu("Archivo"),
            sesion=new JMenu("Sesion"),
            evento=new JMenu("Eventos"),
            asistencia=new JMenu("Asistencias y Participantes"),
            listados=new JMenu("Generacion de listados"),
            criterios=new JMenu("Criterios de evaluacion");
    
    
    JMenuItem cargar_ar=new JMenuItem("Help me!"),
            guardar_ar=new JMenuItem("Imprimir"),
            salir_ar=new JMenuItem("Salir"),
            respaldo_ar=new JMenuItem("Generacion del Respaldo"),
            
            iniciar_se=new JMenuItem("Iniciar sesion"),
            cerrar_se=new JMenuItem("Cerrar sesion"),
            cambiar_se=new JMenuItem("Cambiar sesion"),
            
            crear_ev=new JMenuItem("Nuevo evento"),
            cargar_ev=new JMenuItem("Cargar evento"),
            guardar_ev=new JMenuItem("Cancelar evento"),
            
            registrar_as=new JMenuItem("Registrar nuevo participante"),
            modificar_as=new JMenuItem("Modificar participante"),
            eliminar_as=new JMenuItem("Eliminar participante"),
            asistencia_as=new JMenuItem("Registrar asistencias"),
    
            generar_asis=new JMenuItem("Listado de reconocimientos");
            
    Clock reloj=new Clock();
    
    Home(){
        start();
    }
    
    void start(){
        setTitle("Sistema de Registro de Asistencia a Eventos (SISRAE)");
        setLayout(new BorderLayout());
        setIconImage(icono); //Cambio el icono de java por el de ADEC
        getContentPane().add(dp);//Agrego el desktopPane al Contenedor principal
        //archivo.addSeparator();
        
        //AGREGAR LISTENERS A ITEMS
        cargar_ar.addActionListener(new Manejador(1));
        guardar_ar.addActionListener(new Manejador(2));
        salir_ar.addActionListener(new Manejador(3));
        respaldo_ar.addActionListener(new Manejador(15));
        
        iniciar_se.addActionListener(new Manejador(4));
        cambiar_se.addActionListener(new Manejador(5));
        cerrar_se.addActionListener(new Manejador(6));
        
        crear_ev.addActionListener(new Manejador(7));
        guardar_ev.addActionListener(new Manejador(8));
        cargar_ev.addActionListener(new Manejador(9));
        
        registrar_as.addActionListener(new Manejador(10));
        modificar_as.addActionListener(new Manejador(11));
        eliminar_as.addActionListener(new Manejador(12));
        asistencia_as.addActionListener(new Manejador(13));
        listados.addActionListener(new Manejador(14));
        criterios.addActionListener(new Manejador(15));
        
        generar_asis.addActionListener(new Manejador(16));
        
        //agregar submenus a MENUS
        archivo.add(cargar_ar);
        archivo.add(guardar_ar);
        archivo.add(salir_ar);
        archivo.add(generar_asis);
        
        
        sesion.add(iniciar_se);
        sesion.add(cambiar_se);
        sesion.add(cerrar_se);
        
        evento.add(crear_ev);
        evento.add(cargar_ev);
        evento.add(guardar_ev);
        
        asistencia.add(registrar_as);
        asistencia.add(modificar_as);
        asistencia.add(eliminar_as);
        asistencia.addSeparator();
        asistencia.add(asistencia_as);
        
        //Agregar Menus a la BARRA de MENUS
        menu.add(archivo);
        menu.add(sesion);
        menu.add(evento);
        menu.add(asistencia);
        menu.add(listados);
        menu.add(criterios);
        
        //
        inicio.add(p1_if); //Se agrega el panel de trabajo al InternalFrame
        inicio.setVisible(true);//Se hace visible el internalFrame

        p2_1.add(menu);
        p2_2.add(reloj);
        
        p2.add(p2_1);
        p2.add(p2_2);
        
        p1_if.add(etiqueta); //agrego Etiqueta al Panel    

        
        add(p2,BorderLayout.NORTH);
        add(inicio,BorderLayout.CENTER); //Agrego el internal frame al Frame principal
        
        setDefaultCloseOperation(this.EXIT_ON_CLOSE); //Poder cerrarse 
        setSize(800, 600);
        setVisible(true); //Ser visible
        setResizable(false);//
    }
    
    
    
    
    private class Manejador implements ActionListener{
        int id;
        Manejador(int id){
            this.id=id;
        }
        
        
        
        
        /**
         * Este es la forma para agregar el panel de cada quien al internal frame.
         * inicio.remove(p1_if);
                    JPanel p=new JPanel();
                    JLabel label=new JLabel("Para iniciar sesion");
                    p.add(label);
                    inicio.add(p);
         * @param e 
         */
        
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (id) {
                case 1:
                    System.out.println("HELP");
                    
                    break;
                case 2:
                    System.out.println("Imprimir");
                    JOptionPane.showMessageDialog(null, "Esta es una version de prueba.\n\nPara desbloquear esta opcion, favor de comprar la licencia por solo ");
                    
                    break;
                case 3:
                    System.out.println("salir archivo");
                    
                    break;
                case 4:
                    System.out.println("Iniciar sesion");
                    
                    break;
                case 5:
                    System.out.println("Cambiar sesion");
                    
                    break;
                case 6:
                    System.out.println("Cerrar sesion");
                    
                    break;
                case 7:
                    System.out.println("Crear evento");
                    new CRCrearEventos(jf).setVisible(true);
                    setVisible(false);
                    break;
                case 8:
                    System.out.println("Cancelar evento");
                    new CRCancelarEvento(jf).setVisible(true);
                    jf.setVisible(false);
                    break;
                case 9:
                    System.out.println("Modificar evento");
                    new CRModificarEvento(jf).setVisible(true);
                    jf.setVisible(false);
                    break;
                case 10:
                    System.out.println("Registrar Asistente");
                    break;
                case 11:
                    System.out.println("Modificar Asistente");
                    break;
                case 12:
                    System.out.println("Eliminar Asistente");
                    break;
                case 13:
                    System.out.println("Asistencias (Asistió o no)");
                    break;
                case 14:
                    System.out.println("Generacion de Listados");
                    break;
                case 15:
                    System.out.println("Generacion del Respaldo");
                    break;
                case 16:
                    Listado_Resultados listado=new Listado_Resultados();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    public static void main(String[] args) {
        new Home();
    }
    
}
