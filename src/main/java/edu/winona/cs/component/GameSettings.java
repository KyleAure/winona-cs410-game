
package edu.winona.cs.component;

public class GameSettings {
    
    private String backgroundColor;
    private boolean highScoreTracking;

    public GameSettings() {
    }

    public GameSettings(String backgroundColor, boolean highScoreTracking) {
        this.backgroundColor = backgroundColor;
        this.highScoreTracking = highScoreTracking;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isHighScoreTracking() {
        return highScoreTracking;
    }

    public void setHighScoreTracking(boolean highScoreTracking) {
        this.highScoreTracking = highScoreTracking;
    }

    @Override
    public String toString() {
        return "Background color: " + backgroundColor + 
                "HighScoreTracking: " + highScoreTracking;
    }
    
    
    
}
