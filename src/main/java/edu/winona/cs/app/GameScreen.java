package edu.winona.cs.app;

import edu.winona.cs.gamelogic.Cell;
import edu.winona.cs.component.GameSession;
import edu.winona.cs.db.DatabaseManager;
import edu.winona.cs.gamelogic.DifficultyLevel;
import edu.winona.cs.gamelogic.MoveLogic;
import edu.winona.cs.gamelogic.Randomize;
import edu.winona.cs.image.ImageProcessor;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen extends JFrame {
    //Serialized variable

    private static final long serialVersionUID = -2097350155286375640L;

    //Logger
    private static final Log LOG = new Log(GameScreen.class.getName());

    //Constants
    private static final int WINDOW_MIN_X = 400;
    private static final int WINDOW_MIN_Y = 350;
    private static final int HGAP = 1;
    private static final int VGAP = 1;

    //Difficulty variable
    public static int COLS = 4;
    public static int ROWS = 4;

    //Number of Cells.  Dependent on difficulty
    public static int NUMBER_OF_CELLS = ROWS * COLS;

    //Buttons and Cells
    private static final List<Cell> BUTTONS = new ArrayList<>();
    private static final List<Cell> CORRECT = new ArrayList<>();
    private Cell emptyButton;

    //image/game processing
    private ImageProcessor image = new ImageProcessor();
    private Randomize randomize = new Randomize();
    private GameSession session;
    private int movesCounter = 0;
    private Boolean playing = false;
    private MoveLogic move = new MoveLogic();

    //Databse
    private DatabaseManager dbm = DatabaseManager.getDatabaseManager();

    //UI variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem mainMenu;
    private javax.swing.JMenu optionsMenu;
    private javax.swing.JMenuItem play;
    private javax.swing.JMenuItem restart;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem settings;

    /**
     * Game Screen GUI constructor
     */
    public GameScreen(GameSession session) throws IOException {
        initComponents();

        //Step 1: Set window color to users preference
        if (App.isSettingsSet()) {
            Container a = GameScreen.this.getContentPane();
            if (App.isSettingsSet()) {
                a.setBackground(App.getSettings().getBackgroundColor());
            } else {
                a.setBackground(App.DEFAULT_SETTINGS.getBackgroundColor());
            }
        } else {
            LOG.log(LogLevel.WARNING, "App settings when entering GameScreen.");
        }

        //Step 2: set rows and columns
        DifficultyLevel level;
        if (App.isDifficultyLevelSet()) {
            level = App.getDifficultyLevel();
        } else {
            LOG.log(LogLevel.WARNING, "Difficulty not set when entering GameScreen. Defaulted to EASY.");
            level = DifficultyLevel.EASY;
        }
        COLS = level.getDifficulty();
        ROWS = level.getDifficulty();
        NUMBER_OF_CELLS = ROWS * COLS;

        //STEP 3: Set up JFrame
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(WINDOW_MIN_X, WINDOW_MIN_Y));
        this.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));
        this.setJMenuBar(jMenuBar1);

        //Step 4: is new game?
        if (App.isNewGame()) {
            LOG.log(LogLevel.INFO, "New Game");

            //STEP 4.1: create new image file
            File file;

            //STEP 4.2: Load image 
            if (App.isImgFileSet()) {
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

            //STEP 4.3: assign image file to image object
            image.assignImage(file);

            //STEP 4.4: divide image
            System.out.println("Number of Cells: " + NUMBER_OF_CELLS);
            session.setImageList(image.divideImage(NUMBER_OF_CELLS));
            session.setKeyList(session.getImageList());
            App.setGameSession(session);

            System.out.println("session.getImageList" + session.getImageList().toString());
            System.out.println("session.getImageList" + App.getSession().getImageList().toString());

        } else {
            LOG.log(LogLevel.INFO, "Loaded Game");
            session = App.getSession();
        }

        //STEP 5: Create cell list
        createCellsList();

        //STEP 7: create empty space
        emptyButton = BUTTONS.get(NUMBER_OF_CELLS - 1);

        //STEP 6: Assign icons
        assignIcons();

        //STEP 8: set this screen visible
        this.setVisible(true);
        this.pack();

        //Step 9: set victory condition
        setVictoryCondition();
    }

    /**
     * Action performed when button is clicked. perform swap
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (checkNeighbor((Cell) e.getSource())) {
            swap((Cell) e.getSource());
            session.incrementClick();
            movesCounter++;
        } else {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    /**
     * Create cell subroutine
     *
     * @param v
     */
    private void createCell(int v) {
        Cell c = new Cell(v);
        c.addActionListener((ActionEvent e) -> {
            actionPerformed(e);
        });
        BUTTONS.add(c);
        this.add(c);
    }

    /**
     * Check neighbor subroutine
     *
     * @param c
     * @return
     */
    private boolean checkNeighbor(Cell c) {
        int x = Math.abs(c.getCol() - emptyButton.getCol());
        int y = Math.abs(c.getRow() - emptyButton.getRow());

        if (!(x == 0 && y == 1 || y == 0 && x == 1)) {
            return false;
        }

        return true;
    }

    /**
     * Swap subroutine
     *
     * @param c
     */
    private void swap(Cell c) {
        Icon tmpIcon = emptyButton.getIcon();
        emptyButton.setIcon(c.getIcon());
        c.setIcon(tmpIcon);
        // update reference to empty button
        emptyButton = c;
        
        checkVictory();
    }

    /**
     * Set victory condition subroutine
     */
    private void setVictoryCondition() {
        for (Integer i = 1; i < NUMBER_OF_CELLS; i++) {
            CORRECT.add(new Cell(i));
        }
        CORRECT.add(new Cell(0));
    }

    private void checkVictory() {
        
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            if (!(BUTTONS.get(i).getIcon().equals(CORRECT.get(i).getIcon()))) {
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "You achieved victory in " + session.getClickCount() + " moves.",
                "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
        
        session.saveHighScores();
    }

    /**
     * Assign icons subroutine
     */
    private void assignIcons() {
        //set icons for each button
        for (int i = 0; i < BUTTONS.size() - 1; i++) {
            BUTTONS.get(i).setIcon(new ImageIcon(App.getSession().getImageList().get(i)));
            BUTTONS.get(i).setEnabled(!BUTTONS.get(i).isEnabled());
        }
    }

    /**
     * create cell list subroutine
     */
    private void createCellsList() {
        for (Integer i = NUMBER_OF_CELLS - 1; i >= 0; i--) {
            createCell(i);
        }
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
                SaveActionPerformed(evt);
            }
        });
        optionsMenu.add(save);

        settings.setText("Settings");
        settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
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
        session = App.getSession();
        //FIXME how are we randomizing the list?
        List<BufferedImage> rand = randomize.randomize(session.getImageList());
        session.setImageList(rand);
        App.setGameSession(session);
        //set icons for each button
        assignIcons();
        playing = true;
    }//GEN-LAST:event_playActionPerformed

    private void SaveActionPerformed(ActionEvent evt) {
        App.setGameSession(session);
        saveGame();
    }

    private void saveGame() {
        //create a serialized file
        try {
            // create a new file with an ObjectOutputStream
            File file = new File(App.getUsername() + ".ser");

            FileOutputStream out = new FileOutputStream(file);
            try (ObjectOutputStream oout = new ObjectOutputStream(out)) {
                oout.writeObject(session);
            }

            //add the serialized file to the database
            dbm.getSaveStateTable().createSaveState(App.getUsername(), file);

            JOptionPane.showMessageDialog(null, "Game saved successfully",
                    "Saved Game Confirmation", 1);

        } catch (HeadlessException | IOException ex) {
        }
    }

    //FIXME the user should not be able to change settings midway through a game.
    private void SettingsActionPerformed(ActionEvent evt) {
        if (playing) {//game started
            JOptionPane.showMessageDialog(null, "Unable to change setting midway through a game...",
                    "Game Settings", 1);
        } else {//game not started
            Settings settings = new Settings();
            settings.setVisible(true);
        }
    }


    private void mainMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuActionPerformed
        int result = JOptionPane.showConfirmDialog((Component) null, "Save before returning to main menu?",
                "alert", JOptionPane.YES_NO_CANCEL_OPTION);

        if (result == JOptionPane.YES_OPTION) {
            saveGame();

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

        int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure you'd like to restart the current game?",
                "alert", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            session.setImageList(session.getKeyList());
            assignIcons();
            movesCounter = 0;
            session.setClickCount(movesCounter);
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
                    new GameScreen(App.getSession()).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(GameScreen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
