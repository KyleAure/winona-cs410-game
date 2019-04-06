package edu.winona.cs.app;

import edu.winona.cs.gamelogic.AdjacencyListMaker;
import edu.winona.cs.gamelogic.DifficultyLevel;
import edu.winona.cs.gamelogic.Space;
import edu.winona.cs.image.ImageProcessor;
import edu.winona.cs.temp.*;
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
    public static final int HGAP = 3;
    public static final int VGAP = 3;

    public static int NUMBER_OF_CELLS = ROWS * COLS;

    private final JFrame frame;
    private final List<Cell> buttons = new ArrayList<>();
    private final List<Cell> correct = new ArrayList<>();
    private Cell emptyButton;

    private int movesCounter;
    private int levelInt;
    private DifficultyLevel level;

    //image/game processing
    List<BufferedImage> imageList = new ArrayList<>();
    ImageProcessor image = new ImageProcessor();
    AdjacencyListMaker adjacency = new AdjacencyListMaker();
    List<Space> spaces = new ArrayList();

    /**
     * Creates new form GUI TODO: get level input
     */
    public GameScreen() throws IOException {

        //set up window
        frame = new JFrame("Puzzle Slider Game");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(WINDOW_MIN_X, WINDOW_MIN_Y));
        frame.setLayout(new GridLayout(ROWS, COLS, HGAP, VGAP));

        //TODO: temporary
        levelInt = level.EASY.getDifficulty();

        //set rows and columns
        switch (2) {
            case 2:
                COLS = 2;
                ROWS = 2;
                break;
            case 3:
                COLS = 3;
                ROWS = 3;
                break;
            case 4:
                COLS = 4;
                ROWS = 4;
                break;
            case 5:
                COLS = 5;
                ROWS = 5;
                break;
        }

        NUMBER_OF_CELLS = ROWS * COLS;

        //filechooser
        JFileChooser jfc = new JFileChooser(new File("."));
        //int returnVal = -1;
        int returnVal = jfc.showOpenDialog(this);
        while (returnVal != JFileChooser.APPROVE_OPTION) {
            jfc.showOpenDialog(null);
        }

        //get file
        File file = jfc.getSelectedFile();

        //assign file
        image.assignImage(file);

        //divide image
        imageList = image.divideImage(4);

        for (Integer i = NUMBER_OF_CELLS - 1; i >= 0; i--) {
            createCell(i);
        }

        System.out.print("Number of Buttons: " + buttons.size());

        System.out.print("Test");
        for (int i = 0; i < buttons.size() - 1; i++) {
            buttons.get(i).setIcon(new ImageIcon(imageList.get(i)));
            System.out.print("Test: " + i);
        }

        emptyButton = buttons.get(NUMBER_OF_CELLS - 1);

        frame.setVisible(true);
        frame.pack();

        setVictoryCondition();
        initComponents();
    }

    public void actionPerformed(ActionEvent e) {
        if (checkNeighbour((Cell) e.getSource())) {
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
    private boolean checkNeighbour(Cell c) {
        int x = Math.abs(c.getCol() - emptyButton.getCol());
        int y = Math.abs(c.getRow() - emptyButton.getRow());

        if (!(x == 0 && y == 1 || y == 0 && x == 1)) {
            return false;
        }

        return true;
    }

    private void swap(Cell c) {
        // swap c and empty
        String tmp = emptyButton.getText();
        emptyButton.setText(c.getText());
        c.setText(tmp);

        // update reference to empty button
        emptyButton = c;
    }

    private void setVictoryCondition() {
        for (Integer i = 1; i < NUMBER_OF_CELLS; i++) {
            correct.add(new Cell(i));
        }
        correct.add(new Cell(0));
    }

    private void checkVictory() {
        for (int i = 0; i < NUMBER_OF_CELLS; i++) {
            if (!(buttons.get(i).getText().equals(correct.get(i).getText()))) {
                return;
            }
        }

        JOptionPane.showMessageDialog(null, "You achieved victory in " + movesCounter + " moves.",
                "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
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
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
