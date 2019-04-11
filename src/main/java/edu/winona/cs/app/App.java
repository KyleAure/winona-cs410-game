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
	public static final GameSettings DEFAULT_SETTINGS = new GameSettings(Color.BLUE, true, DifficultyLevel.EASY);
	
	//Used to keep track of username if user is logged in.
	private static String username = null; 
	private static GameSettings settings = null;
	private static GameSession session = null;
	private static String imageURL = null;
	private static boolean isNewGame = true;

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
	 * TODO remove this method, difficulty level is saved in settings and session
	 * Returns the difficulty level set by user.
	 * @return
	 */
	@Deprecated
	public static DifficultyLevel getDifficultyLevel() {
		return session.getDifficulty();
	}

	/**
	 * TODO remove this method, difficulty level is saved in settings and session
	 * Set the difficulty level.
	 * @param level - DifficultyLevel
	 */
	@Deprecated
	public static void setDifficultyLevel(DifficultyLevel level) {
		if(session == null) {
			session = new GameSession(username, level, 0, null, null);
		} else {
			session.setDifficulty(level);
		}	
	}
	
	/**
	 * TODO remove this method, difficulty level is saved in settings and session
	 * Check to see if a difficulty level has been set.
	 * @return boolean: true = set, false = not set.
	 */
	@Deprecated
	public static boolean isDifficultyLevelSet() {
		if(session == null) {
			return false;
		}
		return session.getDifficulty() != null;
	}

	/**
	 * Returns imageURL
	 * @return String - imageURL
	 */
	public static String getImgFileURL() {
		return imageURL;
	}

	/**
	 * Set imageURL 
	 * @param fileURL
	 */
	public static void setImgFileURL(String fileURL) {
		App.imageURL = fileURL;
	}
	
	/**
	 * Checks to see if imageURL has been set.
	 * @return boolean: True = set, False = not set.
	 */
	public static boolean isImgFileSet() {
		return imageURL != null;
	}
	
	/**
	 * Returns current game session
	 * @return GameSession object
	 */
	public static GameSession getSession() {
		return session;
	}
	 /**
	  * Overrides the current gameSession with a new gameSession
	  * @param session
	  */
	public static void setGameSession(GameSession session) {
		App.session = session;
	}

	/**
	 * Checks to see if this is a new game or not.
	 * @return boolean: True = newGame, False = loadedGame
	 */
	public static boolean isNewGame() {
		return isNewGame;
	}

	/**
	 * Sets the isNewGame check flag.
	 * @param isNewGame
	 */
	public static void setNewGame(boolean isNewGame) {
		App.isNewGame = isNewGame;
	}
}
