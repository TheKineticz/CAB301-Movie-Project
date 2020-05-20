package com.kineticz.videostoremanager.members;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Secure password generator class
 */
class Passwords {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int SALT_LENGTH = 32;

    /**
     * Generate a SHA-256 hash from a salted password
     *
     * @param input The password with salt added
     * @return The salted hash of the password
     */
    public static String generateHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = md.digest(input.getBytes(StandardCharsets.UTF_8));

            return bytesToHex(encodedHash);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Generates a 16 character long string of random UTF-8 characters
     *
     * @return The 16 character long string
     */
    public static String generateSalt() {
        byte[] saltBytes = new byte[SALT_LENGTH];
        RANDOM.nextBytes(saltBytes);
        return bytesToHex(saltBytes);
    }

    /**
     * Combine the password and the salt
     *
     * @param password The password
     * @param salt The generated salt
     * @return The password and salt combined
     */
    public static String saltPassword(String password, String salt) {
        return password + salt;
    }

    /**
     * Check if an input password is correct, given the salt and hash
     *
     * @param password The input password
     * @param salt The stored salt for the password
     * @param hash The stored hash of the password
     * @return Return true if the password matches, false otherwise
     */
    public static boolean checkPassword(String password, String salt, String hash) {
        return generateHash(saltPassword(password, salt)).equals(hash);
    }

    /**
     * Convert input bytes to hexadecimal string
     *
     * @param input Array of bytes
     * @return The array of bytes in hexadecimal form as a string
     */
    private static String bytesToHex(byte[] input) {
        StringBuilder hexString = new StringBuilder();

        for (byte b : input) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1){
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }
}
