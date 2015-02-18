package arduinodatacatcher;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Antony
 */
public class Main_Window extends javax.swing.JFrame {

    int ancho = 0;
    int alto = 0;
    Functions callFunction = new Functions();

    public Main_Window() {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }

        initComponents();

        ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;

        callFunction.setMainWindow(this, jPanelBackGround);
        callFunction.setButtons(jButtonEjecutar, jButtonAcerca);
        callFunction.setLabel(jLabel1, 0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jPanelBackGround = new javax.swing.JPanel();
        jButtonEjecutar = new javax.swing.JButton();
        jButtonAcerca = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        jButtonEjecutar.setBackground(new java.awt.Color(102, 153, 255));
        jButtonEjecutar.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButtonEjecutar.setForeground(new java.awt.Color(0, 0, 102));
        jButtonEjecutar.setText("Ejecutar Muestreo");
        jButtonEjecutar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonEjecutar.setFocusable(false);
        jButtonEjecutar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonEjecutarMouseMoved(evt);
            }
        });
        jButtonEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjecutarActionPerformed(evt);
            }
        });

        jButtonAcerca.setBackground(new java.awt.Color(102, 153, 255));
        jButtonAcerca.setFont(new java.awt.Font("Century Gothic", 1, 16)); // NOI18N
        jButtonAcerca.setForeground(new java.awt.Color(0, 0, 102));
        jButtonAcerca.setText("Acerca de");
        jButtonAcerca.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButtonAcerca.setFocusable(false);
        jButtonAcerca.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButtonAcercaMouseMoved(evt);
            }
        });
        jButtonAcerca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAcercaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Century Gothic", 0, 16)); // NOI18N
        jLabel1.setText("Escoja cualquiera de las opciones disponibles");

        javax.swing.GroupLayout jPanelBackGroundLayout = new javax.swing.GroupLayout(jPanelBackGround);
        jPanelBackGround.setLayout(jPanelBackGroundLayout);
        jPanelBackGroundLayout.setHorizontalGroup(
            jPanelBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackGroundLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanelBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEjecutar)
                    .addComponent(jButtonAcerca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(206, Short.MAX_VALUE))
        );
        jPanelBackGroundLayout.setVerticalGroup(
            jPanelBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBackGroundLayout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addGroup(jPanelBackGroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEjecutar)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAcerca)
                .addGap(212, 212, 212))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackGround, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelBackGround, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonEjecutarMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonEjecutarMouseMoved

        jLabel1.setText("<html><p align=\"justify\">Esta opción permite iniciar un muestreo"
                + " en <b>Tiempo Real</b>, donde los datos serán tabulados"
                + " a medida que son recibidos desde Arduino a través del Puerto Serie.</p></html>");
        callFunction.setLabel(jLabel1, 1);
    }//GEN-LAST:event_jButtonEjecutarMouseMoved

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        jLabel1.setText("Escoja cualquiera de las opciones disponibles.");
        callFunction.setLabel(jLabel1, 0);

    }//GEN-LAST:event_formMouseMoved

    private void jButtonAcercaMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAcercaMouseMoved
        jLabel1.setText("<html><p>Información sobre los creadores de este software. Visita <b>PanamaHitek.com</b></p></html>");
        callFunction.setLabel(jLabel1, 2);
    }//GEN-LAST:event_jButtonAcercaMouseMoved

    private void jButtonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjecutarActionPerformed
        Window ventana = new Window();
        ventana.setVisible(true);

    }//GEN-LAST:event_jButtonEjecutarActionPerformed

    private void jButtonAcercaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAcercaActionPerformed
        Acerca A = new Acerca();
        A.setLocationRelativeTo(null);
        A.setVisible(true);
    }//GEN-LAST:event_jButtonAcercaActionPerformed

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
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAcerca;
    private javax.swing.JButton jButtonEjecutar;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelBackGround;
    // End of variables declaration//GEN-END:variables
}
