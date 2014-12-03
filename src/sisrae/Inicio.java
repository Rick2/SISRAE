package interfaces;

import bd.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Inicio extends javax.swing.JFrame {
    private final Conexion c = new Conexion();
    private final ArrayList<String> id_eventos = new ArrayList();
    private final Registro r = new Registro(c);
    
    public Inicio() throws SQLException {
        initComponents();
        llenarCombo();
    }
    
    private void llenarCombo() throws SQLException{
        ResultSet rs = c.executeQuery("SELECT `id_evento`, `Descripcion` FROM `evento`");
        while(rs.next()){
            eventos.addItem(rs.getString("Descripcion"));
            id_eventos.add(rs.getInt("id_evento")+"");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        evento = new javax.swing.JTextField();
        eventos = new javax.swing.JComboBox();
        fecha = new javax.swing.JTextField();
        hora = new javax.swing.JTextField();
        lugar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Seleccione el evento:");

        jLabel2.setText("Evento:");

        jLabel3.setText("Fecha:");

        jLabel4.setText("Hora:");

        jLabel5.setText("Lugar:");

        evento.setEditable(false);

        eventos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eventosActionPerformed(evt);
            }
        });

        fecha.setEditable(false);

        hora.setEditable(false);

        lugar.setEditable(false);
        lugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lugarActionPerformed(evt);
            }
        });

        jButton1.setText("Iniciar Registro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(evento)
                    .addComponent(fecha)
                    .addComponent(hora)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lugar))
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(eventos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(evento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3))
                            .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addComponent(hora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lugar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lugarActionPerformed
        
    }//GEN-LAST:event_lugarActionPerformed

    private void eventosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eventosActionPerformed
        try{
            if(id_eventos.size()>0){
                ResultSet rs = c.executeQuery("select e.Descripcion, s.fecha, e.comienza_hora, l.domicilio, l.ciudad "
                        + "from sede s join evento e on s.id_evento = e.id_evento "
                        + "join lugar l on l.id_lugar = s.id_lugar where e.id_evento = "+
                        id_eventos.get(eventos.getSelectedIndex()));
                rs.next();
                evento.setText(rs.getString("Descripcion"));
                fecha.setText(rs.getString("fecha"));
                hora.setText(rs.getString("comienza_hora"));
                lugar.setText(rs.getString("domicilio") + ", "+rs.getString("ciudad"));
            }
        }catch(SQLException s){
            System.out.println("error 5");
        }
    }//GEN-LAST:event_eventosActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(id_eventos.size()>0){
            r.setId_evento(Integer.parseInt(id_eventos.get(eventos.getSelectedIndex())));
            r.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "Elije un evento");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) throws SQLException {
        new Inicio().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField evento;
    private javax.swing.JComboBox eventos;
    private javax.swing.JTextField fecha;
    private javax.swing.JTextField hora;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField lugar;
    // End of variables declaration//GEN-END:variables
}
