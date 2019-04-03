package edu.winona.cs.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * Saves the state of a users game when exiting.
 * 
 * Preferred usage:
 * 
 * DatabaseManager dbm = new DatabaseManager(); 
 * SaveStateTable sst = DatabaseManager.getSaveStateTable(); 
 * sst.createSaveState(username, file, clickCount); 
 * File saveState = gst.getSaveState(username);
 * 
 * 
 * @author Kyle Aure
 *
 */
public class SaveStateTable implements Table {
	private static final Log LOG = new Log(SaveStateTable.class.getName());

	// Table attributes
	public static final String NAME = "savestate";
	private static boolean created = false;

	// Column Attributes
	private String pkUsername = "username";
	private String attSerializedFile = "filePath"; // path to serialized state of board

	@Override
	public boolean isCreated() {
		return created;
	}

	@Override
	public void createTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: SaveStateTable.createTable");
		
		//TODO check here to see if table is alread created from previous session

		// Check to see if table has not previously been created
		if (!created) {
			// SQL Statement to be run to create table.
			String sql = "CREATE TABLE " + NAME + " (" + pkUsername + " VARCHAR(255) not NULL, " + attSerializedFile
					+ " VARCHAR(255) not NULL, " + " PRIMARY KEY (" + pkUsername
					+ "))";
			created = DatabaseUtil.createTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to create table even though flag is true.");
		}
	}

	@Override
	public void dropTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: SaveStateTable.dropTable");

		// Check to see if table has previously been created
		if (created) {
			String sql = "DROP TABLE " + NAME;
			created = DatabaseUtil.dropTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to drop table even though flag is false.");
		}

	}

	/**
	 * Creates a new save state for a user or updates their previous save state.
	 * @param username - unique name for user.
	 * @param file - Serialized File object.
	 * @param clickCount - current number of clicks for user.
	 */
	public void createSaveState(String username, File file) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: SaveStateTable.createSaveState");
		if (created) {
			// STEP 1: Create JDBC Variables
			Connection conn = null;
			Statement stmt = null;
			ResultSet rs = null;
			String sqlGetUser = "SELECT * FROM " + NAME + " WHERE " + pkUsername + " = '" + username + "'";
			String sqlInsert = "INSERT INTO " + NAME 
					+ " VALUES ('" + username + "', '" + file.getAbsolutePath() + "')";
			String sqlUpdateFile = "UPDATE " + NAME 
					+ " SET " + attSerializedFile + " = '" + file.getAbsolutePath() + "' " 
					+ " WHERE " + pkUsername + " = '" + username + "'";
			try {
				// STEP 2: Register JDBC driver
				Class.forName(DatabaseManager.JDBCDRIVER);

				// STEP 3: Open a connection
				LOG.log(LogLevel.INFO, "Connecting to a selected database...");
				conn = DriverManager.getConnection(DatabaseManager.DBURL);
				LOG.log(LogLevel.INFO, "Connected database successfully...");

				// STEP 4: Execute a query
				LOG.log(LogLevel.INFO, "Creating statement...");
				stmt = conn.createStatement();
				
				// STEP 5: Check to see if user already exists
				LOG.log(LogLevel.INFO, "Checking to see if user has previous save state set...");
				rs = stmt.executeQuery(sqlGetUser);
				boolean found = false;
				while (rs.next()) {
					found = true;
				}

				// STEP 6: Update or create new
				if (found) {
					LOG.log(LogLevel.INFO, "User has been found. Attempting to update save state...");
						stmt.executeUpdate(sqlUpdateFile);
					LOG.log(LogLevel.INFO, "User save state has been updated successfully...");
				} else {
					LOG.log(LogLevel.INFO, "User has not been found.  Attempting to add new save state...");
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
			LOG.log(LogLevel.INFO, "End user creation.\n");
		} else {
			LOG.log(LogLevel.WARNING,
					"Attempted to access table that has not been created. User creation no executed.");
		}
	}

	/**
	 * Returns file for saved state of game.
	 * 
	 * @param username - unique name of user
	 * @return File object or null if not found.
	 */
	public File getSaveState(String username) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: SaveStateTable.getSaveState");
		File result = null;
		if (created) {
			// STEP 1: Create variables assume user is not verified.
			Connection conn = null;
			Statement stmt = null;
			String sql = "SELECT * FROM " + NAME + " WHERE " + pkUsername + " = '" + username + "'";
			try {
				// STEP 2: Register JDBC driver
				Class.forName(DatabaseManager.JDBCDRIVER);

				// STEP 3: Open a connection
				LOG.log(LogLevel.INFO, "Connecting to a selected database...");
				conn = DriverManager.getConnection(DatabaseManager.DBURL);
				LOG.log(LogLevel.INFO, "Connected database successfully...");

				// STEP 4: Execute a query
				LOG.log(LogLevel.INFO, "Creating statement...");
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);

				// STEP 5: Extract data from result set
				LOG.log(LogLevel.INFO, "Extracting results...");
				while (rs.next()) {
					// Retrieve by column name
					String filePath = rs.getString(attSerializedFile);
					result = new File(filePath);
				}
				rs.close();
				LOG.log(LogLevel.INFO, "Finished extracting results...");
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
			LOG.log(LogLevel.INFO, "End verification.\n");
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to access table that has not been created. Result returned null.");
		}
		return result;
	}
}
