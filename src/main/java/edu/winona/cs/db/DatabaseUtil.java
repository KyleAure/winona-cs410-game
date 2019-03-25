package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * Database utility methods to help perform routine tasks. 
 * @author Kyle Aure
 */
public class DatabaseUtil {
	private static final Log LOG = new Log(DatabaseUtil.class.getName());
	
	/**
	 * Utility method used to create a table by the TABLE classes.
	 * 
	 * @param tableName - Name of table to be referenced when making queries.
	 * @param SQLStatement - SQL statement to create this table with the correct attributes.
	 * @return Status of Table: True = created, False =  not created 
	 */
	public static boolean createTable(String tableName, String SQLStatement) {
		// STEP 1: Create JDBC variables
		Connection conn = null;
		Statement stmt = null;
		boolean result = false; //Assume that table creation fails
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
			stmt.executeUpdate(SQLStatement);
			result = true;
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
		LOG.log(LogLevel.INFO, "End " + tableName + " table creation.\n");
		return result;
	}
	
	/**
	 * Utility method used to drop a table by the TABLE classes.
	 * 
	 * @param tableName - Name of table to be referenced when making queries.
	 * @param SQLStatement - SQL statement to drop this table.
	 * @return Status of Table: True = not dropped, False = dropped
	 */
	public static boolean dropTable(String tableName, String SQLStatement) {
		// STEP 1: Create JDBC variables
		Connection conn = null;
		Statement stmt = null;
		boolean result = true;
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
			stmt.executeUpdate(SQLStatement);
			result = false;
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
		LOG.log(LogLevel.INFO, "End " + tableName + " table drop.\n");
		return result;
	}
}
