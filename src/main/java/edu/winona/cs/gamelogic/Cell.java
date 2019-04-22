package edu.winona.cs.gamelogic;

import javax.swing.*;

public class Cell extends JButton {
	private static final long serialVersionUID = 7704011875796784970L;

	//Value of cell, starting position
    private int value;
    
    //Location of cell
    private int row; 
    private int col;
    
    //Is empty
    private boolean isEmpty;
    
    //CONSTRUCTOR
    public Cell (int value, DifficultyLevel level, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
        
        isEmpty = (value == level.getSqure() - 1);
    }
    
    //##########################
  	//METHODS FOR THIS CELL
  	//##########################
    public int  getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
    
    public int getValue() {
    	return value;
    }
    
    public void setValue(int val) {
    	this.value = val;
    }
    
    public boolean isEmpty() {
    	return isEmpty;
    }
    
    public void setEmpty(boolean b) {
    	this.isEmpty = b;
    }
    
    public void updatePosition(int row, int col) {
    	this.row = row;
    	this.col = col;
    }
    
    /**
     * TODO This swap function should be integrated into game screen
     */
    @Deprecated
    public Cell swap(Cell c) {
    	Icon tempIcon = this.getIcon();
        int tempValue = this.value;
        int tempRow = this.row; 
        int tempCol = this.value;
        boolean tempIsEmpty = isEmpty;
        
        this.setIcon(c.getIcon());
        this.value = c.getValue();
        this.row = c.getRow();
        this.col = c.getCol();
        this.isEmpty = c.isEmpty();
        
        c.setIcon(tempIcon);
        c.setValue(tempValue);
        c.updatePosition(tempRow, tempCol);
        c.setEmpty(tempIsEmpty);
        
        return c;
    }
    
    //TODO this should be used to determine if cells are equal
    @Deprecated
    public boolean equals(Object o) {
    	if(!Cell.class.isInstance(o)) {
    		return false;
    	} else {
    		return value == ((Cell) o).getValue();
    	}
    }

	@Override
	public String toString() {
		return "\n[value=" + value + ", row=" + row + ", col=" + col + ", isEmpty=" + isEmpty + "]";
	}
}
