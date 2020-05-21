package com.kineticz.videostoremanager.members;

/**
 * Staff account management container
 */
public class StaffAccount {
    private static final String staffUsername = "staff";
    private static final String staffPasswordSalt = "e488b1eca1e1ccf463d5b2af4aed2504e961586f186389e3baf467ad2838e845";
    private static final String staffPasswordHash = "134e24e42d6a3bcbb4b1200b95cdaa8c06b421106f46e03c7c7530728fda54d5";

    /**
     * Check the staff login details are correct
     *
     * @param username The entered staff username
     * @param password The entered staff password
     * @return Returns true if login credentials are correct, false otherwise
     */
    public static boolean checkStaffLogin(String username, String password) {
        return username.equals(staffUsername) && Passwords.checkPassword(password, staffPasswordSalt, staffPasswordHash);
    }
}
