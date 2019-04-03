package edu.winona.cs.db;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SaveStateTableTest {
	private String username = "Kyle";
	private String serializedFile = "puzzle.log"; 
	private static SaveStateTable sst;
	private static DatabaseManager dbm = new DatabaseManager();
	
	@BeforeClass
	public static void init() {
		dbm.createDatabase();
	}

	
	@Before
	public void setUp() {
		sst = dbm.getSaveStateTable();
	}
	
	@After
	public void tearDown() {
		sst.dropTable();
	}
	
	@Test
	public void createSaveStateTest() {
		File file = new File(serializedFile);
		sst.createSaveState(username, file);
		assertEquals(sst.getSaveState(username).getAbsolutePath(), file.getAbsolutePath());
	}
}
