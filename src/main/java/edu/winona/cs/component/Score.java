package edu.winona.cs.component;

/**
 * Variables here match those stored in the HighScoreTable.
 * HighScore (int) - lowest number of moves made to complete puzzle.
 * 
 * @author Tristin Harvell
 * @version 1.1
 */
public class Score {
	//private static final Log LOG = new Log(Score.class.getName());

    private int highScore;

    public Score() {
    }

    public double getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    @Override
    public String toString() {
        return "High Score: " + highScore;
    }

}
