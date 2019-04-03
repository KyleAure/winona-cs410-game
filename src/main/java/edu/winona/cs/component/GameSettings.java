
package edu.winona.cs.component;

import java.awt.Color;

/**
 * Variables here match those stored in the GameSettingsTable.
 * Background Color - is a color object
 * High Score Tracking - true - we are tracking, false - we are not tracking.
 * 
* @author Tristin Harvell
 * @version 1.1
 */
public class GameSettings {
	//private static final Log LOG = new Log(GameSettings.class.getName());
    
    private Color backgroundColor;
    private boolean highScoreTracking;

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
