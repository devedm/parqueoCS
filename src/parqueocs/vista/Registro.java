/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package parqueocs.vista;

import parqueocs.modelo.Usuario;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author minio
 */
public class Registro extends javax.swing.JFrame {

    /**
     * Creates new form Registro
     */
    public Registro() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Sistema de Parqueo - Registro");
    }

    public JButton getBtnRegistrarse() {
        return btnRegistrarse;
    }

    public void setBtnRegistrarse(JButton btnRegistrarse) {
        this.btnRegistrarse = btnRegistrarse;
    }

    public JTextField getFieldCedula() {
        return fieldCedula;
    }

    public void setFieldCedula(JTextField fieldCedula) {
        this.fieldCedula = fieldCedula;
    }

    public JPasswordField getFieldConfContrasenia() {
        return fieldConfContrasenia;
    }

    public void setFieldConfContrasenia(JPasswordField fieldConfContrasenia) {
        this.fieldConfContrasenia = fieldConfContrasenia;
    }

    public JPasswordField getFieldContrasenia() {
        return fieldContrasenia;
    }

    public void setFieldContrasenia(JPasswordField fieldContrasenia) {
        this.fieldContrasenia = fieldContrasenia;
    }

    public JTextField getFieldUsuario() {
        return fieldUsuario;
    }

    public void setFieldUsuario(JTextField fieldUsuario) {
        this.fieldUsuario = fieldUsuario;
    }

    public JLabel getjLabel3() {
        return jLabel3;
    }

    public void setjLabel3(JLabel jLabel3) {
        this.jLabel3 = jLabel3;
    }

    public JLabel getLabelCedula() {
        return labelCedula;
    }

    public void setLabelCedula(JLabel labelCedula) {
        this.labelCedula = labelCedula;
    }

    public JLabel getLabelConfContrasenia() {
        return labelConfContrasenia;
    }

    public void setLabelConfContrasenia(JLabel labelConfContrasenia) {
        this.labelConfContrasenia = labelConfContrasenia;
    }

    public JLabel getLabelContrasenia() {
        return labelContrasenia;
    }

    public void setLabelContrasenia(JLabel labelContrasenia) {
        this.labelContrasenia = labelContrasenia;
    }

    public JLabel getLabelUsuario() {
        return labelUsuario;
    }

    public void setLabelUsuario(JLabel labelUsuario) {
        this.labelUsuario = labelUsuario;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        btnRegistrarse = new javax.swing.JButton();
        fieldCedula = new javax.swing.JTextField();
        labelCedula = new javax.swing.JLabel();
        labelContrasenia = new javax.swing.JLabel();
        labelConfContrasenia = new javax.swing.JLabel();
        fieldContrasenia = new javax.swing.JPasswordField();
        fieldConfContrasenia = new javax.swing.JPasswordField();
        fieldUsuario = new javax.swing.JTextField();
        labelUsuario = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Calibri", 1, 32)); // NOI18N
        jLabel3.setText("Registro");

        btnRegistrarse.setBackground(new java.awt.Color(45, 54, 72));
        btnRegistrarse.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        btnRegistrarse.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarse.setText("Registrarse");
        btnRegistrarse.setAlignmentX(0.5F);
        btnRegistrarse.setBorderPainted(false);
        btnRegistrarse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRegistrarse.setMargin(new java.awt.Insets(5, 14, 3, 14));
        btnRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarseActionPerformed(evt);
            }
        });

        labelCedula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelCedula.setText("Cedula");

        labelContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelContrasenia.setText("Contraseña");

        labelConfContrasenia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelConfContrasenia.setText("Confirmar Contraseña");

        labelUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labelUsuario.setText("Usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelConfContrasenia)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelContrasenia)
                                .addComponent(labelCedula)
                                .addComponent(fieldCedula)
                                .addComponent(fieldContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(fieldConfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUsuario)
                            .addComponent(fieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jLabel3)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(37, 37, 37)
                .addComponent(labelCedula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelContrasenia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelConfContrasenia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldConfContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegistrarse, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRegistrarseActionPerformed

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnRegistrarse;
    public javax.swing.JTextField fieldCedula;
    public javax.swing.JPasswordField fieldConfContrasenia;
    public javax.swing.JPasswordField fieldContrasenia;
    public javax.swing.JTextField fieldUsuario;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelCedula;
    private javax.swing.JLabel labelConfContrasenia;
    private javax.swing.JLabel labelContrasenia;
    private javax.swing.JLabel labelUsuario;
    // End of variables declaration//GEN-END:variables
}
