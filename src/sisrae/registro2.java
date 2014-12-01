/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sisrae;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.*;
import java.awt.GridLayout;
import java.awt.Image;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class registro2 extends JPanel
                             implements ActionListener {
    static private final String newline = "\n";
    JButton aceptar, saveButton;
    JFileChooser fc;   
    JTextField jtf[] = new JTextField[10];
    JLabel jl[] = new JLabel[10];
    

    Image icono=new ImageIcon("img/IconoAdec.jpg").getImage();
    JSeparator js;
    

    public registro2() {
        super(new BorderLayout());
        
        
        jtf[0] = new JTextField(10);
        jtf[1] = new JTextField(10);
        jtf[2] = new JTextField(10);
        jtf[3] = new JTextField(10);
        jtf[4] = new JTextField(10);
        jtf[5] = new JTextField(10);

        jl[0] = new JLabel("Ingrese codigo de barras.", JLabel.LEFT);
        jl[1] = new JLabel("Captura manual:", JLabel.LEFT);
        jl[2] = new JLabel("Numero de Resgistro", JLabel.LEFT);
        jl[3] = new JLabel("hora de inicio", JLabel.LEFT);
        jl[4] = new JLabel("hora de termino", JLabel.LEFT);
        jl[5] = new JLabel("calificacion", JLabel.LEFT);
       
        for (int i = 6; i < jl.length; i++) {
            jl[i]=new JLabel("");
        }
        
        

       
        fc = new JFileChooser();
        aceptar = new JButton("aceptar");
        aceptar.addActionListener(this);
        //Create the save button.  We use the image from the JLF
        //Graphics Repository (but we extracted it from the jar).
        saveButton = new JButton("finalizar y guardar archivo.",
                                 createImageIcon("images/Save16.gif"));
        saveButton.addActionListener(this);

        //For layout purposes, put the buttons in a separate panel
        JPanel buttonPanel = new JPanel(); //use FlowLayout
        buttonPanel.add(aceptar);
        buttonPanel.add(saveButton);
        JPanel p1= new   JPanel();
        p1.setLayout(new GridLayout(0,2,1,1));
        p1.add(jl[0]);          
        p1.add(jtf[0]);
        p1.add(jl[1]); 
        p1.add(jtf[1]);
        p1.add(jl[2]); 
        p1.add(jtf[2]);
        p1.add(jl[3]);
        p1.add(jtf[3]);
        p1.add(jl[4]);
        p1.add(jtf[4]);
        p1.add(jl[5]);  
        p1.add(jtf[5]);
       
        

        //Add the buttons and the log to this panel.
        add(buttonPanel, BorderLayout.PAGE_END);
        add(p1,BorderLayout.CENTER);
       
    }

    public void actionPerformed(ActionEvent e) {

        //Handle open button action.
        if (e.getSource() == aceptar) {
           

        //Handle save button action.
        } else if (e.getSource() == saveButton) {
            int returnVal = fc.showSaveDialog(registro2.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                //This is where a real application would save the file.
          String path= file.getPath();
                try {
                    CrearArchivoR ca=new CrearArchivoR(path);
                } catch (SQLException ex) {
                    Logger.getLogger(registro2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null,"Save command cancelled by user." + newline);
            }
            
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = registro2.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("registro2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Add content to the window.
        frame.add(new registro2());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE); 
                createAndShowGUI();
            }
        });
    }
}

