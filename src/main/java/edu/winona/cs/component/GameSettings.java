
package edu.winona.cs.component;

import java.awt.Color;

import edu.winona.cs.gamelogic.DifficultyLevel;

/**
 * Variables here match those stored in the GameSettingsTable.
 * 1) Background Color - is a color object
 * 2) High Score Tracking - true - we are tracking, false - we are not tracking.
 * 3) Difficulty Level - users perferred difficulty
 * 
* @author Tristin Harvell
 * @version 1.1
 */
public class GameSettings {
	//private static final Log LOG = new Log(GameSettings.class.getName());
    
    private Color backgroundColor;
    private boolean highScoreTracking;
    private DifficultyLevel difficulty;

    public GameSettings(Color backgroundColor, boolean highScoreTracking, DifficultyLevel level) {
        this.backgroundColor = backgroundColor;
        this.highScoreTracking = highScoreTracking;
        this.setDifficulty(level);
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
    
	public DifficultyLevel getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(DifficultyLevel difficulty) {
		this.difficulty = difficulty;
	}

    @Override
    public String toString() {
        return "Background color: " + backgroundColor + 
                "HighScoreTracking: " + highScoreTracking +
                "DifficultyLevel: " + difficulty;
    }
}
