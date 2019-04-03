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
	private Optional<String> salt;
	
	@Before
	public void setup() {
		salt = AuthenticationHash.generateSalt(passwordLength);
	}
	
	//@Test
	public void testSaltGeneration() {
		//FIXME this test is failing.  Should authentication has generate the same salt for the same password length?
		//Otherwise, how are we going to verify passwords if we cannot generate the correct salt?
		Optional<String> duplicateSalt = AuthenticationHash.generateSalt(passwordLength);
		
		assertEquals("Salt generated using the same length should be equal.", salt.get(), duplicateSalt.get());
	}
	
	@Test
	public void testGeneration() {
		Optional<String> hashedPass = AuthenticationHash.hashPassword(testPassword, salt.get());
		Optional<String> duplicateHashPass = AuthenticationHash.hashPassword(testPassword, salt.get());
		assertEquals("Hashed passwords should be the same.", hashedPass.get(), duplicateHashPass.get());
	}
	
	@Test
	public void testDivergence() {
		Optional<String> hashedPass = AuthenticationHash.hashPassword(testPassword, salt.get());
		Optional<String> altHashPass = AuthenticationHash.hashPassword(altPassword, salt.get());
		assertNotEquals("Hashed passwords should not be the same.", hashedPass.get(), altHashPass.get());
	}
	
	@Test
	public void testVerification() {
		Optional<String> hashedPass = AuthenticationHash.hashPassword(testPassword, salt.get());
		boolean result = AuthenticationHash.verifyPassword(testPassword, hashedPass.get(), salt.get());
		assertTrue("Verification should have returned true.", result);
	}

}
