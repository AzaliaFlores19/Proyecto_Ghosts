/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1;

import javax.swing.JOptionPane;

/**
 *
 * @author Azalia
 */
public class SegundoPlayer1 extends javax.swing.JFrame {
Player datos;
Login1 login;
Menu1 menu;

    /**
     * Creates new form SegundoPlayer1
     */
    public SegundoPlayer1(Login1 login,Player datos) {
        initComponents();
        setLocationRelativeTo(this);
         this.datos=datos;  
        this.login=login;
        menu=new Menu1(login, datos);
        lblPlayer1.setText(datos.UsuarioLogeado);
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
        jLabel2 = new javax.swing.JLabel();
        lblPlayer1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPlayer2 = new javax.swing.JTextField();
        btnJugar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Player 1: ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Player 2: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        lblPlayer1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        lblPlayer1.setForeground(new java.awt.Color(255, 51, 0));
        jPanel1.add(lblPlayer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, 240, 28));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 51));
        jLabel3.setText("Players");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 144, -1));

        txtPlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPlayer2ActionPerformed(evt);
            }
        });
        jPanel1.add(txtPlayer2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 280, -1));

        btnJugar.setBackground(new java.awt.Color(0, 102, 153));
        btnJugar.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnJugar.setText("Jugar");
        btnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJugarActionPerformed(evt);
            }
        });
        jPanel1.add(btnJugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, -1, -1));

        btnSalir.setBackground(new java.awt.Color(0, 102, 153));
        btnSalir.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo sub.jpg"))); // NOI18N
        jLabel4.setText("jLabel4");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 570, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPlayer2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPlayer2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPlayer2ActionPerformed

    private void btnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJugarActionPerformed
        // TODO add your handling code here:
        String segundoplayer = txtPlayer2.getText();
        Usuarios1 user = datos.buscarUsuario(segundoplayer);
        if(txtPlayer2.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Llene todos los campos.");
        }else{
            if(user!= null){
                if(segundoplayer.equals(String.valueOf(this.datos.UsuarioLogeado))){
                    JOptionPane.showMessageDialog(null, "El segundo player no puede ser el mismo que el primer player.");
                }else{
                    if(user.getUsername().equals(segundoplayer)){
                        this.datos.SegundoUsuario = segundoplayer;
                        Juego juego = new Juego(login,datos);
                        juego.setVisible(true);
                        this.dispose();
                    }

                }
            }else{
                JOptionPane.showMessageDialog(null, "El username del segundo player no existe.");
            }

        }

    }//GEN-LAST:event_btnJugarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJugar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblPlayer1;
    private javax.swing.JTextField txtPlayer2;
    // End of variables declaration//GEN-END:variables
}
