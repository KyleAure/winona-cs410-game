package edu.winona.cs.db;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.winona.cs.component.GameSettings;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * Creates game settings table.
 * Preferred usage:
 * 
 * DatabaseManager dbm = new DatabaseManager();
 * GameSettingsTable gst = DatabaseManager.getGameSettingsTable();
 * gst.recordGameSettings(username, gameSettings);
 * GameSettings gs = gst.getGameSettings(username);
 * 
 * @author Kyle Aure
 *
 */
public class GameSettingsTable implements Table{
	private static final Log LOG = new Log(GameSettingsTable.class.getName());

	// Table attributes
	public static final String NAME = "gamesettings";
	private static boolean created = false;

	// Column Attributes
	private String pkUsername = "username";
	private String attBackgroundColor = "backgroundColor";
	private String attHighScoreTracking = "highScoreTracking";

	@Override
	public boolean isCreated() {
		return created;
	}

	@Override
	public void createTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: GameSettingsTable.createTable");
		
		//TODO check here to see if table is alread created from previous session

		// Check to see if table has not previously been created
		if (!created) {
			// SQL Statement to be run to create table.
			String sql = "CREATE TABLE " + NAME + " (" 
					+ pkUsername + " VARCHAR(255) not NULL, " 
					+ attBackgroundColor + " INTEGER not NULL, "
					+ attHighScoreTracking + " BOOLEAN not Null, "
					+ " PRIMARY KEY (" + pkUsername + "))";
			created = DatabaseUtil.createTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to create table even though flag is true.");
		}
		
	}

	@Override
	public void dropTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: GameSettingsTable.dropTable");
		
		//Check to see if table has previously been created
		if (created) {
			String sql = "DROP TABLE " + NAME;
			created = DatabaseUtil.dropTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to drop table even though flag is false.");
		}
		
	}
	
	/**
	 * Records game settings for a user.  If user already had saved game settings these settings will be overwritten.
	 * @param username - user's unique name.
	 * @param settings - GameSettings object.
	 */
	public void recordGameSetting(String username, GameSettings settings) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: GameSettingsTable.recordGameSetting");
		if (created) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sqlGetUser = "SELECT * FROM " + NAME 
					+ " WHERE " + pkUsername + " = '" + username + "'";
			String sqlInsert = "INSERT INTO " + NAME 
					+ " VALUES ('" + username + "', " + settings.getBackgroundColor().getRGB() + ", " + settings.isHighScoreTracking() + ")";
			String sqlUpdateColor = "UPDATE " + NAME 
					+ " SET " + attBackgroundColor + " = " + settings.getBackgroundColor().getRGB() 
					+ " WHERE " + pkUsername + " = '" + username + "'";
			String sqlUpdateTracking = "UPDATE " + NAME 
					+ " SET " + attHighScoreTracking + " = " + settings.isHighScoreTracking()
					+ " WHERE " + pkUsername + " = '" + username + "'";
			try {
				// STEP 2: Register JDBC driver
				Class.forName(DatabaseManager.JDBCDRIVER);

				// STEP 3: Open a connection
				LOG.log(LogLevel.INFO, "Connecting to a selected database...");
				conn = DriverManager.getConnection(DatabaseManager.DBURL);
				LOG.log(LogLevel.INFO, "Connected database successfully...");

				// STEP 4: Create statement
				LOG.log(LogLevel.INFO, "Creating statement...");
				stmt = conn.createStatement();

				// STEP 5: Check to see if user already exists
				LOG.log(LogLevel.INFO, "Checking to see if user has previous settings set...");
				rs = stmt.executeQuery(sqlGetUser);
				boolean found = false;
				while (rs.next()) {
					found = true;
				}

				// STEP 6: Update or create new
				if (found) {
					LOG.log(LogLevel.INFO, "User has been found.  Attempting to update settings...");
					stmt.executeUpdate(sqlUpdateColor);
					stmt.executeUpdate(sqlUpdateTracking);
					LOG.log(LogLevel.INFO, "User settings has been updated successfully...");
				} else {
					LOG.log(LogLevel.INFO, "User has not been found.  Attempting to add new user settings...");
					stmt.execute(sqlInsert);
					LOG.log(LogLevel.INFO, "User settings successfully created...");
				}
			} catch (SQLException e) {
				// Handle errors for JDBC
				LOG.log(e, LogLevel.SEVERE, "");
			} catch (Exception e) {
				// Handle errors for Class.forName
				LOG.log(e, LogLevel.SEVERE, "");
			} finally {
				// finally block used to close resources
				try {
					if (stmt != null)
						conn.close();
				} catch (SQLException e) {
				} // do nothing
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					LOG.log(e, LogLevel.WARNING, "");
				} // end finally try
			} // end try
			LOG.log(LogLevel.INFO, "End user settings recording.\n");
		} else {
			LOG.log(LogLevel.WARNING,
					"Attempted to access table that has not been created. User difficulty no executed.");
		}
	}
	
	/**
	 * Returns a user's game settings as a GameSettings object.
	 * @param username - user's unique name.
	 * @return - GameSettings object or null if not found.
	 */
	public GameSettings getGameSetting(String username) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: GameSettingsTable.getGameSetting");
		GameSettings result = null;
		if (created) {
			// Step 1: Create JDBC objects 
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sqlGetUser = "SELECT * FROM " + NAME + " WHERE " + pkUsername + " = '" + username + "'";
			try {
				// STEP 2: Register JDBC driver
				Class.forName(DatabaseManager.JDBCDRIVER);

				// STEP 3: Open a connection
				LOG.log(LogLevel.INFO, "Connecting to a selected database...");
				conn = DriverManager.getConnection(DatabaseManager.DBURL);
				LOG.log(LogLevel.INFO, "Connected database successfully...");

				// STEP 4: See if user already exists
				LOG.log(LogLevel.INFO, "Creating statement...");
				stmt = conn.createStatement();

				LOG.log(LogLevel.INFO, "Checking to see if user has previous settings set...");
				rs = stmt.executeQuery(sqlGetUser);
				boolean found = false;
				while (rs.next()) {
					found = true;
					LOG.log(LogLevel.INFO, "User has been found.  Attempting to get settings...");
					int colorResult = rs.getInt(attBackgroundColor);
					boolean highScoreResult = rs.getBoolean(attHighScoreTracking);
					result = new GameSettings(new Color(colorResult), highScoreResult);
					LOG.log(LogLevel.INFO, "User settings has been found successfully...");
				}
				if (!found) {
					LOG.log(LogLevel.WARNING, "User has not been found...");
				}
			} catch (SQLException e) {
				// Handle errors for JDBC
				LOG.log(e, LogLevel.SEVERE, "");
			} catch (Exception e) {
				// Handle errors for Class.forName
				LOG.log(e, LogLevel.SEVERE, "");
			} finally {
				// finally block used to close resources
				try {
					if (stmt != null)
						conn.close();
				} catch (SQLException e) {
				} // do nothing
				try {
					if (conn != null)
						conn.close();
				} catch (SQLException e) {
					LOG.log(e, LogLevel.WARNING, "");
				} // end finally try
			} // end try
			LOG.log(LogLevel.INFO, "End getting settings.\n");
		} else {
			LOG.log(LogLevel.WARNING,
					"Attempted to access table that has not been created. User settings returned null.");
		}
		return result;
	}
}
