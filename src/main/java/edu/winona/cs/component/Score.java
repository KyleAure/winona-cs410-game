package edu.winona.cs.component;

import edu.winona.cs.log.Log;

public class Score {
	private static final Log LOG = new Log("Score");

    private double highScore;

    public Score() {
    }

    public double getHighScore() {
        return highScore;
    }

    public void setHighScore(double highScore) {
        this.highScore = highScore;
    }

    @Override
    public String toString() {
        return "High Score: " + highScore;
    }

}
