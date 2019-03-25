package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * Creates user table.
 * Preferred usage:
 * 
 * DatabaseManager dbm = new DatabaseManager();
 * UserTable ut = DatabaseManager.getUserTable();
 * boolean successful = ut.createUser(username, password);
 * boolean verified = ut.verifyUser(username, password);
 * 
 * @author Kyle Aure
 */
public class UserTable implements Table {
	private static final Log LOG = new Log(UserTable.class.getName());

	// Table attributes
	public static final String NAME = "users";
	private static boolean created = false;

	// Column Attributes
	private String pkUsername = "username";
	private String attPassword = "password";

	@Override
	public boolean isCreated() {
		return created;
	}

	@Override
	public void createTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: UserTable.createTable");

		// Check to see if table has not previously been created
		if (!created) {
			// SQL Statement to be run to create table.
			String sql = "CREATE TABLE " + NAME + " (" + pkUsername + " VARCHAR(255) not NULL, " + attPassword
					+ " VARCHAR(255) not NULL, " + " PRIMARY KEY (" + pkUsername + "))";
			created = DatabaseUtil.createTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to create table even though flag is true.");
		}
	}

	@Override
	public void dropTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: UserTable.dropTable");
		
		//Check to see if table has previously been created
		if (created) {
			String sql = "DROP TABLE " + NAME;
			created = DatabaseUtil.dropTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to drop table even though flag is false.");
		}
	}

	/**
	 * Create a new user and store their data in the userTable.
	 * @param username - unique name of user.
	 * @param password - hashed version of user's password.
	 * @return boolean: True - new user created, False - user creation failed.
	 */
	public boolean createUser(String username, String password) throws IllegalArgumentException{
		LOG.log(LogLevel.INFO, "Attempting database connection from: UserTable.createUser");
		boolean result = false; 
		if (created) {
			// STEP 1: Create Variables
			Connection conn = null;
			Statement stmt = null;
			String sql = "INSERT INTO " + NAME + " VALUES ('" + username + "', '" + password + "')";
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
				stmt.execute(sql);
				result = true;
				LOG.log(LogLevel.INFO, "User successfully created...");
			} catch (SQLException e) {
				// Handle errors for JDBC
				LOG.log(e, LogLevel.SEVERE, "");
				throw new IllegalArgumentException("Collision occures when trying to create a new user.");
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
		return result;
	}

	/**
	 * Verify a user's credentials 
	 * @param username - unique name for user.
	 * @param password - hashed password for user.
	 * @return Boolean: True - verification successful, False - verification unsuccessful. 
	 */
	public boolean verifyUser(String username, String password) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: UserTable.verifyUser");
		boolean result =  false;
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
				LOG.log(LogLevel.INFO, "Extracting results...");

				// STEP 5: Extract data from result set
				LOG.log(LogLevel.INFO, "Comparing results...");
				while (rs.next()) {
					// Retrieve by column name
					String passCompare = rs.getString(attPassword);
					// Compare passwords
					if (passCompare.compareTo(password) == 0) {
						result = true;
					}
				}
				rs.close();
				LOG.log(LogLevel.INFO, "Finished comparing results...");
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
			LOG.log(LogLevel.WARNING, "Attempted to access table that has not been created. Verification returned false.");
		}
		return result;
	}
} // end class
