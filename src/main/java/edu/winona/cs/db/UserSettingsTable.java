package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.winona.cs.gamelogic.DifficultyLevel;
import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * Creates user settings table.
 * Preferred usage:
 * 
 * DatabaseManager dbm = new DatabaseManager();
 * UserSettingsTable ust = DatabaseManager.getUserSettingsTable();
 * ust.recordUserDifficulty(username, DifficultyLevel.level);
 * Difficulty level = gst.getDifficultyLevel(username);
 * 
 * @author Kyle Aure
 */
public class UserSettingsTable implements Table {
	private static final Log LOG = new Log(UserSettingsTable.class.getName());

	// Table attributes
	public static final String NAME = "UserSettings";
	private static boolean created = false;

	// Column Attributes
	private String pkUsername = "username";
	private String attDifficulty = "difficulty";

	@Override
	public boolean isCreated() {
		return created;
	}

	@Override
	public void createTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: UserSettingsTable.createTable");

		if (!created) {
			String sql = "CREATE TABLE " + NAME 
					+ " (" + pkUsername + " VARCHAR(255) not NULL, " 
					+ attDifficulty + " Integer not NULL, " 
					+ " PRIMARY KEY (" + pkUsername + "))";
			created = DatabaseUtil.createTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to create table even though flag is true.");
		} // end if-else
	}

	@Override
	public void dropTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: UserSettingsTable.dropTable");
		//Check to see if table has previously been created
		if (created) {
			String sql = "DROP TABLE " + NAME;
			created = DatabaseUtil.dropTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to drop table even though flag is false.");
		}
	}

	/**
	 * Record a user's difficulty level of choice, or update the difficulty level if necessary.
	 * @param username - unique name of user.
	 * @param level - DifficultyLevel datatype.
	 */
	public void recordUserDifficultyLevel(String username, DifficultyLevel level) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: UserSettingsTable.recordUserDifficultyLevel");
		if (created) {
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sqlGetUser = "SELECT * FROM " + NAME + " WHERE " + pkUsername + " = '" + username + "'";
			String sqlInsert = "INSERT INTO " + NAME + " VALUES ('" + username + "', " + level.getDifficulty() + ")";
			String sqlUpdate = "UPDATE " + NAME + " SET " + attDifficulty + " = " + level.getDifficulty() + " WHERE "
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
				LOG.log(LogLevel.INFO, "Checking to see if user has previous difficulty set...");
				rs = stmt.executeQuery(sqlGetUser);
				boolean found = false;
				while (rs.next()) {
					found = true;
				}

				// STEP 6: Update or create new
				if (found) {
					LOG.log(LogLevel.INFO, "User has been found.  Attempting to update difficulty...");
					stmt.executeUpdate(sqlUpdate);
					LOG.log(LogLevel.INFO, "User difficulty has been updated successfully...");
				} else {
					LOG.log(LogLevel.INFO, "User has not been found.  Attempting to add new user difficulty...");
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
			LOG.log(LogLevel.INFO, "End user difficulty recording.\n");
		} else {
			LOG.log(LogLevel.WARNING,
					"Attempted to access table that has not been created. User difficulty no executed.");
		}
	}

	/**
	 * Returns a users saved difficulty level.
	 * @param username - unique name of user.
	 * @return DifficultyLevel datatype.
	 */
	public DifficultyLevel getDifficultyLevel(String username) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: UserSettingsTable.getDifficultyLevel");
		int result = 0;
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

				LOG.log(LogLevel.INFO, "Checking to see if user has previous difficulty set...");
				rs = stmt.executeQuery(sqlGetUser);
				boolean found = false;
				while (rs.next()) {
					found = true;
					LOG.log(LogLevel.INFO, "User has been found.  Attempting to get difficulty...");
					result = rs.getInt(attDifficulty);
					LOG.log(LogLevel.INFO, "User difficulty has been found successfully...");
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
			LOG.log(LogLevel.INFO, "End getting difficulty.\n");
		} else {
			LOG.log(LogLevel.WARNING,
					"Attempted to access table that has not been created. User difficulty no executed.");
		}
		return DifficultyLevel.get(result);
	}
}// end class
