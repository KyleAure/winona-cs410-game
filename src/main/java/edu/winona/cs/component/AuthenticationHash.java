package edu.winona.cs.component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * Hash Authentication Class
 *
 * This is used to verify passwords
 *
 * @author Tristin Harvell
 */
public class AuthenticationHash {

    private static final SecureRandom RAND = new SecureRandom();
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 255;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";

    /**
     * Generate salt
     *
     * @param length This is the length of the password.
     * @require A password has to be entered.
     * @ensure The associated password has a salt generated with it.
     * @return Returns the generated salt as a string.
     */
    public Optional<String> generateSalt(final int length) {

        if (length < 1) {
            System.err.println("error in generateSalt: length must be > 0");
            return Optional.empty();
        }

        byte[] salt = new byte[length];
        RAND.nextBytes(salt);

        return Optional.of(Base64.getEncoder().encodeToString(salt));
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
    public Optional<String> hashPassword(String password, String salt) {

        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);

        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();

            return Optional.of(Base64.getEncoder().encodeToString(securePassword));

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            System.err.println("Exception encountered in hashPassword()");
            return Optional.empty();

        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Verify password
     *
     * @param password This is the clear-text password the user inputs.    
     * @param key This is the stored hashed password and salt together.
     * @param salt This is the stored salt that was generated with the password.
     * @require Password is entered, while associated key and salt are already stored.
     * @ensure The the password and salt get concatenated and hashed together.
     * @return Returns the resulting hash that includes both password and salt.
     */
    public boolean verifyPassword(String password, String key, String salt) {
        Optional<String> optEncrypted = hashPassword(password, salt);
        if (!optEncrypted.isPresent()) {
            return false;
        }
        return optEncrypted.get().equals(key);
    }

    //store in db
    public void addLoginToDB(String customerID, String securePassword) {

    }
}
