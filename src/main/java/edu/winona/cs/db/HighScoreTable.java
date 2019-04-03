package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * High Score Table holds a users high score
 * 
 * Preferred usage:
 * 
 * DatabaseManager dbm = new DatabaseManager();
 * HighScoreTable hst = DatabaseManager.getScoreTable();
 * hst.recordHighScore(username, highScore);
 * int highscore = gst.getHighScore(username);
 * 
 * @author kaure
 *
 */
public class HighScoreTable implements Table{
	private static final Log LOG = new Log(HighScoreTable.class.getName());

	// Table attributes
	public static final String NAME = "HighScore";
	private static boolean created = false;

	// Column Attributes
	private String pkUsername = "username";
	private String attHighScore = "highscore";

	@Override
	public boolean isCreated() {
		return created;
	}

	@Override
	public void createTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: HighScoreTable.createTable");
		
		//TODO check here to see if table is alread created from previous session
		
		if (!created) {
			// STEP 1: Create variables
			String sql = "CREATE TABLE " + NAME 
					+ " (" + pkUsername + " VARCHAR(255) not NULL, " 
					+ attHighScore + " Integer not NULL, " 
					+ " PRIMARY KEY (" + pkUsername + "))";
			Connection conn = null;
			Statement stmt = null;
			try {
				// STEP 2: Register JDBC driver
				Class.forName(DatabaseManager.JDBCDRIVER);

				// STEP 3: Open a connection
				LOG.log(LogLevel.INFO, "Connecting to a selected database...");
				conn = DriverManager.getConnection(DatabaseManager.DBURL);
				LOG.log(LogLevel.INFO, "Connected database successfully...");

				// STEP 4: Execute a query
				LOG.log(LogLevel.INFO, "Creating table in given database...");
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				created = true;
				LOG.log(LogLevel.INFO, "Created table in given database...");
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
			LOG.log(LogLevel.INFO, "End table creation.\n");
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to create table even though flag is true.");
		} // end if-else
	}
	
	public void dropTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: HighScoreTable.dropTable");
		if (created) {
			// STEP 1: Create Variables
			String sql = "DROP TABLE " + NAME;
			Connection conn = null;
			Statement stmt = null;
			try {
				// STEP 2: Register JDBC driver
				Class.forName(DatabaseManager.JDBCDRIVER);

				// STEP 3: Open a connection
				LOG.log(LogLevel.INFO, "Connecting to a selected database...");
				conn = DriverManager.getConnection(DatabaseManager.DBURL);
				LOG.log(LogLevel.INFO, "Connected database successfully...");

				// STEP 4: Execute a query
				LOG.log(LogLevel.INFO, "Dropping table in given database...");
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				created = false;
				LOG.log(LogLevel.INFO, "Dropped table in given database...");
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
			LOG.log(LogLevel.INFO, "End table drop.\n");
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to drop table even though flag is false.");
		} // end if-else
	}

	/**
	 * Records a users high score.
	 * Includes logic to determine if high score is better than the current high score on record for a user.
	 * @param username - unique name for user.
	 * @param highscore - int highscore to be evaluated and stored.
	 */
	public void recordHighScore(String username, int highscore) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: HighScoreTable.recordHighScore");
		if (created) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sqlGetUser = "SELECT * FROM " + NAME + " WHERE " + pkUsername + " = '" + username + "'";
			String sqlInsert = "INSERT INTO " + NAME + " VALUES ('" + username + "', " + highscore + ")";
			String sqlUpdate = "UPDATE " + NAME + " SET " + attHighScore + " = " + highscore + " WHERE "
					+ pkUsername + " = '" + username + "'";
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
				LOG.log(LogLevel.INFO, "Checking to see if user has previous highscore set...");
				rs = stmt.executeQuery(sqlGetUser);
				boolean found = false;
				int result = Integer.MAX_VALUE;
				while (rs.next()) {
					found = true;
					result = rs.getInt(attHighScore);
				}

				// STEP 6: Update or create new
				if (found) {
					if(highscore < result) {
						LOG.log(LogLevel.INFO, "User has been found and highscore is less than previous high score.  Attempting to update highscore...");
						stmt.executeUpdate(sqlUpdate);
						LOG.log(LogLevel.INFO, "User highscore has been updated successfully...");
					} else {
						LOG.log(LogLevel.INFO, "User has been found, but highscore is more than previous so high score has not been updated.");
					}
				} else {
					LOG.log(LogLevel.INFO, "User has not been found.  Attempting to add new highscore...");
					stmt.execute(sqlInsert);
					LOG.log(LogLevel.INFO, "User successfully created...");
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
			LOG.log(LogLevel.INFO, "End user high score recording.\n");
		} else {
			LOG.log(LogLevel.WARNING,
					"Attempted to access table that has not been created. User record high score no executed.");
		}
	}
	
	/**
	 * Get a user's high score.
	 * @param username - unique name for user.
	 * @return - int highscore or -1 if not found.
	 */
	public int getHighScore(String username) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: HighScoreTable.getDifficultyLevel");
		int result = -1;
		if (created) {
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

				LOG.log(LogLevel.INFO, "Checking to see if user is in table...");
				rs = stmt.executeQuery(sqlGetUser);
				boolean found = false;
				while (rs.next()) {
					found = true;
					LOG.log(LogLevel.INFO, "User has been found.  Attempting to get high score...");
					result = rs.getInt(attHighScore);
					LOG.log(LogLevel.INFO, "User high score has been found successfully...");
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
			LOG.log(LogLevel.INFO, "End getting high score.\n");
		} else {
			LOG.log(LogLevel.WARNING,
					"Attempted to access table that has not been created. User high score no executed.");
		}
		return result;
	}
}//end class
