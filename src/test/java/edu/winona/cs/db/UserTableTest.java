package edu.winona.cs.db;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.winona.cs.log.Log.LogLevel;
import edu.winona.cs.log.LogTest;

/**
 * Tests the UserTable class
 * 
 * @author Kyle Jon Aure
 */
public class UserTableTest {
	private static final LogTest LOG = new LogTest("UserTableTest"); 
	private UserTable ut = new UserTable();
	private String username = "Kyle";
	private String password = "Test1";
	private String password2 = "Test2";
	
	@BeforeClass
	public static void init() {
		DatabaseManager.createDatabase();
	}
	
	@Before
	public void setUp() {
		ut.createTable();
	}
	
	@After
	public void tearDown() {
		ut.dropTable();
	}

	
	@Test
	public void createUserTest() {
		//Print list of tables
		LOG.log(LogLevel.INFO, DatabaseManager.getTables());
		
		//Create user
		ut.createUser(username, password);
		
		//Print list of users
		LOG.log(LogLevel.INFO, ut.toString());
		
		//Assert Kyle can be verified
		assertTrue("Kyle should be in userTable.", ut.verifyUser(username, password));
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void duplicateUserTest() {		
		//Create user
		ut.createUser(username, password);
		
		//Create user with same name
		ut.createUser(username, password2);
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
		ut.createUser(username, password);
		
		//Ensure that calling verifyUser gracefully fails.
		assertFalse("Table not created. Should return false.", ut.verifyUser(username, password));
		
		//Restore dropped table
		ut.createTable();
	}
}
