
package edu.winona.cs.component;

import java.awt.Color;

import edu.winona.cs.log.Log;

public class GameSettings {
	private static final Log LOG = new Log("GameSettings");
    
    private Color backgroundColor;
    private boolean highScoreTracking;

    public GameSettings() {
    }

    public GameSettings(Color backgroundColor, boolean highScoreTracking) {
        this.backgroundColor = backgroundColor;
        this.highScoreTracking = highScoreTracking;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
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
