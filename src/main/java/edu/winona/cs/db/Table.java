package edu.winona.cs.db;

/**
 * Defines attributes and methods to be included in any class 
 * that represents a table in the database.
 * 
 * @author Kyle Aure
 */
public interface Table {
	
	/**
	 * Creates this table in the database and 
	 * will change the created attribute to true if successful.
	 */
	public void createTable();
	
	/**
	 * Drops this table if it is in the database and 
	 * will change the created attribute to false if successful.
	 */
	public void dropTable(); 
	
	/**
	 * Returns the created attribute
	 * @return - boolean: True: table is accessible, False: table is not-accessible.
	 */
	public boolean isCreated();
	
	/**
	 * Set the created status for this table.
	 * @param status 
	 */
	public void setCreated(boolean status);
	
}
