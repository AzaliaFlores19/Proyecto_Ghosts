/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Azalia
 */
public class Dificultad extends javax.swing.JFrame {
Player datos;
    Usuarios1 usuario;
     Login1 login;
     Configuracion1 configuracion;
    /**
     * Creates new form Dificultad
     */
    public Dificultad(Login1 login) {
        initComponents();
         setLocationRelativeTo(this);
        this.login=login;
        datos=login.getDatos();
         configuracion=new Configuracion1(login,datos);  
  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnNormal = new javax.swing.JButton();
        btnExpert = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnGenius = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 102, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setText("Dificultad");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 230, -1));

        btnNormal.setBackground(new java.awt.Color(153, 0, 102));
        btnNormal.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        btnNormal.setForeground(new java.awt.Color(255, 255, 255));
        btnNormal.setText("Normal");
        btnNormal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNormalActionPerformed(evt);
            }
        });
        jPanel1.add(btnNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, -1, -1));

        btnExpert.setBackground(new java.awt.Color(153, 0, 102));
        btnExpert.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        btnExpert.setForeground(new java.awt.Color(255, 255, 255));
        btnExpert.setText("Expert");
        btnExpert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExpertActionPerformed(evt);
            }
        });
        jPanel1.add(btnExpert, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, -1, -1));

        btnBack.setBackground(new java.awt.Color(153, 0, 102));
        btnBack.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 390, -1, -1));

        btnGenius.setBackground(new java.awt.Color(153, 0, 102));
        btnGenius.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        btnGenius.setForeground(new java.awt.Color(255, 255, 255));
        btnGenius.setText("Genius");
        btnGenius.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGeniusActionPerformed(evt);
            }
        });
        jPanel1.add(btnGenius, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo sub sub.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNormalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNormalActionPerformed
        // TODO add your handling code here:
        login.dificultad="normal";
       JOptionPane.showMessageDialog(null, "Ahora estas en modo normal");
  //volver submenu configuracion
        configuracion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnNormalActionPerformed

    private void btnExpertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExpertActionPerformed
        // TODO add your handling code here:
        login.dificultad="expert";
           JOptionPane.showMessageDialog(null, "Ahora estas en modo expert");
           //volver submenu configuracion
         configuracion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnExpertActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        configuracion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnGeniusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGeniusActionPerformed
        // TODO add your handling code here:
        login.dificultad="genius";
         JOptionPane.showMessageDialog(null, "Ahora estas en modo genius");
         //volver submenu configuracion
         configuracion.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnGeniusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnExpert;
    private javax.swing.JButton btnGenius;
    private javax.swing.JButton btnNormal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
