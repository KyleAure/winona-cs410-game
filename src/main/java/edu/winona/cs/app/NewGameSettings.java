
package edu.winona.cs.app;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.winona.cs.gamelogic.DifficultyLevel;


/**
 * New Game Settings
 * This screen should let users:
 * - Choose their difficulty
 * - Click a button to upload photo, and display photo once chosen
 * - Button to launch game, but is disabled until both of the other options are chosen. 
 * 
 * @author Erika Tix
 */
public class NewGameSettings extends javax.swing.JFrame {
    private static final long serialVersionUID = 7664729301277149568L;
    
    
    ButtonGroup buttonGroup = new ButtonGroup();
    File file = null;

    /**
     * Creates new form NewGameSettings
     */
    public NewGameSettings() {
        initComponents();
        
        //add radio buttons to the button group
        buttonGroup.add(easyBtn);
        buttonGroup.add(mediumBtn);
        buttonGroup.add(hardBtn);
        
        //automatically set the playButton to be unclickable
        playBtn.setEnabled(false);
        
        
        Container a = NewGameSettings.this.getContentPane();
        a.setBackground(Color.PINK);
        
        Container c = NewGameSettings.this.playBtn;
        c.setBackground(Color.RED);
     
   
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jFileChooser1 = new javax.swing.JFileChooser();
        imageDisplay = new javax.swing.JLabel();
        playBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buttonGroup1.add(easyBtn);
        easyBtn.setFont(new java.awt.Font("Noteworthy", 0, 14)); // NOI18N
        easyBtn.setSelected(true);
        easyBtn.setText("Easy");

        buttonGroup1.add(mediumBtn);
        mediumBtn.setFont(new java.awt.Font("Noteworthy", 0, 14)); // NOI18N
        mediumBtn.setText("Medium");

        buttonGroup1.add(hardBtn);
        hardBtn.setFont(new java.awt.Font("Noteworthy", 0, 14)); // NOI18N
        hardBtn.setText("Hard");

        jLabel1.setFont(new java.awt.Font("Noteworthy", 1, 18)); // NOI18N
        jLabel1.setText("Choose your difficulty:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel2.setFont(new java.awt.Font("Noteworthy", 1, 36)); // NOI18N
        jLabel2.setText("Choose a photo:");

        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        playBtn.setFont(new java.awt.Font("Noteworthy", 1, 14)); // NOI18N
        playBtn.setText("Let's play a game!");
        playBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addContainerGap()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(easyBtn)
        						.addComponent(mediumBtn)
        						.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        						.addComponent(hardBtn)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(12)
        					.addComponent(imageDisplay, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
        			.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(jLabel2)
        						.addComponent(jFileChooser1, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(31)
        					.addComponent(playBtn, GroupLayout.PREFERRED_SIZE, 277, GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGap(22)
        					.addComponent(jLabel1)
        					.addGap(18)
        					.addComponent(easyBtn)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(mediumBtn)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(hardBtn)
        					.addGap(53)
        					.addComponent(imageDisplay, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addGap(15)
        					.addComponent(jLabel2)
        					.addGap(18)
        					.addComponent(jFileChooser1, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(playBtn, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
            file = jFileChooser1.getSelectedFile();
            
            //check to make sure the file can be read.
            if(file.isFile()){
                playBtn.setEnabled(true);
            }
            
            //set the icon of jLabel(imageDisplay) to image chosen from jFileChooser
            try{
                imageDisplay.setIcon(new ImageIcon(ImageIO.read(file).getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
            }catch (IOException e){
               e.printStackTrace();
            }
        //}
        
    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void playBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playBtnActionPerformed
		//Step 1: get difficulty
		if(easyBtn.isSelected()) {
			App.setDifficultyLevel(DifficultyLevel.EASY);
		} else if (mediumBtn.isSelected()) {
			App.setDifficultyLevel(DifficultyLevel.MEDIUM);
		} else {
			App.setDifficultyLevel(DifficultyLevel.HARD);
		}
		
		//Step 2: get file
		App.setFileURL(file.getAbsolutePath());
		
        
        //Step 3: display the game screen
            GameScreen gameScreen = null;
        try {
            gameScreen = new GameScreen();
        } catch (IOException ex) {
            Logger.getLogger(NewGameSettings.class.getName()).log(Level.SEVERE, null, ex);
        }
            gameScreen.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_playBtnActionPerformed
    
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
            java.util.logging.Logger.getLogger(NewGameSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewGameSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewGameSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewGameSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewGameSettings().setVisible(true);
            }
        });
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton easyBtn;
    private javax.swing.JRadioButton hardBtn;
    private javax.swing.JLabel imageDisplay;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JRadioButton mediumBtn;
    private javax.swing.JButton playBtn;
    // End of variables declaration//GEN-END:variables
}
