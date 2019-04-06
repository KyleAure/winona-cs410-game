/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.winona.cs.gamelogic;

import edu.winona.cs.app.GameScreen;
import javax.swing.*;

/**
 * FIXME - Remove this class and package, just for testing.
 *
 */
public class Cell extends JButton {
    private static final long serialVersionUID = 8822510600483479089L;
    private int row;
    private int col;

    public Cell (Integer value)
    {
        if (value == 0) {
            //setText (" "); 
        } else {
            //setText(value.toString());
        }

        row = GameScreen.NUMBER_OF_CELLS / GameScreen.COLS-1 - ( value / GameScreen.COLS);
        col = GameScreen.NUMBER_OF_CELLS-1 - (row*GameScreen.COLS + value);

    }

    public int  getCol (){
        return col;
    }

    public int getRow (){
        return row;
    }
}
