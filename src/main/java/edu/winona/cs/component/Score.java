package edu.winona.cs.component;

import edu.winona.cs.log.Log;

public class Score {
	private static final Log LOG = new Log("Score");

    private double completionTime;
    private double highScore;

    public Score() {
    }

    public Score(double completionTime, double highScore) {
        this.completionTime = completionTime;
        this.highScore = highScore;
    }

    public double getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(double completionTime) {
        this.completionTime = completionTime;
    }

    public double getHighScore() {
        return highScore;
    }

    public void setHighScore(double highScore) {
        this.highScore = highScore;
    }

    @Override
    public String toString() {
        return "Score: " + completionTime
                + "High Score: " + highScore;
    }

}
