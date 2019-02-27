package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	private static final String DBCREATEURL = "jdbc:derby:" + DBNAME + ";create=true";
	private static boolean created = false;
	
	/**
	 * Creates database and will set the created variable to True if successful.
	 */
	public static void createDatabase() {
		System.out.println("Attempting database connection from: createDatabase");
		//If database has not been created
		if(!created) {
			//Create it
		   Connection conn = null;
		   try{
		      //STEP 2: Register JDBC driver
			  Class.forName(JDBCDRIVER);
	
		      //STEP 3: Open a connection and create database
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DBCREATEURL);
		      System.out.println("Database created successfully...");
		      created = true;
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("End database creation.\n");
		//If the database has been created
		} else {
			System.out.println("Warning: attempted to create database even though flag is true.");
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
			System.out.println("Connection to database failed.");
		}
		return results;
	}
}
