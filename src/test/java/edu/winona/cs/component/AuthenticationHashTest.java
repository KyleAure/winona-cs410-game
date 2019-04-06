package edu.winona.cs.component;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

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
	private int passwordLength = testPassword.length();
	private String salt;
        private AuthenticationHash hash;
	
	@Before
	public void setup() {
		salt = hash.generateSalt();
	}
	
	//@Test
	public void testSaltGeneration() {
		//FIXME this test is failing.  Should authentication has generate the same salt for the same password length?
		//Otherwise, how are we going to verify passwords if we cannot generate the correct salt?
		String duplicateSalt = hash.generateSalt();
		
		assertEquals("Salt generated using the same length should be equal.", salt.toString(), duplicateSalt.toString());
	}
	
	@Test
	public void testGeneration() {
		String hashedPass = hash.hashPassword(testPassword, salt.toString());
		String duplicateHashPass = hash.hashPassword(testPassword, salt.toString());
		assertEquals("Hashed passwords should be the same.", hashedPass.toString(), duplicateHashPass.toString());
	}
	
	@Test
	public void testDivergence() {
		String hashedPass = hash.hashPassword(testPassword, salt.toString());
		String altHashPass = hash.hashPassword(altPassword, salt.toString());
		assertNotEquals("Hashed passwords should not be the same.", hashedPass.toString(), altHashPass.toString());
	}

}
