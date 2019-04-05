package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.winona.cs.log.Log;
import edu.winona.cs.log.Log.LogLevel;

/**
 * Database Manager Class
 * 
 * This class creates the puzzledb.
 * Defines the database Name, URL, driver, and creation status of the database.
 * 
 * @author Kyle Aure and Erika Tix
 */
public class DatabaseManager {
	private static final Log LOG = new Log(DatabaseManager.class.getName());
	public static final String DBNAME = ".puzzledb";
	public static final String DBURL = "jdbc:derby:" + DBNAME;
	public static final String JDBCDRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String DBCREATEURL = "jdbc:derby:" + DBNAME + ";create=true";
	private static boolean created = false;
	
	/**
	 * Determines whether or not database has been created or not.
	 */
	public boolean isCreated() {
		return created;
	}
	
	/**
	 * Creates database and will set the created variable to True if successful.
	 */
	public void createDatabase() {
		LOG.log(LogLevel.INFO,"Attempting database connection from DatabaseManager.createDatabase");
		if(!created) {
			//STEP 1: Create JDBC variables
		   Connection conn = null;
		   try{
		      //STEP 2: Register JDBC driver
			  Class.forName(JDBCDRIVER);
	
		      //STEP 3: Open a connection and create database
		      LOG.log(LogLevel.INFO,"Connecting to database...");
		      conn = DriverManager.getConnection(DBCREATEURL);
		      LOG.log(LogLevel.INFO, "Database created successfully...");
		      created = true;
		   }catch(SQLException e){
		      //Handle errors for JDBC
		      LOG.log(e, LogLevel.SEVERE, "");
		   }catch(Exception e){
		      //Handle errors for Class.forName
		     LOG.log(e, LogLevel.SEVERE, "");
		   }finally{
		      //finally block used to close resources
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException e){
		         LOG.log(e, LogLevel.WARNING, "");
		      }//end finally try
		   }//end try
		   LOG.log(LogLevel.INFO, "End database creation.\n");
		//If the database has been created
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to create database even though flag is true.");
		}
	}
	
	/**
	 * Returns a string representation of the database by the
	 * tables that the database has included.
	 */
	public String getTablesString() {
		String results = "Printing Tables";
		results += "\n---------------------\n";
		Connection conn;
		try {
			conn = DriverManager.getConnection(DBURL);
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet resultSet = dbmd.getTables(null, null, null, new String[]{"TABLE"});
			while(resultSet.next()) {
				results += resultSet.getString("TABLE_NAME") + "\n";
			}
		} catch (SQLException e) {
			LOG.log(e, LogLevel.WARNING, "");
		}
		return results;
	}
	
	/**
	 * Returns the UserTable object to be used for database connectivity.
	 * @return UserTable
	 */
	public UserTable getUserTable() {
		UserTable ut = new UserTable();
		LOG.log(LogLevel.INFO, "Request for UserTable from DatabaseManager received.");
		if(!ut.isCreated()) {
			LOG.log(LogLevel.INFO, "DatabaseManager creating UserTable.");
			ut.createTable();
		}
		return ut;
	}
	
	/**
	 * Returns the UserSettingsTable object to be used for database connectivity.
	 * @return UserSettingsTable
	 */
	public UserSettingsTable getUserSettingsTable() {
		UserSettingsTable ust = new UserSettingsTable();
		LOG.log(LogLevel.INFO, "Request for UserSettingsTable from DatabaseManager received.");
		if(!ust.isCreated()) {
			LOG.log(LogLevel.INFO, "DatabaseManager creating UserSettingsTable.");
			ust.createTable();
		}
		return ust;
	}
	
	/**
	 * Returns the HighScoreTable object to be used for database connectivity.
	 * @return HighScoreTable
	 */
	public HighScoreTable getScoreTable() {
		HighScoreTable st = new HighScoreTable();
		LOG.log(LogLevel.INFO, "Request for ScoreTable from DatabaseManager received.");
		if(!st.isCreated()) {
			LOG.log(LogLevel.INFO, "DatabaseManager creating ScoreTable.");
			st.createTable();
		}
		return st;
	}
	
	/**
	 * Returns the SaveStateTable object to be used for database connectivity.
	 * @return SaveStateTable
	 */
	public SaveStateTable getSaveStateTable() {
		SaveStateTable sst = new SaveStateTable();
		LOG.log(LogLevel.INFO, "Request for SaveStateTable from DatabaseManager received.");
		if(!sst.isCreated()) {
			LOG.log(LogLevel.INFO, "DatabaseManager creating SaveStateTable.");
			sst.createTable();
		}
		return sst;
	}
	
	/**
	 * Returns the ImageTable object to be used for database connectivity.
	 * @return ImageTable
	 */
	public ImageTable getImageTable() {
		ImageTable it = new ImageTable();
		LOG.log(LogLevel.INFO, "Request for ImageTable from DatabaseManager received.");
		if(!it.isCreated()) {
			LOG.log(LogLevel.INFO, "DatabaseManager creating ImageTable.");
			it.createTable();
		}
		return it;
	}
	
	/**
	 * Returns the GameSettingsTable object to be used for database connectivity.
	 * @return GameSettingsTable
	 */
	public GameSettingsTable getGameSettingsTable() {
		GameSettingsTable gst = new GameSettingsTable();
		LOG.log(LogLevel.INFO, "Request for GameSettingsTable from DatabaseManager received.");
		if(!gst.isCreated()) {
			LOG.log(LogLevel.INFO, "DatabaseManager creating GameSettingsTable.");
			gst.createTable();
		}
		return gst;
	}
}
