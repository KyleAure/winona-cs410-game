package edu.winona.cs.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppTest {

	@Test
	public void stringValTest() {
		assertEquals("Should be TEST", "TEST", App.stringValidation());	
	}
	
	@Test
	public void intValTest() {
		assertEquals("Should be 0", 0, App.intValidation());
	}
	
	@Test
	public void boolValTest() {
		assertTrue("Should be True.", App.boolValidation());
	}
}
