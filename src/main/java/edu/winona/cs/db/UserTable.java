package edu.winona.cs.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserTable {
	private Connection conn;
	
	public UserTable(Connection conn){
		this.conn=conn;
		
		//this checks if User table exists already
		//if it doesn't, create table.
		try {
			if(!this.doesExist()) {
				this.createTable();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void createTable() throws SQLException {
		
		Statement stmt = conn.createStatement();
		
		//Create Table
		stmt.execute("Create Table USERS (username varchar(15) primary key, hashpass varchar(30))"); //TODO hashpass length
		stmt.close();
		
	} //end createTable
	
	//method to check if table exists
	public boolean doesExist() throws SQLException{
		DatabaseMetaData dbm = conn.getMetaData();
		
		ResultSet tables = dbm.getTables(null, null, "USERS", null);
		boolean results = false;
		if(tables.next()) {
			results = true;
		}
		
		return results;
		
	}//end doesExist
	
	//method to add user to table
	public void addUser(String username, String hashpass) throws SQLException{
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("Insert into USERS values ('"+ username + "','" + hashpass + "')");
		stmt.close();
		
	}
	
	//method to drop table (after testing)
	public void dropTable() throws SQLException {
		Statement stmt = conn.createStatement();
		stmt.executeUpdate("Drop table USERS");
		stmt.close();
		
	}

} //end class
