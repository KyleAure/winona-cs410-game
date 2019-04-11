package edu.winona.cs.component;

import edu.winona.cs.gamelogic.DifficultyLevel;

/**
 * Variables here match those stored in the UserSettingsTable.
 * difficulty (DifficultyLevel) - preferred difficulty level.
 * 
 * Why have this class when all we are keeping track of is DifficultyLevel.
 * TODO Merge this class with GameSettings
 * 
 * @author Kyle Aure
 * @version 1.0
 *
 */
@Deprecated
public class UserSettings {
	//private static final Log LOG = new Log(UserSettings.class.getName());
    
	private DifficultyLevel difficulty;

	public UserSettings(DifficultyLevel difficulty) {
		this.difficulty = difficulty;
	}

	public DifficultyLevel getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(DifficultyLevel difficulty) {
		this.difficulty = difficulty;
	}

	@Override
	public String toString() {
		return "UserSettings [difficulty=" + difficulty + "]";
	}
}
