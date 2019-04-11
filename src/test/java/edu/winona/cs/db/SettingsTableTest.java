package edu.winona.cs.db;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.winona.cs.component.GameSettings;
import edu.winona.cs.gamelogic.DifficultyLevel;

public class SettingsTableTest {
	private String username = "Kyle";
	private Color c1 = Color.BLACK;
	private Color c2 = Color.RED;
	private boolean tracking1 = true;
	private boolean tracking2 = false;
	private DifficultyLevel level1 = DifficultyLevel.EASY;
	private DifficultyLevel level2 = DifficultyLevel.HARD;
	private static SettingsTable st;
	private static DatabaseManager dbm;
	
	@BeforeClass
	public static void init() {
		dbm = DatabaseManager.getDatabaseManager();
	}
	
	@Before
	public void setUp() {
		st = dbm.getSettingsTable();
	}
	
	@After
	public void tearDown() {
		st.dropTable();
	}
	
	@Test
	public void createGameSettings() {
		GameSettings settings = new GameSettings(c1, tracking1, level1);
		st.recordSetting(username, settings);
		assertEquals(st.getGameSetting(username).getBackgroundColor(), settings.getBackgroundColor());
		assertEquals(st.getGameSetting(username).isHighScoreTracking(), settings.isHighScoreTracking());
		assertEquals(st.getGameSetting(username).getDifficulty(), settings.getDifficulty());
	}
	
	@Test
	public void updateColor() {
		GameSettings settings = new GameSettings(c1, tracking1, level1);
		GameSettings settings2 = new GameSettings(c2, tracking1, level1);
		st.recordSetting(username, settings);
		st.recordSetting(username, settings2);
		GameSettings result = st.getGameSetting(username);
		assertEquals(result.getBackgroundColor(), settings2.getBackgroundColor());
	}
	
	@Test
	public void updateTracking() {
		GameSettings settings = new GameSettings(c1, tracking1, level1);
		GameSettings settings2 = new GameSettings(c1, tracking2, level1);
		st.recordSetting(username, settings);
		st.recordSetting(username, settings2);
		GameSettings result = st.getGameSetting(username);
		assertEquals(result.isHighScoreTracking(), settings2.isHighScoreTracking());
	}
	
	@Test
	public void updateDifficulty() {
		GameSettings settings = new GameSettings(c1, tracking1, level1);
		GameSettings settings2 = new GameSettings(c1, tracking1, level2);
		st.recordSetting(username, settings);
		st.recordSetting(username, settings2);
		GameSettings result = st.getGameSetting(username);
		assertEquals(result.getDifficulty(), settings2.getDifficulty());
	}
}
