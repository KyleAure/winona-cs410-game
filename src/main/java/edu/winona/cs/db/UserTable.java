package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Creates a USER table and allows users to create and verify users.
 * 
 * @author Kyle Aure
 */
public class UserTable implements Table {
	//Table attributes
	public static final String NAME = "users";
	private static boolean created = false;
	
	//Column Attributes
	private String primaryKey = "username";
	private String attPassword = "password";
	
	@Override
	public void createTable() {
		System.out.println("Attempting database connection from: UserTable.createTable");
		if(!created) {
		   //STEP 1: Create variables
		   String sql = "CREATE TABLE " + NAME + " (" + 
					primaryKey + " VARCHAR(255) not NULL, " +
					attPassword + " VARCHAR(255) not NULL, " +
					" PRIMARY KEY (" + primaryKey + "))";
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DatabaseManager.JDBCDRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DatabaseManager.DBURL);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      stmt.executeUpdate(sql);
		      created = true;
		      System.out.println("Created table in given database...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("End table creation.\n");
		} else {
			System.out.println("Warning: attempted to create table even though flag is true.");
		}//end if-else
	}
	
	public void dropTable() {
		System.out.println("Attempting database connection from: UserTable.dropTable");
		if(created) {
		   //STEP 1: Create Variables
		   String sql = "DROP TABLE " + NAME;
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DatabaseManager.JDBCDRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DatabaseManager.DBURL);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Dropping table in given database...");
		      stmt = conn.createStatement();
		      stmt.executeUpdate(sql);
		      created = false;
		      System.out.println("Dropped table in given database...");
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("End table drop.\n");
		} else {
			System.out.println("Warning: attempted to drop table even though flag is false.");
		}//end if-else
	}

	@Override
	public boolean isCreated() {
		return created;
	}
	
	public void createUser(String username, String password) throws IllegalArgumentException {
		System.out.println("Attempting database connection from: UserTable.createUser");
		if(created) {
			   Connection conn = null;
			   Statement stmt = null;
			   String sql = "INSERT INTO " + NAME + " VALUES ('" + username + "', '" + password + "')";
			   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(DatabaseManager.JDBCDRIVER);
		
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DatabaseManager.DBURL);
			      System.out.println("Connected database successfully...");
			      
			      //STEP 4: Execute a query
			      System.out.println("Creating statement...");
			      stmt = conn.createStatement();
			      stmt.execute(sql);
			      System.out.println("User successfully created...");
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      throw new IllegalArgumentException("Failed to create user due to non-unique username.");
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("End user creation.\n");
		} else {
			System.out.println("Attempted to access table that has not been created. User creation no executed.");
		}
	}

	public boolean verifyUser(String username, String password) {
		System.out.println("Attempting database connection from: UserTable.verifyUser");
		if(created) {
			   //STEP 1: Create variables assume user is not verified. 
			   boolean result = false;
			   Connection conn = null;
			   Statement stmt = null;
			   String sql = "SELECT * FROM " + NAME + " WHERE " + primaryKey + " = '" + username + "'";
			   try{
			      //STEP 2: Register JDBC driver
			      Class.forName(DatabaseManager.JDBCDRIVER);
		
			      //STEP 3: Open a connection
			      System.out.println("Connecting to a selected database...");
			      conn = DriverManager.getConnection(DatabaseManager.DBURL);
			      System.out.println("Connected database successfully...");
			      
			      //STEP 4: Execute a query
			      System.out.println("Creating statement...");
			      stmt = conn.createStatement();
			      ResultSet rs = stmt.executeQuery(sql);
			      System.out.println("Extracting results...");
			      
			      //STEP 5: Extract data from result set
			      System.out.println("Comparing results...");
			      while(rs.next()){
			         //Retrieve by column name
			         String passCompare = rs.getString(attPassword);   
			         //Compare passwords
			         if(passCompare.compareTo(password) == 0 ) {
			        	 result = true;
			         }
			      }
			      rs.close();
			      System.out.println("Finished comparing results...");
			   }catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
			   }//end try
			   System.out.println("End verification.\n");
			   return result;
		} else {
			System.out.println("Attempted to access table that has not been created. Verfication returned false.");
			return false;
		}
	}

	@Override
	public String toString() {
		//Check if table has been created.
		if(created) {	
		   String results = NAME + ": List of Entries\n";
		   results += primaryKey + "\t" + attPassword; 
		   results += "\n------------------------\n";
		   Connection conn = null;
		   Statement stmt = null;
		   try{
		      //STEP 2: Register JDBC driver
		      Class.forName(DatabaseManager.JDBCDRIVER);
	
		      //STEP 3: Open a connection
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(DatabaseManager.DBURL);
		      System.out.println("Connected database successfully...");
		      
		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
	
		      String sql = "SELECT * FROM " + NAME;
		      ResultSet rs = stmt.executeQuery(sql);
		      //STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         String username = rs.getString(primaryKey);
		         String password = rs.getString(attPassword);
		         
		         results += username + "\t";
		         results += password + "\n";
		      }
		      rs.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }// do nothing
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("End table string creation.\n");
		   return results;
		} else {
			return "User table has not been create. No data to display.";
		}
	} // end toString	
} //end class
