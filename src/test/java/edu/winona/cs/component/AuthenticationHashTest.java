package edu.winona.cs.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * This is a test class to ensure that the Authentication Hash performs as expected
 * 
 * @author Kyle Aure
 * @version 1.0
 *
 */
public class AuthenticationHashTest {
	private String testPassword = "TestPassWord1!";
	private String altPassword = "AltePassWord1!";
	private String salt;
    private AuthenticationHash hash;
	
	@Before
	public void setup() {
		hash = new AuthenticationHash();
		salt = hash.generateSalt();
	}
	
	@Test
	public void testSaltGeneration() {
		String duplicateSalt = hash.generateSalt();
		assertEquals("Salt generated using the same length should be equal.", salt.toString(), duplicateSalt.toString());
	}
	
	@Test
	public void testGeneration() {
		String hashedPass = hash.hashPassword(testPassword, salt);
		String duplicateHashPass = hash.hashPassword(testPassword, salt);
		assertEquals("Hashed passwords should be the same.", hashedPass, duplicateHashPass);
	}
	
	@Test
	public void testDivergence() {
		String hashedPass = hash.hashPassword(testPassword, salt);
		String altHashPass = hash.hashPassword(altPassword, salt);
		assertNotEquals("Hashed passwords should not be the same.", hashedPass, altHashPass);
	}

}
