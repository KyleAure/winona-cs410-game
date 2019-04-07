package edu.winona.cs.component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Hash Authentication Class
 *
 * This is used to verify passwords
 * 1) Create hash object
 * 2) Generate the salt
 * 3) Hash the password + salt
 * 4) Store the hash
 * 5) Use to verify password
 *
 * @author Tristin Harvell
 */
public class AuthenticationHash {
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 255;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    /**
     * Generate salt
     *
     * @require A password has to be entered.
     * @ensure The associated password has a salt generated with it.
     * @return Returns the generated salt as a string.
     */
    public String generateSalt() {
        //always use 5 as the salt
        int length = 5;

        byte[] salt = new byte[length];

        return Base64.getEncoder().encodeToString(salt);
    }

    /**
     * Hash password
     *
     * @param password This is the clear-text password the user inputs.    
     * @param salt This is the stored salt that was generated with the password.
     * @require A password has to be entered, and the salt is already generated.
     * @ensure The the password and salt get concatenated and hashed together.
     * @return Returns the resulting stored hash that includes both password and salt.
     */
    public String hashPassword(String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(securePassword);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return String.valueOf("x");

        } finally {
            spec.clearPassword();
        }
    }
}
