
package edu.winona.cs.component;

import edu.winona.cs.gamelogic.DifficultyLevel;

public class GameSession {
	//private static final Log LOG = new Log(GameSession.class.getName());
    
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
