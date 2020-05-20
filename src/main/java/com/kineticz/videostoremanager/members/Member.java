package com.kineticz.videostoremanager.members;

import com.kineticz.videostoremanager.movies.*;

/**
 * Container for registered members and their information
 */
public class Member {
    public final String username;
    private String passwordHash;
    private String passwordSalt;

    public final String givenName;
    public final String surname;
    public final String address;
    public final String phoneNumber;
    private MovieCollection borrowedMovies;

    /**
     * Creates a new member object
     *
     * @param givenName The given name of the member
     * @param surname The surname of the member
     * @param password The member's password
     * @param address The member's address
     * @param phoneNumber The member's phone number
     */
    public Member(String givenName, String surname, String password, String address, String phoneNumber) {
        this.givenName = givenName;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        borrowedMovies = new MovieCollection();

        username = surname + givenName;

        //Salted SHA-256 password encryption, because science isn't about why. It's about WHY NOT.
        //Hardly secure in 2020, but let's be honest - it's a library
        passwordSalt = Passwords.generateSalt();
        passwordHash = Passwords.generateHash(Passwords.saltPassword(password, passwordSalt));
    }

    /**
     * Check if an entered password matches the member's stored password information
     *
     * @param password The input password
     * @return Returns true if the password matches, false otherwise
     */
    public boolean checkPassword(String password) {
        return Passwords.checkPassword(password, passwordSalt, passwordHash);
    }
}
