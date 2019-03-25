package edu.winona.cs.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserTableTest {
	private String username = "Kyle";
	private String password = "Test1";
	private String password2 = "Test2";
	private static UserTable ut;
	private static DatabaseManager dbm = new DatabaseManager();
	
	@BeforeClass
	public static void init() {
		dbm.createDatabase();
	}

	
	@Before
	public void setUp() {
		ut = dbm.getUserTable();
	}
	
	@After
	public void tearDown() {
		ut.dropTable();
	}

	
	@Test
	public void createUserTest() {		
		//Create user
		assertTrue("Collision should not occure this user has not been created before.", ut.createUser(username, password));		
	}
	
	@Test
	public void verifyUserTest() {
		ut.createUser(username, password);
		
		//Assert user can be verified
		assertTrue("User should be in userTable.", ut.verifyUser(username, password));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCollision() {
		ut.createUser(username, password);
		
		//Assert false when trying to create another user with the same username
		assertFalse("User name should be taken.", ut.createUser(username, password2));
	}
	
	@Test
	public void failVerification() {
		//Create user
		ut.createUser(username, password);
		
		//Assert John is not in userTable
		assertFalse("John should not be in userTable", ut.verifyUser("John", password));
	}

	@Test
	public void dropedTable() {
		//First drop "Before" table
		ut.dropTable();
		
		//Ensure that calling dropTable again gracefully fails.
		ut.dropTable();
		
		//Ensure that calling createUser gracefully fails.
		assertFalse("Table not created. should return false", ut.createUser(username, password));
		
		//Ensure that calling verifyUser gracefully fails.
		assertFalse("Table not created. Should return false.", ut.verifyUser(username, password));
		
		//Restore dropped table
		ut.createTable();
	}
}
