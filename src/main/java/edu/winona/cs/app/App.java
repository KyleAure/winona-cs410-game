package edu.winona.cs.app;

import java.awt.Color;

import edu.winona.cs.component.GameSession;
import edu.winona.cs.component.GameSettings;
import edu.winona.cs.gamelogic.DifficultyLevel;

/**
 * Application class is the starting point for the program.
 * 
 * @author Kyle Aure
 * @version 1.0
 */
public class App {
	//Default settings for new user, or verified user that does not have saved settings.
	public static final GameSettings DEFAULT_SETTINGS = new GameSettings(Color.BLUE, true);
	
	//Used to keep track of username if user is logged in.
	private static String username = null; 
	
	private static GameSettings settings = null;
	
	private static GameSession session = null;
	
	private static String imageURL = null;
	

	/**
	 * Main Method - Launches LoginScreen.
	 * @param args
	 */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginScreen().setVisible(true);
            }
        });
    }
    
    /**
     * Use this method to set the UserName of the player once it has been
     * created or verified.
     * @param username - String 
     */
    public static void setUsername(String username) {
    	App.username = username; 
    }
    
    /**
     * Once a user has been created or verified username will be set in the 
     * main application.  Use the getUsername method to communicate the username
     * to other modules.
     * @return String - username or null if not yet set.
     */
    public static String getUsername() {
    	return username;
    }
    
    /**
     * Use this method to determine if user has been created or verified yet.
     * @return boolean - true: Created/Verified - false: not Created/Verified
     */
    public static boolean isUser() {
    	return username != null;
    }

    /**
     * If game settings were previously set or retrieved user this method
     * to get those settings and use them. 
     * @return GameSettings object
     */
	public static GameSettings getSettings() {
		return settings;
	}

	/**
	 * If a user is created, save GameSetting in Database and also let setSetting in the App
	 * If a user is verified, get GameSettings from Database and also setSetting in the App
	 * @param settings - GameSettings object
	 */
	public static void setSettings(GameSettings settings) {
		App.settings = settings;
	}
	
	/**
	 * Check to see if settings have been set
	 * @return boolean: true = set, false = not set
	 */
	public static boolean isSettingsSet() {
		return settings != null;
	}

	/**
	 * Returns the difficulty level set by user.
	 * @return
	 */
	public static DifficultyLevel getDifficultyLevel() {
		return session.getDifficulty();
	}

	/**
	 * Set the difficulty level.
	 * @param level - DifficultyLevel
	 */
	public static void setDifficultyLevel(DifficultyLevel level) {
		session.setDifficulty(level);;
	}
	
	/**
	 * Check to see if a difficulty level has been set.
	 * @return boolean: true = set, false = not set.
	 */
	public static boolean isDifficultyLevelSet() {
		return session.getDifficulty() != null;
	}

	public static String getImgFileURL() {
		return imageURL;
	}

	public static void setImgFileURL(String fileURL) {
		App.imageURL = fileURL;
	}
	
	public static boolean isImgFileSet() {
		return imageURL != null;
	}
	
	public static GameSession getSession() {
		return session;
	}
	
	public static void setGameSession(GameSession session) {
		App.session = session;
	}
}
