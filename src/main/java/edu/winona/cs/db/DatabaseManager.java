package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//TODO: Remove this class and develop a database interface to create new users. 
public class DatabaseManager {
	//connection variable to database
	private Connection conn;
	
	
	/**
	 * Establishes connection to our database
	 * @throws SQLException
	 */
	public Connection connectionToDerby() throws SQLException {
		String dbURL = "jdbc:derby:puzzledb;create=true";
		conn = DriverManager.getConnection(dbURL);
		return conn;
	}
	
	public String sampleDBUsabe() throws SQLException {
		Statement stmt = conn.createStatement();
		String results = "";
		
		//Create Table
		stmt.execute("Create Table USERS (id int primary key, name varchar(30))");
		
		//Insert two rows
		stmt.executeUpdate("insert into USERS values (1, 'Tom')");
		stmt.executeUpdate("insert into USERS values (2,'Peter')");
		
		//Query
		ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");
		
		//Print results
		while (rs.next()) {
			results += String.format("%d\t%s\n", rs.getInt("id"), rs.getString("name"));
		}
		
		//Drop table
		stmt.executeUpdate("Drop Table USERS");
		
		return results;
	}
}
