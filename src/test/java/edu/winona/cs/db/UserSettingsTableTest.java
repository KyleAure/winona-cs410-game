package edu.winona.cs.db;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.winona.cs.gamelogic.DifficultyLevel;

public class UserSettingsTableTest {
	private String username = "Kyle";
	private DifficultyLevel difficulty = DifficultyLevel.HARD;
	private DifficultyLevel updateDifficulty = DifficultyLevel.EASY;
	private static UserSettingsTable ust;
	private static DatabaseManager dbm = new DatabaseManager();
	
	@BeforeClass
	public static void init() {
		dbm.createDatabase();
	}
	
	@Before
	public void setUp() {
		ust = dbm.getUserSettingsTable();
	}
	
	@After
	public void tearDown() {
		ust.dropTable();
	}
	
	@Test
	public void recordUserSettingTest() {
		//Create user
		ust.recordUserDifficultyLevel(username, difficulty);
		
		//Assert difficulty has been saved
		assertEquals(ust.getDifficultyLevel(username), difficulty);
	}
	
	@Test
	public void updateUserSettingsTest() {
		//create user
		ust.recordUserDifficultyLevel(username, difficulty);
		
		//update user
		ust.recordUserDifficultyLevel(username, updateDifficulty);
		
		//Assert user difficulty was updated
		assertEquals(ust.getDifficultyLevel(username), updateDifficulty);
	}
}
