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
	//STEP 1: Define location and name of embedded database
	public static final String DBNAME = "puzzledb";
	public static final String DBURL = "jdbc:derby:" + DBNAME;
	public static final String JDBCDRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
	
	//Private variables
	private static final Log LOG = new Log("DatabaseManager");
	private static final String DBCREATEURL = "jdbc:derby:" + DBNAME + ";create=true";
	private static boolean created = false;
	
	/**
	 * Creates database and will set the created variable to True if successful.
	 */
	public static void createDatabase() {
		LOG.log(LogLevel.INFO,"Attempting database connection from: createDatabase");
		//If database has not been created
		if(!created) {
			//Create it
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
		      LOG.log(e, LogLevel.SEVERE, "SQLException thrown while creating database.");
		   }catch(Exception e){
		      //Handle errors for Class.forName
		     LOG.log(e, LogLevel.SEVERE, "Exception registering JDBC Driver");
		   }finally{
		      //finally block used to close resources
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException e){
		         LOG.log(e, LogLevel.WARNING, "SQLException thrown while closing connection.");
		      }//end finally try
		   }//end try
		   LOG.log(LogLevel.INFO, "End database creation.\n");
		//If the database has been created
		} else {
			LOG.log(LogLevel.WARNING, "Attempted to create database even though flag is true.");
		}
	}
	
	public static boolean isCreated() {
		return created;
	}
	
	/**
	 * Returns a string representation of the database by the
	 * tables that the database has included.
	 */
	public static String getTables() {
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
			LOG.log(e, LogLevel.WARNING, "Connection to database failed.");
		}
		return results;
	}
}
