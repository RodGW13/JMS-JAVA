/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente1;
import java.awt.event.KeyEvent;
import javax.jms.*;
import javax.naming.*;
import javax.swing.JOptionPane;


/**
 *
 * @author andrea
 */
public class Jfr_Chat extends javax.swing.JFrame {
    
    private QueueSender qs=null;
    private QueueConnection qc=null;
    private QueueSession qss=null;
    
    
    /**
     * Creates new form Jfr_Chat
     */
    public Jfr_Chat() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        txt_Contenido = new javax.swing.JTextField();
        Btn_Conectar = new javax.swing.JButton();
        Btn_Enviar = new javax.swing.JButton();
        Btn_salir = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cliente 1");

        txt_Contenido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_ContenidoKeyTyped(evt);
            }
        });

        Btn_Conectar.setText("Conectar");
        Btn_Conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_ConectarActionPerformed(evt);
            }
        });

        Btn_Enviar.setText("Enviar");
        Btn_Enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_EnviarActionPerformed(evt);
            }
        });

        Btn_salir.setText("Salir");
        Btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Btn_Conectar)
                        .addGap(32, 32, 32)
                        .addComponent(Btn_Enviar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Btn_salir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_Contenido, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(txt_Contenido, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Conectar)
                    .addComponent(Btn_Enviar)
                    .addComponent(Btn_salir))
                .addContainerGap(191, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Btn_ConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_ConectarActionPerformed
        try {
            Context ctx=new InitialContext();
            QueueConnectionFactory qcf= (QueueConnectionFactory) ctx.lookup("jms/ra");
            
            Queue q=(Queue) ctx.lookup("Chat");
            qc= qcf.createQueueConnection();
            qss=qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            qs=qss.createSender(q);
            qc.start();
            this.Btn_Conectar.setVisible(false);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se puede conectar");
        }
        
    }//GEN-LAST:event_Btn_ConectarActionPerformed

    private void Btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_salirActionPerformed
        try {
            qc.close();
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en Salida");
        }
      
    }//GEN-LAST:event_Btn_salirActionPerformed

    private void Btn_EnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_EnviarActionPerformed
       Envio();
    }//GEN-LAST:event_Btn_EnviarActionPerformed

    private void txt_ContenidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ContenidoKeyTyped
        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        Envio();
    }//GEN-LAST:event_txt_ContenidoKeyTyped

    private void Envio(){  
        try {
           TextMessage tx=qss.createTextMessage(this.txt_Contenido.getText());
           qs.send(tx);
           this.txt_Contenido.setText("");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en envio");
        }
    
    }                                          

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Jfr_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jfr_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jfr_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jfr_Chat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jfr_Chat().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_Conectar;
    private javax.swing.JButton Btn_Enviar;
    private javax.swing.JButton Btn_salir;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField txt_Contenido;
    // End of variables declaration//GEN-END:variables
}
