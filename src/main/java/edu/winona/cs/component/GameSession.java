
package edu.winona.cs.component;

import javax.swing.Icon;

public class GameSession {
    
    private String difficulty;
    private Icon imageChosen;
    private int numOfSquares;

    public GameSession() {
    }

    public GameSession(String difficulty, Icon imageChosen, int numOfSquares) {
        this.difficulty = difficulty;
        this.imageChosen = imageChosen;
        this.numOfSquares = numOfSquares;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Icon getImageChosen() {
        return imageChosen;
    }

    public void setImageChosen(Icon imageChosen) {
        this.imageChosen = imageChosen;
    }

    public int getNumOfSquares() {
        return numOfSquares;
    }

    public void setNumOfSquares(int numOfSquares) {
        this.numOfSquares = numOfSquares;
    }

    @Override
    public String toString() {
        return "Difficulty: " + difficulty + 
                "Image Chosen: " + imageChosen + 
                "Number of Square: " + numOfSquares;
    }
    
}
