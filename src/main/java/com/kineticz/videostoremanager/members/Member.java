package com.kineticz.videostoremanager.members;

import com.kineticz.videostoremanager.movies.Movie;

import java.util.ArrayList;

/**
 * Container for registered members and their information
 */
public class Member {
    public String username;
    public String password;

    public String givenName;
    public String surname;
    public String address;
    public String phoneNumber;
    public ArrayList<Movie> borrowedMovies;

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
        this.username = surname + givenName;
        this.password = password; //Plaintext password storage for extra security

        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedMovies = new ArrayList<>();
    }
}
