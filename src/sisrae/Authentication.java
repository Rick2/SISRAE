/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sisrae;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.*;

/**
 *@author Rick
 * Esta clase es la encargada de mostrar la pantalla de autenticacion segun el usuario
 * que valla a trabajar con el sistema
 * 
 */
public class Authentication {
    JFrame sesion=new JFrame("Iniciar Sesion como");
    Image icono= new ImageIcon("img/IconoAdec.jpg").getImage();
    JPanel p_principal=new JPanel(new BorderLayout()),
            p_arriba=new JPanel(new FlowLayout()),
            p_abajo=new JPanel(new FlowLayout()),
            p_izquierda=new JPanel(new FlowLayout()),
            p_centro=new JPanel(new GridLayout(2, 2)),
            p_derecha=new JPanel(new FlowLayout());
    JLabel  l_indicaciones=new JLabel("Indicaciones"),
            l_usuario=new JLabel("Usuario"),
            l_contraseña=new JLabel("Contraseña"),
            l_error=new JLabel("Errores");
    JPasswordField pas_field =new JPasswordField();
    JTextField tf_usuario=new JTextField(20);
    
    
    Authentication(){
        start();
    }
    
    
    void start(){
        
        p_arriba.add(l_indicaciones);
        p_abajo.add(l_error);
        p_centro.add(l_usuario);
        p_centro.add(tf_usuario);
        p_centro.add(l_contraseña);
        p_centro.add(pas_field);
        
        p_arriba.setBackground(Color.red);
        p_abajo.setBackground(Color.blue);
        p_derecha.setBackground(Color.yellow);
        p_izquierda.setBackground(Color.gray);
        p_centro.setBackground(Color.green);
        
        p_principal.add(p_arriba,BorderLayout.NORTH);
        p_principal.add(p_abajo, BorderLayout.SOUTH);
        p_principal.add(p_derecha,BorderLayout.EAST);
        p_principal.add(p_izquierda,BorderLayout.WEST);
        p_principal.add(p_centro,BorderLayout.CENTER);
        
        sesion.add(p_principal);
        sesion.setDefaultCloseOperation(sesion.EXIT_ON_CLOSE);
        sesion.setVisible(true);
        sesion.pack();
        
    }
    public static void main(String[] args) {
        new Authentication();
        
    }
    
    
}
