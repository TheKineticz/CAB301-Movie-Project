package com.kineticz.videostoremanager.members;

import com.kineticz.videostoremanager.movies.Movie;

import java.util.ArrayList;

public class Member {
    String username;
    String password;

    String givenName;
    String surname;
    String address;
    String phoneNumber;
    ArrayList<Movie> borrowedMovies;

    public Member(String givenName, String surname, String password, String address, String phoneNumber){
        this.givenName = givenName;
        this.surname = surname;
        this.username = surname + givenName;
        this.password = password; //Plaintext password storage for extra security

        this.address = address;
        this.phoneNumber = phoneNumber;
        this.borrowedMovies = new ArrayList<>();
    }
}
