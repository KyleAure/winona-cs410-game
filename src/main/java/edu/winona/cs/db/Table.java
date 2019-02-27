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
	public abstract void createTable();
	
	/**
	 * Returns the created attribute
	 * @return - boolean: True: table is accessible, False: table is not-accessible.
	 */
	public abstract boolean isCreated();
	
	/**
	 * Outputs a string representation of the table.
	 */
	public abstract String toString();
	
}
