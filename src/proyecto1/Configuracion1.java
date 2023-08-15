/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Azalia
 */
public class Configuracion1 extends javax.swing.JFrame {
 Player datos;
    Login1 login;
   Menu1 menu;
   ModoJuego modo;
   Dificultad dificultad;
   private String selectedDifficulty="";

    /**
     * Creates new form Configuracion1
     */
    public Configuracion1(Login1 login,Player datos) {
        initComponents();
         setLocationRelativeTo(this);
         this.datos=datos;
         this.login=login;
         menu=new Menu1(login,datos); 
    }
    
    // Método para establecer la dificultad seleccionada desde la clase Dificultad
    public void setDifficulty(String difficulty) {
        selectedDifficulty = difficulty;
    }

    // Método para obtener la dificultad seleccionada
    public String getDifficulty() {
        return selectedDifficulty;
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
        btnDificultad = new javax.swing.JButton();
        btnModoJuego = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 102, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 0));
        jLabel1.setText("Configuracion");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 310, -1));

        btnDificultad.setBackground(new java.awt.Color(153, 0, 102));
        btnDificultad.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnDificultad.setForeground(new java.awt.Color(255, 255, 255));
        btnDificultad.setText("Dificultad");
        btnDificultad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDificultadActionPerformed(evt);
            }
        });
        jPanel1.add(btnDificultad, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, -1, -1));

        btnModoJuego.setBackground(new java.awt.Color(153, 0, 102));
        btnModoJuego.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnModoJuego.setForeground(new java.awt.Color(255, 255, 255));
        btnModoJuego.setText("Modo De Juego");
        btnModoJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModoJuegoActionPerformed(evt);
            }
        });
        jPanel1.add(btnModoJuego, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, -1, -1));

        btnBack.setBackground(new java.awt.Color(153, 0, 102));
        btnBack.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        jPanel1.add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, -1, -1));

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

    private void btnDificultadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDificultadActionPerformed
        // TODO add your handling code here:
        dificultad=new Dificultad(login);
        dificultad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnDificultadActionPerformed

    private void btnModoJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModoJuegoActionPerformed
        // TODO add your handling code here:
       
          modo=new ModoJuego(login);
        modo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnModoJuegoActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
         menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDificultad;
    private javax.swing.JButton btnModoJuego;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
