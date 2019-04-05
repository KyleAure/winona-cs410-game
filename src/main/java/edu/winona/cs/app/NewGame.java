package edu.winona.cs.app;

import javax.swing.JFrame;

/**
 * New Game Settings
 * This screen should let users:
 * - Choose their difficulty
 * - Click a button to upload photo, and display photo once chosen
 * - Button to launch game, but is disabled until both of the other options are chosen. 
 * 
 * @author Erika Tix
 */
public class NewGame extends JFrame {
	private static final long serialVersionUID = 7664729301277149568L;
	/**
     * Creates new form GameSettings
     */
    public NewGame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        easyBtn = new javax.swing.JRadioButton();
        mediumBtn = new javax.swing.JRadioButton();
        hardBtn = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(easyBtn);
        easyBtn.setFont(new java.awt.Font("Noteworthy", 0, 18)); // NOI18N
        easyBtn.setSelected(true);
        easyBtn.setText("Easy");
        easyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                easyBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(mediumBtn);
        mediumBtn.setFont(new java.awt.Font("Noteworthy", 0, 18)); // NOI18N
        mediumBtn.setText("Medium");
        mediumBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mediumBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(hardBtn);
        hardBtn.setFont(new java.awt.Font("Noteworthy", 0, 18)); // NOI18N
        hardBtn.setText("Hard");
        hardBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hardBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noteworthy", 1, 32)); // NOI18N
        jLabel1.setText("CHOOSE YOUR DIFFICULTY");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(easyBtn))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mediumBtn)
                            .addComponent(hardBtn))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(easyBtn)
                .addGap(18, 18, 18)
                .addComponent(mediumBtn)
                .addGap(18, 18, 18)
                .addComponent(hardBtn)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void easyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_easyBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_easyBtnActionPerformed

    private void mediumBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mediumBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mediumBtnActionPerformed

    private void hardBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hardBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hardBtnActionPerformed

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
            java.util.logging.Logger.getLogger(NewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewGame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewGame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton easyBtn;
    private javax.swing.JRadioButton hardBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JRadioButton mediumBtn;
    // End of variables declaration//GEN-END:variables
}
