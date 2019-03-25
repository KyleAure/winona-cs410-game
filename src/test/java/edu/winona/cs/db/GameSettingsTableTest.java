package edu.winona.cs.db;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.winona.cs.component.GameSettings;

public class GameSettingsTableTest {
	private String username = "Kyle";
	private Color c1 = Color.BLACK;
	private Color c2 = Color.RED;
	private boolean tracking1 = true;
	private boolean tracking2 = false;
	private static GameSettingsTable gst;
	private static DatabaseManager dbm = new DatabaseManager();
	
	@BeforeClass
	public static void init() {
		dbm.createDatabase();
	}
	
	@Before
	public void setUp() {
		gst = dbm.getGameSettingsTable();
	}
	
	@After
	public void tearDown() {
		gst.dropTable();
	}
	
	@Test
	public void createGameSettings() {
		GameSettings settings = new GameSettings(c1, tracking1);
		gst.recordGameSetting(username, settings);
		assertEquals(gst.getGameSetting(username).getBackgroundColor(), settings.getBackgroundColor());
		assertEquals(gst.getGameSetting(username).isHighScoreTracking(), settings.isHighScoreTracking());
	}
	
	@Test
	public void updateColor() {
		GameSettings settings = new GameSettings(c1, tracking1);
		GameSettings settings2 = new GameSettings(c2, tracking1);
		gst.recordGameSetting(username, settings);
		gst.recordGameSetting(username, settings2);
		assertEquals(gst.getGameSetting(username).getBackgroundColor(), settings2.getBackgroundColor());
		
	}
	
	@Test
	public void updateTracking() {
		GameSettings settings = new GameSettings(c1, tracking1);
		GameSettings settings2 = new GameSettings(c1, tracking2);
		gst.recordGameSetting(username, settings);
		gst.recordGameSetting(username, settings2);
		assertEquals(gst.getGameSetting(username).isHighScoreTracking(), settings2.isHighScoreTracking());
	}
}
