package com.kineticz.videostoremanager.members;

import com.kineticz.videostoremanager.movies.*;

/**
 * Container for registered members and their information
 */
public class Member {
    public final String username;
    private final String passwordHash;
    private final String passwordSalt;

    public final String givenName;
    public final String surname;
    public final String address;
    public final String phoneNumber;
    private final MovieCollection borrowedMovies;

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
        //Hardly secure in 2020, but let's be honest - it's a community library
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

    /**
     * Borrows a movie and adds it to the member's borrowed movies list
     *
     * @param movie The movie to be borrowed
     * @return Returns true if the movie has been successfully borrowed, false if it cannot be borrowed
     */
    public boolean borrowMovie(Movie movie) {
        if (movie.borrowSingle()) {
            borrowedMovies.addMovie(movie);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns a movie that has been borrowed by a member
     *
     * @param movie The movie that has been returned
     */
    public void returnMovie(Movie movie) {
        borrowedMovies.deleteMovie(movie.title);
        movie.returnSingle();
    }

    /**
     * Get the currently borrowed movies
     *
     * @return The currently borrowed movies
     */
    public MovieCollection getBorrowedMovies() {
        return borrowedMovies;
    }
}
