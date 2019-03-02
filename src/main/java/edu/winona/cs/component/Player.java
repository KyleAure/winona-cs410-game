
package edu.winona.cs.component;

import java.util.ArrayList;
import java.util.List;

import edu.winona.cs.log.Log;

public class Player {
	private static final Log LOG = new Log("Player");

    private String username;
    private List<Score> highScores = new ArrayList<>();

    public Player() {
    }
    
    public Player(String username) {
        this.username = username;
    } 

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Score> getHighScores() {
        return highScores;
    }

    public void setHighScores(List<Score> highScores) {
        this.highScores = highScores;
    }

    @Override
    public String toString() {
        return "Player: " + username
                + "High-Scores: " + highScores;
    }

}
