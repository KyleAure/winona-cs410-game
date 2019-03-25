
package edu.winona.cs.component;

import javax.swing.Icon;

import edu.winona.cs.gamelogic.DifficultyLevel;
import edu.winona.cs.log.Log;

public class GameSession {
	private static final Log LOG = new Log("GameSession");
    
    private DifficultyLevel difficulty;

    public GameSession(String username) {
    	//TODO get users difficulty from database
    }

    public GameSession(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }
    
    //TODO Save to database
    public void save() {
    	
    }

    @Override
    public String toString() {
        return "Difficulty: " + difficulty;
    }
    
}
