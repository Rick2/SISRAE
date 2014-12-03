package interfaces;

import bd.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

public class Registro extends javax.swing.JFrame {
    private int id_evento;
    private final Conexion c;
    public Registro(Conexion c) {
        this.c = c;
        initComponents();
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public void setId_evento(int id_evento) {
        this.id_evento = id_evento;
    }
    
    private boolean vacios(){
        return nombre.getText().equals("") ||
                hora_entrada.getText().equals("") ||
                hora_salida.getText().equals("") ||
                calificacion.getText().equals("");
    }
    
    private boolean existeNombre() throws SQLException{
        ResultSet rs = c.executeQuery("Select nombre from participante where nombre = '"+nombre.getText()+"'");
        return rs.next();
    }
    
    private boolean formatoHora(){
        if(hora_entrada.getText().length()!=5) return false;
        if(hora_salida.getText().length()!=5) return false;
        
        if(!esNumero(hora_entrada.getText().charAt(0))) return false;
        if(!esNumero(hora_entrada.getText().charAt(1))) return false;
        if(hora_entrada.getText().charAt(2) != ':') return false;
        if(!esNumero(hora_entrada.getText().charAt(3))) return false;
        if(!esNumero(hora_entrada.getText().charAt(4))) return false;
        
        if(!esNumero(hora_salida.getText().charAt(0))) return false;
        if(!esNumero(hora_salida.getText().charAt(1))) return false;
        if(hora_salida.getText().charAt(2) != ':') return false;
        if(!esNumero(hora_salida.getText().charAt(3))) return false;
        if(!esNumero(hora_salida.getText().charAt(4))) return false;
        
        int n1 = Integer.parseInt(hora_entrada.getText().charAt(0)+""+hora_entrada.getText().charAt(1));
        int n2 = Integer.parseInt(hora_entrada.getText().charAt(3)+""+hora_entrada.getText().charAt(4));
        
        if(n1<0 || n1>23) return false;
        if(n2<0 || n2>60) return false;
        
        n1 = Integer.parseInt(hora_salida.getText().charAt(0)+""+hora_salida.getText().charAt(1));
        n2 = Integer.parseInt(hora_salida.getText().charAt(3)+""+hora_salida.getText().charAt(4));
        
        if(n1<0 || n1>23) return false;
        if(n2<0 || n2>60) return false;
        
        return true;
    }
    
    private int hora_limite = 30;
    private boolean horasValidas(){
        int h1 = Integer.parseInt(hora_entrada.getText().charAt(0)+""+hora_entrada.getText().charAt(1));
        int h2 = Integer.parseInt(hora_entrada.getText().charAt(3)+""+hora_entrada.getText().charAt(4));
        
        int h3 = Integer.parseInt(hora_salida.getText().charAt(0)+""+hora_salida.getText().charAt(1));
        int h4 = Integer.parseInt(hora_salida.getText().charAt(3)+""+hora_salida.getText().charAt(4));
        
        if(h1>h3) return true;
        if(h1<h3) return false;
        if(h1==h3)
            if(h4-h2 < hora_limite) return false;
        
        return true;
    }
    
    private boolean esNumero(char caracter){
        return caracter>=48 && caracter<=57;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nombre = new javax.swing.JTextField();
        hora_entrada = new javax.swing.JTextField();
        hora_salida = new javax.swing.JTextField();
        calificacion = new javax.swing.JTextField();
        guardar = new javax.swing.JButton();
        terminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ingrese codigo barras:");

        jLabel2.setText("Captura manual:");

        jLabel3.setText("Nombre:");

        jLabel4.setText("Hora entrada:");

        jLabel5.setText("Hora salida:");

        jLabel6.setText("Calificacion:");

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        terminar.setText("Terminar y generar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nombre)
                            .addComponent(hora_entrada)
                            .addComponent(hora_salida)
                            .addComponent(calificacion))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addComponent(terminar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(calificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel4))
                                    .addComponent(hora_entrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5))
                            .addComponent(hora_salida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(terminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        try{
            if(!vacios())
            if(existeNombre())
            if(formatoHora())
            if(horasValidas()){
                ResultSet rs = c.executeQuery("select id_participante from participante where nombre = '"+nombre.getText()+"'");
                rs.next();
                String id_participante = rs.getInt("id_participante")+"";
                
                c.executeUpdate("INSERT INTO registro (id_participante, id_evento, entrada, salida, calificacion) VALUES"
                        + " ("+id_participante+", "+id_evento+", '"+hora_entrada.getText()+"', '"+hora_salida.getText()+"', '"+calificacion.getText()+"')");
                
                nombre.setText("");
                hora_salida.setText("");
                hora_entrada.setText("");
                calificacion.setText("");
                
                JOptionPane.showMessageDialog(this, "Se inserto el registro.");
            }
            else JOptionPane.showMessageDialog(this, "La hora de salida es menor o igual a la de entrada.\n"
                    + "O no hay un limite de "+hora_limite+" minutos");
            else JOptionPane.showMessageDialog(this, "El formato de hora es incorrecto, debe ser de la forma XX:XX\n"
                    + "Se debe usar un formato de 24 horas, ejemplo: 17:30 o 05:20");
            else JOptionPane.showMessageDialog(this, "El nombre del participante no existe");
            else JOptionPane.showMessageDialog(this, "Hay campos vacios");
        }catch(SQLException s){
            System.out.println("error 6");
        }
    }//GEN-LAST:event_guardarActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField calificacion;
    private javax.swing.JButton guardar;
    private javax.swing.JTextField hora_entrada;
    private javax.swing.JTextField hora_salida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField nombre;
    private javax.swing.JButton terminar;
    // End of variables declaration//GEN-END:variables
}
