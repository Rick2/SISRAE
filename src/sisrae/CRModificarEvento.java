/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sisrae;

import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Chess
 */
public class CRModificarEvento extends javax.swing.JFrame {

    /**
     * Creates new form CRModificarEvento
     */
    ArrayList<Integer> eliminar;
    int contador;
    JFrame jfm = this;
    JFrame jf;

    CRModificarEvento(JFrame jf) {
        initComponents();
        this.jf = jf; //To change body of generated methods, choose Tools | Templates.
        Image imagen = new ImageIcon("img/IconoAdec.jpg").getImage();
        setIconImage(imagen);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotonActualiza = new javax.swing.JButton();
        BotonCancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Selecciona = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MODIFICAR EVENTO");
        setPreferredSize(new java.awt.Dimension(565, 197));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        BotonActualiza.setText("Aceptar");
        BotonActualiza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonActualizaActionPerformed(evt);
            }
        });

        BotonCancelar.setText("Cancelar");
        BotonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCancelarActionPerformed(evt);
            }
        });

        jLabel1.setText("EVENTO");

        Selecciona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SeleccionaMouseClicked(evt);
            }
        });
        Selecciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Selecciona, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(BotonActualiza)
                .addGap(41, 41, 41)
                .addComponent(BotonCancelar)
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Selecciona, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonActualiza)
                    .addComponent(BotonCancelar))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.jf.setVisible(true);
        this.jf.show();
    }//GEN-LAST:event_formWindowClosing

    private void SeleccionaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SeleccionaMouseClicked
        try {
            CRConsultasRegistroDeEventos consulta = new CRConsultasRegistroDeEventos();
            DefaultComboBoxModel modelo = new DefaultComboBoxModel();
            ArrayList<String> arr = consulta.GetSeleccionaEvento();
//            for (String arr1 : arr) {
//                modelo.addElement(arr1);
//                System.out.println(arr1);
//            }
            Selecciona.setModel(modelo);
        } catch (SQLException | InstantiationException ex) {
            Logger.getLogger(CRCrearEventos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_SeleccionaMouseClicked

    private void BotonActualizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonActualizaActionPerformed
        if (Selecciona.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "SELECCIONA EVENTO", "error", JOptionPane.ERROR_MESSAGE);
        } else {
            CRModifica verformulario2 = new CRModifica(jfm);
            verformulario2.setVisible(true);
            this.setVisible(false);
//            this.show(false);
            verformulario2.Inicar(contador);
        }
    }//GEN-LAST:event_BotonActualizaActionPerformed

    private void SeleccionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionaActionPerformed
        CRConsultasRegistroDeEventos consulta = new CRConsultasRegistroDeEventos();
        try {
            eliminar = consulta.GetIndiceEnArray();
        } catch (SQLException ex) {
            Logger.getLogger(CRCancelarEvento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(CRCancelarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
//        for (int i = 0; i < this.eliminar.size(); i++) {
//            int variable = this.eliminar.get(i);
//            System.out.println("LISTA DE ENTEROS ACTIONPERFORMED POSICION " + i + " = " + variable);
//        }
        consulta.SetContador(eliminar.get(Selecciona.getSelectedIndex()));
        contador = consulta.getCont();
        System.out.println("ID DEL ELEMENTO A ACTUALIZAR: " + contador);
//        System.out.println("INDEX BOTON = " + Selecciona.getSelectedIndex() + "  ITEM BOTON -> " + Selecciona.getSelectedItem());
        Selecciona.enable(true);
    }//GEN-LAST:event_SeleccionaActionPerformed

    private void BotonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCancelarActionPerformed
        jf.show();
        this.setVisible(false);
    }//GEN-LAST:event_BotonCancelarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonActualiza;
    private javax.swing.JButton BotonCancelar;
    private javax.swing.JComboBox Selecciona;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}