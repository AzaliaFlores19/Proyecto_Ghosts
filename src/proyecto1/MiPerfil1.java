/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Azalia
 */
public class MiPerfil1 extends javax.swing.JFrame {
    Login1 login;
    Player datos;
    Menu1 menu;
    Cambiar_Password1 cambiarPassword;
    VerMisDatos misDatos;
    /**
     * Creates new form MiPerfil1
     */
    public MiPerfil1(Login1 login,Player datos) {
        initComponents();
         setLocationRelativeTo(this);
        this.datos=datos;
        this.login=login;
        menu=new Menu1(login,datos);

        lblUsuarioLogeado.setText(datos.UsuarioLogeado);
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
        btnVerDatos = new javax.swing.JButton();
        btnCambiarPassword = new javax.swing.JButton();
        btnEliminarCuenta = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        lblUsuarioLogeado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 102, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setText("Mi Perfil");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 200, -1));

        btnVerDatos.setBackground(new java.awt.Color(153, 0, 153));
        btnVerDatos.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnVerDatos.setForeground(new java.awt.Color(255, 255, 255));
        btnVerDatos.setText("Ver Mis Datos");
        btnVerDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDatosActionPerformed(evt);
            }
        });
        jPanel1.add(btnVerDatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));

        btnCambiarPassword.setBackground(new java.awt.Color(153, 0, 153));
        btnCambiarPassword.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnCambiarPassword.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiarPassword.setText("Cambiar Password");
        btnCambiarPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarPasswordActionPerformed(evt);
            }
        });
        jPanel1.add(btnCambiarPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, -1));

        btnEliminarCuenta.setBackground(new java.awt.Color(153, 0, 153));
        btnEliminarCuenta.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnEliminarCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCuenta.setText("Eliminar Cuenta");
        btnEliminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarCuentaActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminarCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, -1, -1));

        btnBack.setBackground(new java.awt.Color(153, 0, 153));
        btnBack.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 310, -1, -1));

        lblUsuarioLogeado.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        lblUsuarioLogeado.setForeground(new java.awt.Color(255, 51, 0));
        lblUsuarioLogeado.setText("----");
        jPanel1.add(lblUsuarioLogeado, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 80, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondo sub.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, -1));

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

    private void btnVerDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDatosActionPerformed
        // TODO add your handling code here:
        misDatos=new VerMisDatos(login,datos);
        misDatos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVerDatosActionPerformed

    private void btnCambiarPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarPasswordActionPerformed
        // TODO add your handling code here:
         cambiarPassword=new Cambiar_Password1(login);
        cambiarPassword.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCambiarPasswordActionPerformed

    private void btnEliminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarCuentaActionPerformed
        // TODO add your handling code here:
         EliminarCuenta1 eliminar =new EliminarCuenta1(login);
        eliminar.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEliminarCuentaActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
         menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

   
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCambiarPassword;
    private javax.swing.JButton btnEliminarCuenta;
    private javax.swing.JButton btnVerDatos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblUsuarioLogeado;
    // End of variables declaration//GEN-END:variables
}