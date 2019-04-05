package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * Stores the location(s) of pictures uploaded by a user.
 * 
 * Preferred usage:
 * 
 * DatabaseManager dbm = new DatabaseManager();
 * ImageTable it = DatabaseManager.getImageTable();
 * it.storeImage(username, imageLocation);
 * ArrayList<String> images = gst.getImages(username);
 * 
 * @author Kyle Aure
 *
 */
public class ImageTable implements Table{
	private static final Log LOG = new Log(ImageTable.class.getName());

	// Table attributes
	public static final String NAME = "IMAGES";
	private static boolean created = false;

	// Column Attributes
	private String userName = "username";
	private String attImageLocation = "imageLocation";

	@Override
	public boolean isCreated() {
		return created;
	}
	
	@Override
	public void setCreated(boolean status) {
		ImageTable.created = status;
	}

	@Override
	public void createTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: ImageTable.createTable");

		// Check to see if table has not previously been created
		if (!created) {
			// SQL Statement to be run to create table.
			String sql = "CREATE TABLE " + NAME + " (" 
					+ userName + " VARCHAR(255) not NULL, " 
					+ attImageLocation + " VARCHAR(255) not NULL)";
			created = DatabaseUtil.createTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to create table even though flag is true.");
		}	
	}

	@Override
	public void dropTable() {
		LOG.log(LogLevel.INFO, "Attempting database connection from: ImageTable.dropTable");
		
		//Check to see if table has previously been created
		if (created) {
			String sql = "DROP TABLE " + NAME;
			created = DatabaseUtil.dropTable(NAME, sql);
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to drop table even though flag is false.");
		}
	}
	
	/**
	 * Stores the location of an image that a user has uploaded.
	 * @param username - unique name for user.
	 * @param imageLocation - filepath to image
	 */
	public void storeImage(String username, String imageLocation) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: ImageTable.storeImage");
		if (created) {
			// STEP 1: Create Variables
			Connection conn = null;
			Statement stmt = null;
			String sql = "INSERT INTO " + NAME + " VALUES ('" + username + "', '" + imageLocation + "')";
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
				LOG.log(LogLevel.INFO, "User successfully created...");
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
			LOG.log(LogLevel.INFO, "End image storage creation.\n");
		} else {
			LOG.log(LogLevel.WARNING,
					"Attempted to access table that has not been created. User creation no executed.");
		}
	}
	
	/**
	 * Gets a list of images that a user has uploaded.
	 * @param username - unique name of user.
	 * @return - ArrayList of filepaths to images.
	 */
	public ArrayList<String> getImages(String username) {
		LOG.log(LogLevel.INFO, "Attempting database connection from: ImageTable.getImages");
		ArrayList<String> results = new ArrayList<>();
		if (created) {
			// STEP 1: Create variables assume user is not verified.
			Connection conn = null;
			Statement stmt = null;
			String sql = "SELECT * FROM " + NAME + " WHERE " + userName + " = '" + username + "'";
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
					results.add(rs.getString(attImageLocation));
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
			LOG.log(LogLevel.WARNING, "Attempted to access table that has not been created. Result returned empty arraylist.");
		}
		return results;
	}
}
