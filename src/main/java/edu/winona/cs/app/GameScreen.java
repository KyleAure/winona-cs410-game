package edu.winona.cs.app;

import edu.winona.cs.gamelogic.Cell;
import edu.winona.cs.component.GameSession;
import edu.winona.cs.db.DatabaseManager;
import edu.winona.cs.gamelogic.AdjacencyListMaker;
import edu.winona.cs.gamelogic.DifficultyLevel;
import edu.winona.cs.gamelogic.Randomize;
import edu.winona.cs.gamelogic.Space;
import edu.winona.cs.image.ImageProcessor;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen extends JFrame {

    private static final long serialVersionUID = -2097350155286375640L;
    public static final int WINDOW_MIN_X = 400;
    public static final int WINDOW_MIN_Y = 350;

    public static int COLS = 4;
    public static int ROWS = 4;
    public static final int  HGAP = 1;
    public static final int  VGAP = 1;

    public static int NUMBER_OF_CELLS = ROWS * COLS;

    //private final JFrame frame;
    private final List<Cell> buttons = new ArrayList<>();
    private final List<Cell> correct = new ArrayList<>();
    private Cell emptyButton;

    private int movesCounter;
    private int levelInt;
    private DifficultyLevel level = App.getDifficultyLevel();

    //image/game processing
    public List<BufferedImage> imageList = new ArrayList<>();
    public List<BufferedImage> keyList = imageList;
    ImageProcessor image = new ImageProcessor();
    AdjacencyListMaker adjacency = new AdjacencyListMaker();
    Randomize randomize = new Randomize();
    GameSession session;

    //database
    private DatabaseManager dbm = DatabaseManager.getDatabaseManager();
    /**
     * Creates new form GUI
     */
    public GameScreen() throws IOException {
    	//Step 1: Set window color
    	if(App.isSettingsSet()) {
            Container a = GameScreen.this.getContentPane();
            if(App.isSettingsSet()) {
            	a.setBackground(App.getSettings().getBackgroundColor());
            } else {
            	a.setBackground(App.DEFAULT_SETTINGS.getBackgroundColor());
            }
    	}

        //set rows and columns
    	COLS = level.getDifficulty();
    	ROWS = level.getDifficulty();

        //set up window
        //frame = new JFrame("Puzzle Slider Game");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(WINDOW_MIN_X, WINDOW_MIN_Y));
        this.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));
        this.setJMenuBar(jMenuBar1);
        

        //calculate number of cells
        NUMBER_OF_CELLS = ROWS * COLS;
        
        File file;

        //filechooser
        if(App.isImgFileSet()) {
        	file = new File(App.getImgFileURL());
        } else {
        	JFileChooser jfc = new JFileChooser(new File("./src/main/resources"));
            //int returnVal = -1;
            int returnVal = jfc.showOpenDialog(this);
            while (returnVal != JFileChooser.APPROVE_OPTION) {
                jfc.showOpenDialog(null);
            }

            //get file
            file = jfc.getSelectedFile();	
        }
        

        //assign file
        image.assignImage(file);

        //divide image
        imageList = image.divideImage(level.getDifficulty() * level.getDifficulty());

        createCellsList();

        //set icons for each button
        assignIcons();

        //remove the last cell
        emptyButton = buttons.get(NUMBER_OF_CELLS - 1);

        this.setVisible(true);
        this.pack();
        

        setVictoryCondition();

        initComponents();
        //frame.add(frame2);

        
        //Save initial game session
        session = new GameSession(App.getUsername(), App.getDifficultyLevel(), movesCounter, imageList, keyList);
        App.setGameSession(session);
    }

    public void actionPerformed(ActionEvent e) {
        if (checkNeighbor((Cell) e.getSource())) {
            swap((Cell) e.getSource());
            movesCounter++;
            checkVictory();
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    private void createCell(int v) {
        Cell c = new Cell(v);
        c.addActionListener(
                (ActionEvent e) -> {
                    actionPerformed(e);
                }
        );
        buttons.add(c);
        this.add(c);
    }

    private boolean checkNeighbor(Cell c) {
        int x = Math.abs(c.getCol() - emptyButton.getCol());
        int y = Math.abs(c.getRow() - emptyButton.getRow());

        if (!(x == 0 && y == 1 || y == 0 && x == 1)) {
            return false;
        }

        return true;
    }

    private void swap(Cell c) {
        Icon tmpIcon = emptyButton.getIcon();
        emptyButton.setIcon(c.getIcon());
        c.setIcon(tmpIcon);
        // update reference to empty button
        emptyButton = c;

        checkVictory();
    }

    private void setVictoryCondition() {
        for (Integer i = 1; i < NUMBER_OF_CELLS; i++) {
            correct.add(new Cell(i));
        }
        correct.add(new Cell(0));
    }

    //TODO: not working
    private void checkVictory() {
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            if (!(buttons.get(i).getIcon().equals(correct.get(i).getIcon()))) {
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "You achieved victory in " + movesCounter + " moves.",
                "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void assignIcons() {
        //set icons for each button
        for (int i = 0; i < buttons.size() - 1; i++) {
            buttons.get(i).setIcon(new ImageIcon(imageList.get(i)));
            buttons.get(i).setEnabled(!buttons.get(i).isEnabled());
        }
    }

    private void createCellsList() {
        for (Integer i = NUMBER_OF_CELLS - 1; i >= 0; i--) {
            createCell(i);
        }
    }

    public void setSessionDetails() {
    	session = new GameSession(App.getUsername(), App.getDifficultyLevel(), movesCounter, imageList, keyList);
    	App.setGameSession( session );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        optionsMenu = new javax.swing.JMenu();
        play = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        settings = new javax.swing.JMenuItem();
        mainMenu = new javax.swing.JMenuItem();
        restart = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        optionsMenu.setText("File");

        play.setText("Play");
        play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playActionPerformed(evt);
            }
        });
        optionsMenu.add(play);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        optionsMenu.add(save);

        settings.setText("Settings");
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsActionPerformed(evt);
            }
        });
        optionsMenu.add(settings);

        mainMenu.setText("Main Menu");
        mainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuActionPerformed(evt);
            }
        });
        optionsMenu.add(mainMenu);

        restart.setText("Restart");
        restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartActionPerformed(evt);
            }
        });
        optionsMenu.add(restart);

        jMenuBar1.add(optionsMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void playActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playActionPerformed
        // TODO add your handling code here:
        System.out.print("Play");
                randomize.randomize(imageList);
                //set icons for each button
                assignIcons();
                
        
    }//GEN-LAST:event_playActionPerformed

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }

            private void SaveActionPerformed(ActionEvent evt) {
                System.out.print("Save");

                //save session: score, keylist, imagelist
                setSessionDetails();

                //create a serialized file
                try {
                    // create a new file with an ObjectOutputStream
                    File file = new File(App.getUsername() + ".ser");

                    //TODO: delete
                    System.out.println("BEFORE: " + file.toString());

                    FileOutputStream out = new FileOutputStream(file);
                    try (ObjectOutputStream oout = new ObjectOutputStream(out)) {
                        oout.writeObject(session);
                    }

                    //TODO: delete
                    System.out.println("AFTER: " + file.toString());

                    //add the serialized file to the database
                    dbm.getSaveStateTable().createSaveState(App.getUsername(), file);

                    JOptionPane.showMessageDialog(null, "Game saved successfully",
                            "Saved Game Confirmation", 1);

                } catch (HeadlessException | IOException ex) {
                }
            }
        });

        Settings.setText("Settings");
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }

            private void SettingsActionPerformed(ActionEvent evt) {
                System.out.print("Settings");
                Settings settings = new Settings();
                settings.setVisible(true);
            }
        });

        mainMenu.setText("Main Menu");
        mainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuActionPerformed(evt);
            }

    private void mainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuActionPerformed
        // TODO add your handling code here:
        
       System.out.print("Main Menu");
                int result = JOptionPane.showConfirmDialog((Component) null, "Save before returning to main menu?",
                        "alert", JOptionPane.YES_NO_CANCEL_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    //TODO: save logic

                    //close game screen and open menu
                    this.dispose();
                   
                    MainMenuScreen menu = new MainMenuScreen();
                    menu.setVisible(true);
                } else if (result == JOptionPane.NO_OPTION) {
                    //don't save
                    //close game screen and open menu
                    this.dispose();
                   
                    MainMenuScreen menu = new MainMenuScreen();
                    menu.setVisible(true);
                }
    }//GEN-LAST:event_mainMenuActionPerformed

    private void restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartActionPerformed
        // TODO add your handling code here:
        
        System.out.print("Restart");
                int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure you'd like to restart the current game?",
                        "alert", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    //restart logic
                    imageList = keyList;
                    assignIcons();
                    play.setEnabled(true);
                }
    }//GEN-LAST:event_restartActionPerformed


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
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GameScreen().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mainMenu;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JMenuItem play;
    private javax.swing.JMenuItem restart;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem settings;
    // End of variables declaration//GEN-END:variables
}
