package edu.winona.cs.app;

import edu.winona.cs.component.GameSettings;
import edu.winona.cs.gamelogic.Cell;
import edu.winona.cs.gamelogic.AdjacencyListMaker;
import edu.winona.cs.gamelogic.DifficultyLevel;
import edu.winona.cs.gamelogic.Randomize;
import edu.winona.cs.gamelogic.Space;
import edu.winona.cs.image.ImageProcessor;
import edu.winona.cs.db.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameScreen extends javax.swing.JFrame {

    private static final long serialVersionUID = -2097350155286375640L;
    public static final int WINDOW_MIN_X = 240;
    public static final int WINDOW_MIN_Y = 200;

    public static int COLS = 4;
    public static int ROWS = 4;
    public static int HGAP = 3;
    public static int VGAP = 3;

    public static int NUMBER_OF_CELLS = ROWS * COLS;

    private final JFrame frame;
    private final JFrame frame2;
    private final List<Cell> buttons = new ArrayList<>();
    private final List<Cell> correct = new ArrayList<>();
    private Cell emptyButton;

    private int movesCounter;
    private int levelInt;
    private DifficultyLevel level;

    //image/game processing
    List<BufferedImage> imageList = new ArrayList<>();
    List<BufferedImage> keyList = imageList;
    ImageProcessor image = new ImageProcessor();
    AdjacencyListMaker adjacency = new AdjacencyListMaker();
    List<Space> spaces = new ArrayList();
    Randomize randomize = new Randomize();

    //database
    //DatabaseManager dbm = new DatabaseManager();
    /**
     * Creates new form GUI TODO: get level input
     */
    public GameScreen() throws IOException {

        //TODO: temporary, fix input of difficulty
        //levelInt = level.EASY.getDifficulty();
        //set rows and columns
        switch (5) {
            case 2:
                COLS = 2;
                ROWS = 2;
                HGAP = 1;
                VGAP = 1;
                break;
            case 3:
                COLS = 3;
                ROWS = 3;
                HGAP = 2;
                VGAP = 2;
                break;
            case 4:
                COLS = 4;
                ROWS = 4;
                HGAP = 3;
                VGAP = 3;
                break;
            case 5:
                COLS = 5;
                ROWS = 5;
                HGAP = 4;
                VGAP = 4;
                break;
        }

        //set up window
        frame = new JFrame("Puzzle Slider Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(WINDOW_MIN_X, WINDOW_MIN_Y));
        frame.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));

        frame2 = new JFrame("Game Options");
        frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame2.setMinimumSize(new Dimension(375, 60));

        //calculate number of cells
        NUMBER_OF_CELLS = ROWS * COLS;

        //filechooser
        JFileChooser jfc = new JFileChooser(new File("./src/main/resources"));
        //int returnVal = -1;
        int returnVal = jfc.showOpenDialog(this);
        while (returnVal != JFileChooser.APPROVE_OPTION) {
            jfc.showOpenDialog(null);
        }

        //get file
        File file = jfc.getSelectedFile();

        //assign file
        image.assignImage(file);

        //TODO: change 4, 9, 16, or 25 to difficulty number
        //divide image
        imageList = image.divideImage(25);

        createCellsList();

        //set icons for each button
        assignIcons();

        //remove the last cell
        emptyButton = buttons.get(NUMBER_OF_CELLS - 1);

        frame.setVisible(true);
        frame.pack();

        setVictoryCondition();

        //imageList = randomize.randomize(imageList);
        initComponents();
        buildButtons();
        frame.add(frame2);

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
        frame.add(c);
    }

    //TODO: replace, doesn't work with difficulties besides medium
    private boolean checkNeighbor(Cell c) {
        int x = Math.abs(c.getCol() - emptyButton.getCol());
        int y = Math.abs(c.getRow() - emptyButton.getRow());

        if (!(x == 0 && y == 1 || y == 0 && x == 1)) {
            return false;
        }

        return true;
    }

    private void swap(Cell c) {

        // swap c text and empty
        //String tmpString = emptyButton.getText();
        //emptyButton.setText(c.getText());
        //c.setText(tmpString);
        // swap c icon and empty
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
            if (!(buttons.get(i).getText().equals(correct.get(i).getIcon()))) {
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void buildButtons() {

        JPanel main = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JPanel panel1 = new JPanel();

        JButton Play = new javax.swing.JButton();
        JButton Save = new javax.swing.JButton();
        JButton Settings = new javax.swing.JButton();
        JButton mainMenu = new javax.swing.JButton();
        JButton Restart = new javax.swing.JButton();

        panel1.add(Play);
        panel1.add(Save);
        panel1.add(Settings);
        panel1.add(mainMenu);
        panel1.add(Restart);

        main.add(panel1);

        main.setVisible(true);

        frame2.add(main);
        frame2.setVisible(true);

        //frame.add(main);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Play.setText("Play");
        Play.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PlayActionPerformed(evt);
            }

            private void PlayActionPerformed(ActionEvent evt) {
                System.out.print("Play");
                randomize.randomize(imageList);
                //set icons for each button
                assignIcons();
            }
        });

        Save.setText("Save");
        Save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveActionPerformed(evt);
            }

            private void SaveActionPerformed(ActionEvent evt) {
                System.out.print("Save");
                //TODO: save logic
            }
        });

        Settings.setText("Settings");
        Settings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsActionPerformed(evt);
            }

            private void SettingsActionPerformed(ActionEvent evt) {
                System.out.print("Settings");
                GameSettingsMenu settings = new GameSettingsMenu();
                settings.setVisible(true);
            }
        });

        mainMenu.setText("Main Menu");
        mainMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuActionPerformed(evt);
            }

            private void mainMenuActionPerformed(ActionEvent evt) {
                System.out.print("Main Menu");
                int result = JOptionPane.showConfirmDialog((Component) null, "Save before returning to main menu?",
                        "alert", JOptionPane.YES_NO_CANCEL_OPTION);

                if (result == JOptionPane.YES_OPTION) {
                    //TODO: save logic

                    //exit
                    MainMenuScreen menu = new MainMenuScreen();
                    menu.setVisible(true);
                } else if (result == JOptionPane.NO_OPTION) {
                    //don't save
                    //exit
                    MainMenuScreen menu = new MainMenuScreen();
                    menu.setVisible(true);
                }
            }
        });

        Restart.setText("Restart");
        Restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RestartActionPerformed(evt);
            }

            private void RestartActionPerformed(ActionEvent evt) {
                System.out.print("Restart");
                int result = JOptionPane.showConfirmDialog((Component) null, "Are you sure you'd like to restart the current game?",
                        "alert", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    //restart logic
                    imageList = keyList;
                    assignIcons();
                }
            }
        });

        pack();
    }// </editor-fold> 

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
    // End of variables declaration//GEN-END:variables
}
