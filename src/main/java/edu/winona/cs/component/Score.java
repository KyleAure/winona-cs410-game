package edu.winona.cs.component;

public class Score {
	//private static final Log LOG = new Log(Score.class.getName());

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
