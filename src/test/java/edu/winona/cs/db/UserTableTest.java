package edu.winona.cs.db;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

public class UserTableTest {

	
	static DatabaseManager dm;
	
	@BeforeClass
	public static void createDatabaseManager() {
		dm = new DatabaseManager();
	}
	
	@Test
	public void createTableTest() {
		try {
			Connection conn = dm.connectionToDerby();
			UserTable ut = new UserTable(conn);
			assertTrue("Should be true",ut.doesExist());
			ut.dropTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail(e.toString());
		}
		
		
		
	}
}
