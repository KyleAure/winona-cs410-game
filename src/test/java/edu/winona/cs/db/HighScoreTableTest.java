package edu.winona.cs.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HighScoreTableTest {
	private String username = "Kyle";
	private int highScore = 1546;
	private int lowerHighScore = 200;
	private int higherHighScore = 2000;
	private static HighScoreTable hst;
	private static DatabaseManager dbm = new DatabaseManager();
	
	@BeforeClass
	public static void init() {
		dbm.createDatabase();
	}
	
	@Before
	public void setUp() {
		hst = dbm.getScoreTable();
	}
	
	@After
	public void tearDown() {
		hst.dropTable();
	}
	
	@Test
	public void recordUserSettingLowerTest() {
		//Create user
		hst.recordHighScore(username, highScore);
		
		//update user
		hst.recordHighScore(username, lowerHighScore);
		
		//Assert user difficulty was updated
		assertEquals(hst.getHighScore(username), lowerHighScore);
	}
	
	@Test
	public void recordUserSettingHigherTest() {
		//Create user
		hst.recordHighScore(username, highScore);
		
		//update user
		hst.recordHighScore(username, higherHighScore);
		
		//Assert user difficulty was updated
		assertNotEquals(hst.getHighScore(username), higherHighScore);
	}

}
